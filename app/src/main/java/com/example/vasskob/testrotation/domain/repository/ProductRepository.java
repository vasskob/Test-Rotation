package com.example.vasskob.testrotation.domain.repository;

import com.example.vasskob.testrotation.domain.dto.Product;

import java.util.List;

import io.reactivex.Single;

public interface ProductRepository {
    Single<List<Product>> getProducts(long shopId);
}
