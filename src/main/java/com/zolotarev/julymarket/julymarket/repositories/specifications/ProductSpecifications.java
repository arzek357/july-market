package com.zolotarev.julymarket.julymarket.repositories.specifications;

import com.zolotarev.julymarket.julymarket.models.Product;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecifications {
    public static Specification<Product> priceLEThan(int price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),price);
    }
    public static Specification<Product> priceGEThan(int price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),price);
    }
    public static Specification<Product> nameLike(String msg){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"),"%"+msg+"%");
    }
}
