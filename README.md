# Kala Six Stone Game API

### [Game rules are explained in wikipedia](https://en.wikipedia.org/wiki/Kalah)
For further reference, please consider the following sections:

* [Kalah Game on Github](https://github.com/sandeepsai39/kalahgame)

### Guides
The following guides illustrate how to use some features concretely:
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Rest API Documentation for Kalah Game  
The OpenAPP/Swagger documentation available on https://github.com/sandeepsai39/kalahgame/kalahsixstone/src/main/resources/openapi.yaml 

### Game Specification and Design
#### Endpoint design specification
1. Creation of the game should be possible with the command:
   curl --header "Content-Type: application/json" \
   --request POST http://server:port/games 


       Sample Response:
       HTTP code: 201
       Response Body: { "id": "1234", "uri": "http://<host>:<port>/games/1234" }
       id: unique identifier of a game
       url: link to the game created
   
       Sample Error Response:
       HTTP code: 500
       Response Body : {"errorCode": "500", "Internal Server Error"}

   * New Game is Created and stores the game details in      
     "kalahgame" table and returns new Game ID.
   * For Each Game ID , the pits and stones details
     are stored in "kalahstatus" table.
      
      Note: For DB details(For POC using In memory H2 DB ) please refer 
      https://github.com/sandeepsai39/kalahgame/kalahsixstone/src/main/resources/schema.sql
   * Once Game is created successfully, refer second step. 

2. Make a move:
   curl --header "Content-Type: application/json" \
   --request PUT \
   http://host:port/games/{gameId}/pits/{pitId}
   
       Request:
       gameId: a unique identifier of a game
       pitId: id of the pit selected to make a move. Pits are numbered from 1 to 14 where 7 and 14 are the kalah (or house)
       of each player
   
       Response:
       HTTP code: 200
       Response Body:
       {"id":"1234","url":"http://<host>:<port>/games/1234","status":{"1":"4","2":"4","3":"4","4":"4","5":"4","6":"4","7":"0","8":"4","
       9":"4","10":"4","11":"4","12":"4","13":"4","14":"0"}}
        status: json object key-value, where key is the pitId and value is the number of stones in the pit
       
       Error Response Body: With wrong game Id as parameter
       HTTP Code 404,
       {"erorCode": "404", "errorMessage": "Game not Created""}
