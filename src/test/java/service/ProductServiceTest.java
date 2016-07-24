package service;

import com.example.products.constants.Constants;
import com.example.products.domain.Product;
import com.example.products.repository.ProductRepository;
import com.example.products.service.ProductService;
import com.example.products.wrapper.ResponseWrapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by a036862 on 7/23/16.
 */
public class ProductServiceTest {

    private ProductRepository mockProductRepository = mock(ProductRepository.class);
    private ProductService productService = new ProductService();
    private static final Integer ID = 13860428;
    private static final Integer INVALID_ID = 123;
    private static final String GENERAL_DESCRIPTION = "BIG LEBOWSKI, THE Blu-ray";
    private static final String NOT_VALID_DESCRIPTION = "Not valid product in system: This product ID does not represent a valid product";

    @Before
    public void setup(){
        reset(mockProductRepository);
        productService.setProductRepository(mockProductRepository);
        ReflectionTestUtils.setField(productService, "itemServiceUrl", "https://api.target.com/products/v3/");
        ReflectionTestUtils.setField(productService, "itemServiceKey", "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
    }

    @Test
    public void getProductDetailsTest() throws IOException {
        //Given
        Product product = new Product();
        product.setCurrencyCode("USD");
        product.setValue(10.0);
        Map priceMap = new HashMap<>();
        priceMap.put(Constants.VALUE, 10.0);
        priceMap.put(Constants.CURRENCY_CODE, "USD");

        //When
        when(mockProductRepository.getProductDetails(ID)).thenReturn(product);

        //then
        ResponseWrapper result = productService.getProductDetails(ID);
        assertThat(result.getProductId(), is(ID));
        assertThat(result.getCurrentPrice(), is(priceMap));
        assertThat(result.getName(), is(GENERAL_DESCRIPTION));

    }

    @Test
    public void getInvalidProductDetailsTest() throws IOException {

        ResponseWrapper result = productService.getProductDetails(INVALID_ID);
        verify(mockProductRepository, never()).getProductDetails(INVALID_ID);
        assertThat(result.getProductId(), is(INVALID_ID));
        assertThat(result.getCurrentPrice(), is(nullValue()));
        assertThat(result.getName(), is(NOT_VALID_DESCRIPTION));

    }


}
