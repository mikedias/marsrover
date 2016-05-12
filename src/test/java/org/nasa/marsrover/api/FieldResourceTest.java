package org.nasa.marsrover.api;

import com.jayway.restassured.RestAssured;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nasa.marsrover.Field;

import javax.ws.rs.core.Response;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.http.ContentType.JSON;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static org.hamcrest.Matchers.*;

/**
 * @author Mike Dias
 */
public class FieldResourceTest {

    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        server = RestApp.startServer();
        RestAssured.baseURI = RestApp.BASE_URI;
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testCrudOperations() {

        int width = 5;
        int height = 5;
        Field f = new Field(width, height);

        // create
        f = given()
            .contentType(JSON)
            .body(f)
        .when()
            .post("fields")
        .then()
            .body("id", not(isEmptyOrNullString()))
            .body("width", is(width))
            .body("height", is(height))
        .extract()
            .body()
            .as(Field.class);

        // get
        get("fields/{id}", f.getId()).then()
            .body("id", is(f.getId()))
            .body("width", is(f.getWidth()))
            .body("height", is(f.getHeight()));

        // list
        get("fields").then().body("id", containsInAnyOrder(f.getId()));

        int newWidth = 3;
        int newHeight = 3;

        f.setWidth(newWidth);
        f.setHeight(newHeight);

        // update
        f = given()
            .contentType(JSON)
            .body(f)
        .when()
            .put("fields/{id}", f.getId())
        .then()
            .body("width", is(newWidth))
            .body("height", is(newHeight))
        .extract()
            .body()
            .as(Field.class);

        // delete
        delete("fields/{id}", f.getId()).then().statusCode(NO_CONTENT.getStatusCode());

    }

}