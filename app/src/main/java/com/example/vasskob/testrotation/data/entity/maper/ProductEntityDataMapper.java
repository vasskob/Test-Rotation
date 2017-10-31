package com.example.vasskob.testrotation.data.entity.maper;


import com.example.vasskob.testrotation.data.entity.ProductEntity;
import com.example.vasskob.testrotation.domain.model.Product;

import io.reactivex.functions.Function;

public class ProductEntityDataMapper implements Function<ProductEntity, Product> {

    @Override
    public Product apply(ProductEntity productEntity) throws Exception {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setImageUrl(productEntity.getImageUrl());
        product.setPriceInCents(productEntity.getPriceInCents());
        product.setPrimaryCategory(productEntity.getPrimaryCategory());
        product.setInventoryCount(productEntity.getInventoryCount());
        return product;
    }
}
