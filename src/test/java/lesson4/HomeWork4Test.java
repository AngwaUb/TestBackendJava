package lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HomeWork4Test extends AbstractTest {
    @Test
    void getStatusCodeTest() {
        given().spec(getRequestSpecification())
                .when()
                .get(getUrlBase() + "recipes/716429/information")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecification())
                .queryParam("number", 8)
                .queryParam("query", "apple")
                .when()
                .get(getUrlBase() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getResponseContainTest() {
        given().spec(requestSpecification)
                .expect()
                .body("vegetarian", equalTo(true))
                .body("cheap", equalTo(false))
                .body("healthScore", equalTo(3))
                .when()
                .get(getUrlBase() + "recipes/632631/information")
                .then()
                .spec(responseSpecification);

        Response response1 = given().spec(requestSpecification)
                .queryParam("number", 8)
                .when()
                .get(getUrlBase() + "recipes/complexSearch")
                .then()
                .extract()
                .body()
                .as(Response.class);
        assertThat(response1.getOffset(), equalTo(0));
        assertThat(response1.getNumber(), equalTo(8));

        Response response2 = given().spec(requestSpecification)
                .queryParam("cuisine", "italian")
                .queryParam("query", "pasta")
                .when()
                .get(getUrlBase() + "recipes/complexSearch")
                .body()
                .as(Response.class);
        assertThat(response2.getTotalResults(), equalTo(34));
    }
}
