package homework3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HomeworkTests extends AbstractTest {

    @Test
    void getSpecifyingRequestDataTest() {

        //GET 1
        given()
                .queryParam("apiKey", getApiKey())
                .pathParam("id", "1697833")
                .when()
                .get(getBaseUrl() + "recipes/{id}/equipmentWidget.json")
                .then()
                .statusCode(200);

        //GET 2
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("ingredients", "apples")
                .queryParam("ingredients", "flour")
                .queryParam("ingredients", "sugar")
                .queryParam("ingredients", "salt")
                .queryParam("number", "1")
                .when()
                .get(getBaseUrl() + "recipes/findByIngredients")
                .then()
                .statusCode(200);

        //POST 1
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Scaloppine al Limone")
                .formParam("ingredientList", "4lb boneless chicken thighs")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void getResponseData() {
        //GET 3
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + "food/products/22345");

        //Get status code
        System.out.println("status code: " + response.statusCode());

        //Get content-type
        System.out.println("content-type: " + response.getContentType());

        //Get status line
        System.out.println("StatusLine: " + response.getStatusLine());

        System.out.println("************************");


        //POST 2
        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine);
    }

    @Test
    void getVerifyingResponseData() {
        //GET 4
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .pathParam("id", "716429")
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .body()
                .jsonPath();

        assertThat(response.get("vegetarian"), is(false));
        assertThat(response.get("vegan"), is(false));

        Assertions.assertFalse(response.getBoolean("dairyFree"), "false");

        //GET 5
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .pathParam("id", "716429")
                .when()
                .get(getBaseUrl() + "recipes/{id}/information")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Connection", "keep-alive");

        //GET 6
        given()
                .queryParam("apiKey", getApiKey())
                .pathParam("id", "31868")
                .response()
                .contentType(ContentType.JSON)
                .header("Connection", "keep-alive")
                .expect()
                .body("vegetarian", is(false))
                .body("vegan", is(false))
                .body("dairyFree", is(true))
                .when()
                .get(getBaseUrl() + "recipes/{id}/information");


    }

    @Test
    void anotherPostTests() {
        //POST 3
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList", "black tea")
                .formParam("servings", "1")
                .formParam("includeNutrition", "true")
                .when()
                .post(getBaseUrl() + "recipes/parseIngredients")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Connection", "keep-alive");

        //POST 4
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList", "arabica coffee")
                .formParam("servings", "2")
                .formParam("includeNutrition", "true")
                .when()
                .post(getBaseUrl() + "recipes/parseIngredients")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Connection", "keep-alive");

        //POST 5
        Response response1 = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Dinner Tonight: Chickpea Bruschetta")
                //.formParam("ingredientList", "black tea")
                .when()
                .post(getBaseUrl() + "recipes/cuisine");

        //Get status code
        System.out.println("status code: " + response1.statusCode());

        //Get content-type
        System.out.println("content-type: " + response1.getContentType());

        //Get status line
        System.out.println("StatusLine: " + response1.getStatusLine());

        //POST 6
        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .formParam("title", "Dinner Tonight: Chickpea Bruschetta")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .path("cuisine");
        System.out.println("cuisine: " + cuisine);
    }

}
