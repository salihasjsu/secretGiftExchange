# secretGiftExchange
Imagine than an extended family(parents,children,grandparents,aunts/uncle,cousins,etc) does a yearly gift exchange, in which early family draws another person's name at random and then buy a gift for a person.
Write a program that will assign each family member to give a gift to. Each family member should receive one gift.
# Contraints
A person cannot give gift to themselves.
A family member cannot be given a gift from the same number from than once every 3 years.


# Rest Services
* Get all Family Members (/members)
* Get a Family Member(/members/{id})
* Add a Family Member (/members)
* Delete a Family Member(/members/{id})
* Update a Family Member(/members/{id})
* Gift Exhcange(/gift_exchange)


# Environment Setup
* Java 1.8
* Gradle 7.0.2

# Run
* gradle clean build
* java -jar build/libs/secretGiftExchange-0.0.1-SNAPSHOT.jar


# Implementation
1.Springboot framework with H2 in-memory database for Secret Gift Exchange. I maintain the history of the family for 3 years in DB and after 3 years i clean the oldest history of the member. Checks are performed to not assing the same member as recipient. Also to check if the recipient has already been assigned in past 3 years.
2.H2 database
3.Exception Handling
4.Mocikito Junit5 Test cases.

# Project Setup
1. Controllers Layer - a layer to maintain buisness logic of an application.
2. Service Layer - a layer to provide set of operations
3. Model - contains pojos 
4. Respoitory Layer - DAO objets to interact with the database.
5. Exception layer - Custom class to throw exceptions.

