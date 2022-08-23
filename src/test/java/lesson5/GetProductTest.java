package lesson5;

import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductTest {

    static ProductService productService;

    public GetProductTest() throws IOException {
    }

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @Test
    void getProductByIdPositive() throws IOException {
        Response<Product> response = productService.getProductById(2).execute();
        assertThat(response.body().getId(), equalTo(2));
        assertThat(response.body().getTitle(), equalTo("Bread"));
    }

    @Test
    void getProductByIdNegative() throws IOException {
        Response<Product> response = productService.getProductById(100).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

}
