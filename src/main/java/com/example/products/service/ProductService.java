package com.example.products.service;


import com.example.products.constants.Constants;
import com.example.products.domain.Product;
import com.example.products.repository.ProductRepository;
import com.example.products.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a036862 on 7/21/16.
 */
@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public ResponseWrapper getProductDetails (Integer id) throws IOException {

            String description;
            ResponseEntity<String> productResponse = getProductName(id);

            if (productResponse.getStatusCode().is2xxSuccessful()) {
                JsonNode items = new ObjectMapper().readTree(productResponse.getBody()).get(Constants.PRODUCT_COMPOSITE_RESPONSE).get(Constants.ITEMS).get(0);
                if (null != items.get(Constants.GENERAL_DESCRIPTION)) {
                    description = items.get(Constants.GENERAL_DESCRIPTION).asText();
                } else {
                    description = items.get(Constants.ERRORS).get(0).get(Constants.MESSAGE).asText();
                }

            } else {
                description = Constants.DESCRIPTION_UNAVAILABLE;
            }
            ResponseWrapper responseWrapper = new ResponseWrapper();
            responseWrapper.setProductId(id);
            responseWrapper.setName(description);
            if(!description.contains(Constants.NOT_VALID_PRODUCT)) {
                Product product = productRepository.getProductDetails(id);
                if(product!=null) {
                    Map priceMap = new HashMap<>();
                    priceMap.put(Constants.VALUE, product.getValue());
                    priceMap.put(Constants.CURRENCY_CODE, product.getCurrencyCode());
                    responseWrapper.setCurrentPrice(priceMap);
                }
            }

            return responseWrapper;

    }
    /*This method gets Product name from the Item service*/
    public ResponseEntity<String> getProductName(Integer id){

        String serviceUrl = "https://api.target.com/products/v3/"+id+"?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> headerEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(serviceUrl, HttpMethod.GET, headerEntity, String.class);

    }

    public void saveProductPrice(Integer id, Product product){

        productRepository.saveProductPrice(product.getValue(), product.getCurrencyCode(), id);
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
