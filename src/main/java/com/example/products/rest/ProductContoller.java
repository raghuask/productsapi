package com.example.products.rest;

import com.example.products.constants.Constants;
import com.example.products.domain.Product;
import com.example.products.service.ProductService;
import com.example.products.wrapper.ResponseWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by a036862 on 7/21/16.
 */
@RestController
public class ProductContoller {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductContoller.class);

    @RequestMapping(value = Constants.GET_PRODUCT_BY_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductDetails(@PathVariable (Constants.ID) Integer id) {
        try {
            ResponseWrapper result = productService.getProductDetails(id);
            ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
        }
        catch (Exception e) {
            logger.error("Exception is:" + e);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("message","Unexpected error occured");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = Constants.SAVE_PRODUCT_PRICE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProduct(@PathVariable (Constants.ID) Integer id, @RequestBody Product product) {
        try {
            productService.saveProductPrice(id, product);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("message","Successfully updated the price details");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            logger.error("Exception is:" + e);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("message","Unexpected error occured");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
