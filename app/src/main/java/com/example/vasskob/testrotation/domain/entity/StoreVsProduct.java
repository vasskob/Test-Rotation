package com.example.vasskob.testrotation.domain.entity;

public class StoreVsProduct {
    private Store store;
    private Product product;

    public StoreVsProduct(Store store, Product product) {
        this.store = store;
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
