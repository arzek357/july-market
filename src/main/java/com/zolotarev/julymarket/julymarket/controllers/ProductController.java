package com.zolotarev.julymarket.julymarket.controllers;

import com.zolotarev.julymarket.julymarket.models.Product;
import com.zolotarev.julymarket.julymarket.services.ProductService;
import com.zolotarev.julymarket.julymarket.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String printAllProductInfo(Model model, @RequestParam(name = "p",defaultValue = "1") Integer pageNumber, @RequestParam Map<String,String> requestParams) {
        ProductFilter productFilter = new ProductFilter(requestParams);
        Page<Product> products = productService.findAllByPage(productFilter.getSpec(), PageRequest.of(pageNumber-1,10, Sort.Direction.ASC,"id"));
        model.addAttribute("products", products);
        model.addAttribute("filterDef",productFilter.getFilterDefinition().toString());
        return "products";
    }
    @GetMapping("/add")
    public String tryAddNewProduct(){
        return "add_product_form";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product newProduct) {
        productService.addNewProduct(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product_form";
    }
}
