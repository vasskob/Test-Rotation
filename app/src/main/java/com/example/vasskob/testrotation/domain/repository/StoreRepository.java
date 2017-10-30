package com.example.vasskob.testrotation.domain.repository;


import com.example.vasskob.testrotation.domain.dto.Store;

import java.util.List;

import io.reactivex.Single;


public interface StoreRepository {
    Single<List<Store>> getStores();
}
