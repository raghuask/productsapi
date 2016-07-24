package rest;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by a036862 on 7/23/16.
 */
public class ProductControllerTest {

    private ProductService mockProductService = mock(ProductService.class);
    private ProductContoller productContoller = new ProductContoller();
    private static final Integer ID = 123;
    private static final String EXCEPTIONMESSAGE = "{\"message\":\"Unexpected error occured\"}";
    private static final String PRODUCTRESPONSE = "{\"productId\":123,\"name\":\"Test\",\"currentPrice\":{\"value\":10.0,\"currency_code\":\"USD\"}}";

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
        priceMap.put("value", 10.0);
        priceMap.put("currency_code", "USD");
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
}
