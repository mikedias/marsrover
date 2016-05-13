package org.nasa.marsrover.api;

import org.junit.Test;
import org.nasa.marsrover.Direction;
import org.nasa.marsrover.Field;
import org.nasa.marsrover.Rover;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.http.ContentType.JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.nasa.marsrover.Direction.E;
import static org.nasa.marsrover.Direction.N;

/**
 * @author Mike Dias
 */
public class RoverResourceTest extends ApiTest {

    @Test
    public void testCrudOperations() {

        int width = 5;
        int height = 5;
        Field f = new Field(width, height);

        // create field
        f = given()
            .contentType(JSON)
            .body(f)
        .when()
            .post("fields")
        .then()
            .extract()
            .body()
            .as(Field.class);

        int x = 1;
        int y = 2;
        Direction d = N;

        Rover r = new Rover();
        r.setX(x);
        r.setY(y);
        r.setDirection(d);

        // create rover
        r = given()
            .contentType(JSON)
            .body(r)
        .when()
            .post("fields/{id}/rovers", f.getId())
        .then()
            .body("id", not(isEmptyOrNullString()))
            .body("x", is(x))
            .body("y", is(y))
            .body("direction", is(d.name()))
            .body("field.id", is(f.getId()))
        .extract()
            .body()
            .as(Rover.class);

        // get
        get("fields/{id}/rovers/{rid}", f.getId(), r.getId()).then()
            .body("id", is(r.getId()))
            .body("x", is(r.getX()))
            .body("y", is(r.getY()))
            .body("direction", is(d.name()))
            .body("field.id", is(f.getId()));

        // list
        get("fields/{id}/rovers", f.getId()).then().body("id", containsInAnyOrder(r.getId()));

        int newX = 3;
        int newY = 3;

        r.setX(newX);
        r.setY(newY);

        // update
        r = given()
            .contentType(JSON)
            .body(r)
        .when()
            .put("fields/{id}/rovers/{rid}", f.getId(), r.getId())
        .then()
            .body("x", is(newX))
            .body("y", is(newY))
        .extract()
            .body()
            .as(Rover.class);

        // delete
        delete("fields/{id}/rovers/{rid}", f.getId(), r.getId()).then().statusCode(NO_CONTENT.getStatusCode());

    }

    @Test
    public void goldenTest() {

        Field f = new Field(5, 5);

        // create field
        f = given()
            .contentType(JSON)
            .body(f)
        .when()
            .post("fields")
        .then()
            .extract()
            .body()
            .as(Field.class);


        Rover r1 = new Rover();
        r1.setX(1);
        r1.setY(2);
        r1.setDirection(N);

        // create rover 1
        r1 = given()
            .contentType(JSON)
            .body(r1)
        .when()
            .post("fields/{id}/rovers", f.getId())
        .then()
            .extract()
            .body()
            .as(Rover.class);

        for (char cmd : "LMLMLMLMM".toCharArray()) {
            post("fields/{id}/rovers/{rid}/{cmd}", f.getId(), r1.getId(), cmd);
        }

        get("fields/{id}/rovers/{rid}", f.getId(), r1.getId()).then()
            .body("x", is(1))
            .body("y", is(3))
            .body("direction", is(N.name()));

        Rover r2 = new Rover();
        r2.setX(3);
        r2.setY(3);
        r2.setDirection(E);

        // create rover 2
        r2 = given()
            .contentType(JSON)
            .body(r2)
        .when()
            .post("fields/{id}/rovers", f.getId())
        .then()
            .extract()
            .body()
            .as(Rover.class);

        for (char cmd : "MMRMMRMRRM".toCharArray()) {
            post("fields/{id}/rovers/{rid}/{cmd}", f.getId(), r2.getId(), cmd);
        }

        get("fields/{id}/rovers/{rid}", f.getId(), r2.getId()).then()
            .body("x", is(5))
            .body("y", is(1))
            .body("direction", is(E.name()));

    }

    @Test
    public void testRoverPreconditions() {

        Field f = new Field(5, 5);

        f = given()
            .contentType(JSON)
            .body(f)
        .when()
            .post("fields")
        .then()
            .extract()
            .body()
            .as(Field.class);

        given()
            .contentType(JSON)
            .body(new Rover())
        .when()
            .post("fields/{id}/rovers", f.getId())
        .then()
            .statusCode(BAD_REQUEST.getStatusCode());

        given()
            .contentType(JSON)
            .body(new Rover(null, 6, 6, N))
        .when()
            .post("fields/{id}/rovers", f.getId())
        .then()
            .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());

    }

}