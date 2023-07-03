# Introduction
Movie-Theater is a Spring Boot application and use H2 in memory database.

* MovieTheaterApplication is the main class and is annotated with @SpringBootApplication.
* TheatherController provides a REST API interface to reserve tickets which calls ReservationService.
* Reservation service  calls PricingService to calculate the applicable discount and create Payment entities.
* Reservation service uses Java Persistence API (ResevationRepository) to create new Reservations.
* TheaterService interface exposes methods to display showings in Simple Text format and JSON format.
* Discount related configurations are defined in TheaterConfig. Configs are injected by Spring and can be changed without rebuilding or re-deploying the application 
* src/test folder contains testcases that use Spring and Mocito framework to test the functionality.  @SpringBootTest and @WebMvcTest are used to create the Spring Context and inject the components.

## `movie-theater`

### Features
* Customer can make a reservation for the movie
  And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
  
* System can display movie schedule with simple text format and JSON format

# Instruction

'mvn clean install' will build the application and run the tests. 
Application can be started using one of the following commands

* ./mvnw spring-boot:run 
* java -jar target/movie-theater-1.0.1-RELEASE.jar
