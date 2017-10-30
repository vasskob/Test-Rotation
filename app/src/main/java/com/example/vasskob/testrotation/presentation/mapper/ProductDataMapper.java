package com.example.vasskob.testrotation.presentation.mapper;


import com.example.vasskob.testrotation.domain.dto.Product;
import com.example.vasskob.testrotation.presentation.model.ProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductDataMapper {

    public ProductModel transform(Product productEntity) {
        ProductModel productModel = null;
        if (productEntity != null) {
            productModel = new ProductModel();
            productModel.setId(productEntity.getId());
            productModel.setName(productEntity.getName());
            productModel.setImageUrl(productEntity.getImageUrl());
            productModel.setPriceInCents(productEntity.getPriceInCents());
            productModel.setPrimaryCategory(productEntity.getPrimaryCategory());
            productModel.setInventoryCount(productEntity.getInventoryCount());
        }
        return productModel;
    }

    public List<ProductModel> transform(Collection<Product> productEntities) {
        final List<ProductModel> productModels = new ArrayList<>();
        for (Product productEntity : productEntities) {
            final ProductModel productModel = transform(productEntity);
            if (productModel != null) {
                productModels.add(productModel);
            }
        }
        return productModels;
    }

}
