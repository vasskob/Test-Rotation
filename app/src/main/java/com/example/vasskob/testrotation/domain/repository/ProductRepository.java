package com.example.vasskob.testrotation.domain.repository;

import com.example.vasskob.testrotation.domain.entity.Product;

import java.util.List;

import io.reactivex.Observable;


public interface ProductRepository {
    Observable<List<Product>> products(long shopId);
}
