package nl.inholland;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuitarIT {

    @LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetAllGuitarsShouldRetrieveAnArrayOfGuitars() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = template.exchange(
                createFullUrl("/guitars"),
                HttpMethod.GET, entity, String.class);

        JSONArray array = new JSONArray(response.getBody());
        Assert.assertTrue(array.length() >= 5);
    }
    @Test
    public void testRetrieveOneGuitar() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = template.exchange(
                createFullUrl("/guitars/1000001"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":1000001,\"brand\":\"Fender\",\"model\":\"Stratocaster\",\"price\":1750}";
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }


    @Test
    public void testCreateGuitarShouldReturnCreated() {

        headers.add("Content-type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>("{\"brand\": \"Fender\",\"model\": \"Bullit\", \"price\": 750}", headers);

        ResponseEntity<String> response = template.exchange(
                createFullUrl("/guitars"),
                HttpMethod.POST, entity, String.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertTrue(response.getBody().startsWith("10000"));
    }


    @Test
    public void testCreateExistingGuitarShouldGiveServerError() {

        headers.add("Content-type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>("{\"brand\": \"Fender\",\"model\": \"Precision\", \"price\": 750}", headers);

        ResponseEntity<String> response = template.exchange(
                createFullUrl("/guitars"),
                HttpMethod.POST, entity, String.class);
        ResponseEntity<String> duplicateResponse = template.exchange(
                createFullUrl("/guitars"),
                HttpMethod.POST, entity, String.class);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, duplicateResponse.getStatusCode());
    }

    private String createFullUrl(String uri) {
        return "http://localhost:" + port + uri;
    }
}
