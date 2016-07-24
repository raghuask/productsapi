package com.example.products.domain;

import com.example.products.constants.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by a036862 on 7/21/16.
 */

public class Product {

    private Integer productId;
    private double value;
    @NotEmpty
    @NotNull
    @Size(max = 3)
    @Pattern(regexp = Constants.STRINGREGEX)
    private String currencyCode;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}

