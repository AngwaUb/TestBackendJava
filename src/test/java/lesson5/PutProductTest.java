package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutProductTest {

    static ProductService productService;
    Product product = null;
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @Test
    void changeProductInFoodCategoryTest() throws IOException {
        product = new Product()
                .withCategoryTitle("Food")
                .withPrice(112)
                .withTitle("Sandwich");

        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        product = new Product()
                .withCategoryTitle("Food")
                .withPrice(200)
                .withTitle("SandwichBigger")
                .withId(id);

        response = productService.modifyProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        response = productService.getProductById(id).execute();
        assertThat(response.body().getPrice(), equalTo(200));
        assertThat(response.body().getTitle(), equalTo("SandwichBigger"));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(product.getId()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
