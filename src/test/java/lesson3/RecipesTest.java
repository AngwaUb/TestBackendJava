package lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class RecipesTest extends AbstractTest {

    @Test
    void getStatusCodeTest() {
        given()
                .when()
                .get(getUrlBase() + "recipes/716429/information?" +
                        "includeNutrition=false&apiKey=" + getApiKey())
                .then()
                .statusCode(200);

        given()
                .when()
                .request(Method.GET, getUrlBase() + "recipes/716429/information?" +
                        "number={number}&query={query}&apiKey={apiKey}", 3, "apple", getApiKey())
                .then()
                .statusCode(200);


        given()
                .when()
                .request(Method.GET, getUrlBase() + "recipes/complexSearch")
                .then()
                .statusCode(401);

        given()
                .when()
                .request(Method.GET, getUrlBase() + "recipes/716429/information?" +
                        "number={number}&query={query}&apiKey={apiKey}", 3, "apple", getApiKey())
                .then()
                .time(lessThan(1000L));
    }

    @Test
    void getResponseContainTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .expect()
                .body("vegetarian", equalTo(true))
                .body("cheap", equalTo(false))
                .body("healthScore", equalTo(3))
                .when()
                .get(getUrlBase() + "recipes/632631/information")
                .then()
                .statusCode(200);

        JsonPath response1 = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getUrlBase() + "recipes/632631/information")
                .body()
                .jsonPath();
        assertThat(response1.get("healthScore"), equalTo(3));
        assertThat(response1.get("extendedIngredients[0].aisle"), equalTo("Oil, Vinegar, Salad Dressing"));

        JsonPath response2 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .queryParam("query", "pasta")
                .when()
                .get(getUrlBase() + "recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response2.get("totalResults"), equalTo(34));

        JsonPath response3 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("number", "8")
                .when()
                .get(getUrlBase() + "recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response3.get("number"), equalTo(8));
    }

    @Test
    void postCuisineTest() {
        given()
                .when()
                .request(Method.POST, getUrlBase() + "recipes/cuisine?language={language}&apiKey={apiKey}", "en", getApiKey())
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getUrlBase() + "recipes/cuisine")
                .then().statusCode(200);

        JsonPath response4 = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getUrlBase() + "recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response4.get("cuisine"), equalTo("Mediterranean"));
        assertThat(response4.get("cuisines[0]"), equalTo("Mediterranean"));

        JsonPath response5 = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getUrlBase() + "recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response5.get("cuisines[1]"), equalTo("European"));

        JsonPath response6 = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getUrlBase() + "recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response6.get("cuisines[2]"), equalTo("Italian"));
    }

    @Test
    void addMealTest2() {
        String id = given()
                .queryParam("hash", getHash())
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"PRODUCT\",\n"
                        + " \"value\": {\n"
                        + " \"id\": 183433,\n"
                        + " \"servings\": 1,\n"
                        + " \"title\": \"Ahold Lasagna with Meat Sauce\",\n"
                        + " \"imageType\": \"jpg\"\n"
                        + " }\n"
                        + "}")
                .when()
                .post(getUrlBase() + "mealplanner/" + getUsername() + "/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", getHash())
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/andreburdenko/items/" + id)
                .then()
                .statusCode(200);
    }

}
