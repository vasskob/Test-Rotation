package com.example.vasskob.testrotation.data.entity.maper;

import com.example.vasskob.testrotation.data.entity.StoreEntity;
import com.example.vasskob.testrotation.domain.model.Store;

import io.reactivex.functions.Function;

public class StoreEntityDataMapper implements Function<StoreEntity, Store> {

    @Override
    public Store apply(StoreEntity storeEntity) throws Exception {
        Store store = new Store();
        store.setId(storeEntity.getId());
        store.setName(storeEntity.getName());
        store.setAddress1(storeEntity.getAddress1());
        store.setAddress2(storeEntity.getAddress2());
        store.setCity(storeEntity.getCity());
        store.setTelephone(storeEntity.getTelephone());
        store.setLatitude(storeEntity.getLatitude());
        store.setLongitude(storeEntity.getLongitude());
        store.setHasWheelchairAccessability(storeEntity.getHasWheelchairAccessability());
        store.setHasBilingualServices(storeEntity.getHasBilingualServices());
        store.setHasTastingBar(storeEntity.getHasTastingBar());
        store.setHasBeerColdRoom(storeEntity.getHasBeerColdRoom());
        store.setHasVintagesCorner(storeEntity.getHasVintagesCorner());
        store.setHasParking(storeEntity.getHasParking());
        return store;
    }
}
