package rest;

import com.example.products.constants.Constants;
import com.example.products.domain.Product;
import com.example.products.rest.ProductContoller;
import com.example.products.service.ProductService;
import com.example.products.wrapper.ResponseWrapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by a036862 on 7/23/16.
 */
public class ProductControllerTest {

    private ProductService mockProductService = mock(ProductService.class);
    private ProductContoller productContoller = new ProductContoller();
    private static final Integer ID = 123;
    private static final String EXCEPTIONMESSAGE = "{\"message\":\"Unexpected error occured\"}";
    private static final String PRODUCTRESPONSE = "{\"productId\":123,\"name\":\"Test\",\"currentPrice\":{\"value\":10.0,\"currencyCode\":\"USD\"}}";
    private static final String SUCCESSMESSAGE = "{\"message\":\"Successfully updated the price details\"}";

    @Before
    public void setup(){
        reset(mockProductService);
        productContoller.setProductService(mockProductService);
    }


    @Test
    public void getProductDetailsTest() throws IOException {
        //Given
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setProductId(123);
        responseWrapper.setName("Test");
        Map priceMap = new HashMap<>();
        priceMap.put(Constants.VALUE, 10.0);
        priceMap.put(Constants.CURRENCY_CODE, "USD");
        responseWrapper.setCurrentPrice(priceMap);

        //When
        when(mockProductService.getProductDetails(ID)).thenReturn(responseWrapper);

        //then
        ResponseEntity<?> result = productContoller.getProductDetails(ID);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(PRODUCTRESPONSE));

    }

    @Test
    public void getProductDetailsException() throws IOException {

        //Throw exception when Mongo is called
        when(mockProductService.getProductDetails(ID)).thenThrow(IOException.class);

        ResponseEntity<?> result = productContoller.getProductDetails(ID);
        assertThat(result.getStatusCode(), is(HttpStatus.SERVICE_UNAVAILABLE));
        assertThat(result.getBody(), is(EXCEPTIONMESSAGE));
    }


    @Test
    public void saveProductDetails(){
       //Given
        //Insert a product record into mongo
        Product product = new Product();
        product.setValue(10.90);
        product.setCurrencyCode("USD");


        //then
        ResponseEntity<?> result = productContoller.saveProduct(ID, product);

        assertThat(result.getStatusCode(), is(HttpStatus.ACCEPTED));
        assertThat(result.getBody(), is(SUCCESSMESSAGE));
        verify(mockProductService, times(1)).saveProductPrice(ID, product);

    }

    @Test
    public void saveProductDetailsException() throws IOException {

        Product product = new Product();

        //Throw exception when Mongo is called
        doThrow(IOException.class).when(mockProductService).saveProductPrice(ID, product);

        ResponseEntity<?> result = productContoller.saveProduct(ID, product);
        assertThat(result.getStatusCode(), is(HttpStatus.SERVICE_UNAVAILABLE));
        assertThat(result.getBody(), is(EXCEPTIONMESSAGE));
    }

}
