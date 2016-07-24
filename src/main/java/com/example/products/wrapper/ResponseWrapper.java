package com.example.products.wrapper;

import java.util.Map;

/**
 * Created by a036862 on 7/22/16.
 */
public class ResponseWrapper {

    private Integer productId;
    private String name;
    private Map<String, Object> currentPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Map<String, Object> currentPrice) {
        this.currentPrice = currentPrice;
    }
}
