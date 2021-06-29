# CodingCallengeE-CommerceAPI
This is my solution for Maha's backend coding challenge, where I built a simple e-commerce API endpoint using Spring Boot.

# Built with
* Maven
* Java 11
* Spring Boot
* Spring Framework
* Hibernate
* JPA
* H2 Embedded Database
* IDE - Intellij

# How to Run
Before running the application, you will need to make sure you are have Maven and Java 11

You can build the project and run the tests by using the following command:
```bash
mvn clean install 
```

Run the application using:
```bash 
mvn spring-boot:run 
```

This is the API endpoint to carry out the checkout request
```bash
http://localhost:8080/checkout
```

# How did I approach it ?
I decided to use the Spring Framework because the organization uses Spring Boot and Java technologies so I decided that was the best technology stack to go with. The task itself was simple as only one API endpoint was needed. Since only a simple checkout action was needed to be carried out, we could go with a clean and simple approach but I decided to create a solution that would allow future changes to be integrated easily. The steps of my design are written below as steps.

### Step 1 
Formulate the schema. There are two entities in the checkout system the **Product** and **Discount**, which is visualized below.

![schema](https://user-images.githubusercontent.com/50911194/123850514-2c995700-d92b-11eb-9ddb-31a9e95d4c48.PNG)

### Step 2
Following the Spring Boot architecture, my project includes the following packapges **Model**, **Repository**,**Service**,**Controller** ,and **DTO**

* Model - This is the domain, has all the entities related to our system
* Repository - Contains the interfaces through which we access and manage the models.
* Service - these are application services, where I call the repositories to perform different tasks (eg: calculating the sum of the checkout products)
* Controller - Rest Resources, this has the RESTful API's (eg: POST /checkout )
* DTO - Data Transfer Objects, to hold objects that will be derialized and deserialized.

## Step 3 - Logic
I took in an Array of Strings from the Requests Body and then called upon the **getTotalBill()** function that creates a Map of the Product Id and the number of occurences. For each entry Set I will then do the calculation for the total sum of each product, in which i will apply the discount for the applicable ammount of products. Finally, we get a total sum which the Post function will return a ResponseEntity with the Bill DTO which has the price and a HttpStatus of 200 O.K.

## Testing and Results
For testing I tested both the controllers and the repository. 

### Repository Testing
I added the Junit 5 in my dependencies. I used assert to test the results and @BeforeEach and @AfterEach to set up initial data for tests and delete them once its over. I tested if the Product and Discount exists.

### Controller Testing
I used the Spring  MVC  test  framework  to  write  and  run integration  tests  that  test controllers. I added annotations @SpringBootTest and @AutoConfigureMockMvc to integrate test classes SpringMockMVC. MockMvc performs full Spring MVC request handling but via mock request and response objects. I tested if the '/checkout' api works and it returns the proper format.

Through testing I was able to fix some minor issues and additionally I tested the API with POSTMAN.

![postman_example](https://user-images.githubusercontent.com/50911194/123850555-3cb13680-d92b-11eb-98cd-898c4367a716.PNG)

# What would I improve ?

Since I did a simple implementation, my api returns price = 0.0, for most types of wrong POST body, this can be expanded by including an **exception** package to return a message with unique messages explaining what the error is. If there were other details/entities to consider I would have gone with a different design and implementation. My function in the Service packages could be fineesed to improve the complexity with the use of better Java classes and code.
