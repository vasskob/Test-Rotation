package com.example.vasskob.testrotation.domain.repository;


import com.example.vasskob.testrotation.domain.entity.Store;

import java.util.List;

import io.reactivex.Observable;


public interface StoreRepository {
    Observable<List<Store>> stores();
}
