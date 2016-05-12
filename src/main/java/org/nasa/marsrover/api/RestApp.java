package org.nasa.marsrover.api;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * @author Mike Dias
 */
public class RestApp {

    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        ResourceConfig rc = new ResourceConfig().packages("org.nasa");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        System.in.read();
        server.shutdown();
    }

}
