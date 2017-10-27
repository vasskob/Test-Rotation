package com.example.vasskob.testrotation.domain.repository;

import com.example.vasskob.testrotation.domain.entity.StoreVsProduct;

import java.util.List;

import io.reactivex.Observable;

public interface StoreVsProductRepository {
    Observable<List<StoreVsProduct>> storesVsProducts();
}
