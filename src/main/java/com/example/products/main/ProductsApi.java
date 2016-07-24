package com.example.products.main;

import com.example.products.constants.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by a036862 on 7/21/16.
 */
@SpringBootApplication
@ComponentScan(basePackages = Constants.PACKAGES)

public class ProductsApi {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApi.class, args);
    }

}
