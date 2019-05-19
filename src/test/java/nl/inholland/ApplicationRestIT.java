package nl.inholland;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class ApplicationRestIT {

    @LocalServerPort
    private int port;

    public String ROOT_URL = "http://localhost:" + port ;

    @Test
    public void getAllGuitars() {

        Response response = get(ROOT_URL + "/guitars");
        response.then().body("id", hasItems(1000001, 1000002, 1000003, 1000004, 1000005));
    }

    @Test
    public void getOneGuitar() {
        Response response = get(ROOT_URL + "/guitars/" +1000001 );
        response.then().body("model", Matchers.is("Stratocaster"));
    }

    @Test
    public void createGuitarIsOk() {
        given()
                .contentType("application/json")
                .body("{\"brand\":\"Fender\",\"model\":\"Bullit\",\"price\":800}")
                .when().post(ROOT_URL + "/guitars/")
                .then()
                .statusCode(201)
                .and()
                .body(Matchers.is("1000006"));

    }

    @Test
    public void noContentTypeShouldThrow415() {
        given()
                .body("{\"brand\":\"Fender\",\"model\":\"Stratocaster\",\"price\":800}")
                .when().post(ROOT_URL + "/guitars/")
                .then()
                .statusCode(415);
    }

    @Test
    public void postExistingGuitarShouldReturnServerError() {

        given()
                .contentType("application/json")
                .body("{\"brand\":\"Fender\",\"model\":\"Stratocaster\",\"price\":800}")
                .when().post(ROOT_URL + "/guitars/")
                .then()
                .statusCode(500);
    }
}
