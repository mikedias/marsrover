package org.nasa.marsrover.api;

import com.jayway.restassured.RestAssured;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.nasa.marsrover.storage.Storage;

/**
 * @author Mike Dias
 */
public abstract class ApiTest {

    protected HttpServer server;

    @Before
    public void setUp() throws Exception {
        server = RestApp.startServer();
        RestAssured.baseURI = RestApp.BASE_URI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @After
    public void tearDown() throws Exception {
        Storage.getFieldStorage().clear();
        server.shutdownNow();
    }

}
