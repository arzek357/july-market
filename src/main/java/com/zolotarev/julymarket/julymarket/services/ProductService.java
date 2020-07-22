package com.zolotarev.julymarket.julymarket.services;

import com.zolotarev.julymarket.julymarket.models.Product;
import com.zolotarev.julymarket.julymarket.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addNewProduct(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findAllByPage(Specification<Product> spec, Pageable pageable){
        return productRepository.findAll(spec,pageable);
    }
    public Product findById(long id){
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
