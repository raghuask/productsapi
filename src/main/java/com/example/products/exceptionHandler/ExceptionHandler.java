package com.example.products.exceptionhandler;

import com.example.products.constants.Constants;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by a036862 on 7/24/16.
 */
public final class ExceptionHandler {

    private ExceptionHandler(){}

    public static ResponseEntity<String> handleException(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Constants.MESSAGE,Constants.ERROR_MESSAGE);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.SERVICE_UNAVAILABLE);

    }


}
