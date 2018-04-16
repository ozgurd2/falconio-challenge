# Falcon.io Technical Challenge
Falcon.io

Technology stack : Kafka, Postgresql, WebSocket, Stomp.js
## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. Docker Toolbox
2. java 8 or higher

After installing Docker, you should be able to run the following command on root folder of project to install development environment.


    docker-compose up

Run the following commands in separate terminal to run application.

    ./mvnw spring-boot:run

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Architecture 
* DummyProductJsonController#createProduct : A REST endpoint is taking a dummy JSON input 
  dummy json : io.falcon.challenge.dto.DummyProductDTO
  
  sample call : 


    curl -X POST http://localhost:8080/products -H 'content-type: application/json' -d '{"productName": "testName","productDescription": "testDescription"}'

* ProductReceiverConsumer : A Consumer is running in the application, taking the freshly received message and persists it in a Postgresql database
  entity : io.falcon.challenge.entity.Product
    
* DummyProductJsonController#getProducts :A REST endpoint is implemented for retrieving all the messages persisted in JSON format from the database
   
   sample call: 


    curl -X GET http://localhost:8080/products

* WebSocketBroadcastService : A Service to broadcasting of connected clients
* [http://localhost:8080](http://localhost:8080) a simple index.html for notifications

## Testing

To launch your application's tests, run:

    ./mvnw clean test

