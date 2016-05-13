# Mars Rover Project

Exploring Mars with Java! That is what they mean by **write once, run anywhere**!!! :P

## Compile

* Install JDK 8+ and Maven 3+
* Run `mvn clean install`

## Run over Command Line Interface

* Run `java -jar target/marsrover-cli.jar`
* Paste the specification inputs and see the expected results! :)

## Run over REST API

* Run `java -jar target/marsrover-api.jar`
* This will start up a server in `http://localhost:8080/api` with the following resources:

#### Fields
* **GET**  `/fields`
* **POST** `/fields`
* **GET** `/fields/{id}`
* **PUT** `/fields/{id}`
* **DELETE** `/fields/{id}`

#### Rovers
* **GET**  `/fields/{id}/rovers`
* **POST** `/fields/{id}/rovers`
* **GET** `/fields/{id}/rovers/{rid}`
* **PUT** `/fields/{id}/rovers/{rid}`
* **DELETE** `/fields/{id}/rovers/{rid}`
* **POST** `/fields/{id}/rovers/{rid}/L`
* **POST** `/fields/{id}/rovers/{rid}/R`
* **POST** `/fields/{id}/rovers/{rid}/M`


In order to simplify, just import this [Postman Collection](https://www.getpostman.com/collections/921705c194dfd379fffc)


