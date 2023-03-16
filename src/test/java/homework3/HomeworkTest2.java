package homework3;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HomeworkTest2 extends AbstractTest{
    ResponseSpecification responseSpecification = null;

    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(8000L))
                .expectHeader("Connection", "keep-alive")
                .build();
    }

    RequestSpecification requestSpecification = null;

    @BeforeEach
    void beforeTest1() {
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", getApiKey())
                .addPathParam("username", "minincorg")
                .build();
    }

    @Test
    void addToShoppingList(){
        given()
                .spec(requestSpecification)
                .when()
                .post(getBaseUrl() + "mealplanner/{username}/shopping-list/items")
                .then()
                .spec(responseSpecification);
    }
}
