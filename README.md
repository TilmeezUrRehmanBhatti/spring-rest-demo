## Spring REST - Overview , JSON Data Binding

REST: REpresentational State Transfer
+ Lightweight approach for communication between applications
+ Rest is language independent
+ The **client** application can use **ANY** programming language
+ The **server** application can use **ANY** programming language


**What is the data format?**
+ REST application can use any data format
+ Commonly see XML and JSON
+ JSON is most popular and modern
    + JavaScript Object Notation

**What is JSON?**

+ JavaScript Object Notation
+ Lightweight data format for storing and exchanging data ... plain text
+ Language independent ... not just for **JavaScript**
+ Can use with any programming language: **Java, C#, Python** etc ...

> JSON is just plain text data

**Simple JSON Example**

+ Curley braces define objects in JSON
+ Object members are name / value pairs
    + Delimited by colons

+ Name is **always** in double-quotes

```JSON
{
  "id": 14,
  "firstName": "Mario",
  "lastName": "Rossi",
  "active": true
  "course": null
}
```
+ `"id"` in Name
+ `14` is Value

**JSON Values**

+ Number: no quotes
+ String: in double quotes
+ Boolean: **true, false**
+ Nested JSON object
+ Array
+ **null**

**Nested JSON Objects**

```JSON
{
  "id": 14,
  "firstName": "Mario",
  "lastName": "Rossi",
  "active": true
  "address": {
              "street": "Tibi str 6",
              "city": "Duisburg",
              "postalCode": 47051,
              "country": "DE"
            }
}
```


**JSON Arrays**

```JSON
{
  "id": 14,
  "firstName": "Mario",
  "lastName": "Rossi",
  "languages": ["java", "C#", "Python", "Javascript"]
}
```
+ Arrays use square brackets [...]

**Java JSON Data Binding**

+ Data binding is the process of converting JSON data to a Java POJO

<img src="https://user-images.githubusercontent.com/80107049/192099115-d689e2e7-7c38-42ff-ab30-7f1d75e2f53a.png" width="500" />



> Also known as, Mapping , Serialization/Deserialization, Marshalling/Unmarshalling

**JSON Data Binding with Jackson**

+ Spring uses the **Jackson Project** behind the scenes
+ Jackson handles data binding between JSON and Java POJO
+ Details on Jackson Project: https://github.com/FasterXML/jackson
+ Jackson supports XML and JSON
+ Jackson Data Binding API
    + Package:**come.fasterxml.jackson.databind**

+ Maven Dependency

```XML
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.9.0</version>
</dependency>
```
+ By default, Jackson will call appropriate getter/setter method

**JSON to Java POJO**

```JAVA
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
  
  public static void main(String[] args) throws Exception {
    
    // create object mapper
    ObjectMapper mapper = new ObjectMapper();
    
    // read JSON from file and map/convert to Java POJO
    Student myStudent = mapper.readValue(new File("data/sample.json"), Student.class);
    
    // also print individual items
    System.out.println("First name = " + myStudent.getFirstName());
    System.out.Println("Last name = " + myStudnet.getLastName());
   
  }
}   
```

+ `"data/sample.json"` Rad data from this file
+ `Student.class` Create an instance of this class and populate it









**Java POJO to JSON**
```JAVA
// create object mapper
ObjectMapper mapper = new ObjectMapper();

// read JSON file and map/convert to Java POJO
Student myStudent = mapper.readValue(new File("data/sample.json"), Student.class);

...
  
// now write JSON to output file
mapper.enable(SerializationFeature.INDENT_OUTPUT);
mapper.writerValue(now File("data/outout.json"), myStudent);
```
+ Jackson calls the getter methods on Student POJO to create JSON output file


**Spring and Jackson Support**

+ When building Spring REST applications
+ Spring will automatically handle Jackson Integration
+ JSON data being passed to REST controller is converted to POJO
+ Java object being returned from REST controller is converted to JSON




## Spring REST - Create a Spring REST Controller

**Spring REST - HTTP Overview**

+ Most common use of REST is over HTTP
+ Leverage HTTP methods for CURD operations

| HTTP Method | CRUD Operation                           |
| ----------- | ---------------------------------------- |
| **POST**    | Create a new entity                      |
| **GET**     | Read a list of entities or single entity |
| **PUT**     | Update an existing entity                |
| **DELETE**  | Delete an existing entity                |


**HTTP Messages**

<img src="https://user-images.githubusercontent.com/80107049/192099141-9c347d97-21ea-4c7b-94c7-578ceab9e173.png" width="500" />


**HTTP Request Message**

<img src="https://user-images.githubusercontent.com/80107049/192099155-ddc261d2-fd38-43f6-ae50-5b0c12d5806a.png" width="500" />


+ Request line: the HTTP command
+ Header variables: request metadata
+ Message body: contents of message

**HTTP Response Message**

<img src="https://user-images.githubusercontent.com/80107049/192099161-4b1cbfff-0be5-4829-ad87-9bd6b8935440.png" width="500" />



+ Response line: server protocol and status code
+ Header variables: response metadata
+ Message body: contents of message

**HTTP Response - Status Codes**

| Code Range | Description   |
| ---------- | ------------- |
| 100 - 199  | Informational |
| 200 - 299  | Successful    |
| 300 - 399  | Redirection   |
| 400 - 499  | Client error  |
| 500 - 599  | Server error  |

**e.g**
+ 401 Authentication Required
+ 404 File Not Found
+ 500 Internal Server Error

**MIME Content Types**

+ The message formate is described by MIME content type
    + **M**ultipurpose **I**nternet **M**ail-**E**xtension

+ Basic Syntax: type/sub-type
+ Examples
    + text/html, text/plain
    + application/json , application/xml ,...


**Client Tool**

+ We need a client tool
+ Send HTTP request to the REST Web Service / API
+ Plenty of tolls available: curl, Postman, etc ...


**Spring REST Support**

+ Spring Web MVC provides support for Spring REST
+ New annotation **@RestController**
    + Extension of @Controller
    + Handles REST requests and responses

+ Spring REST will also automatically convert Java POJOs to JSON
    + As long as the Jackson project is on classpath or pom.xml

**Spring REST Controller**

```JAVA
@RestController
@RequestMapping("/test")
public class DemoRestController {
  
  @GetMapping("/hello")
  public String sayHello() {
    return "Hello World!";
  }
  
}
```
+ `@RestController` Adds REST support
+ `@GetMapping("/hello")` Access the REST endpoint at /test/hello
+ `return "Hello World!";` Returns content to client

**Web Browser vs Postman**
+ For simple REST testing for GET requests
    + Web Browser and Postman are similar

+ However, for advanced REST testing:POST,PUT etc ...
    + Postman has much better support
    + POSTing JSON data, setting content type
    + Passing HTTP request headers, authentication etc...

**Development Process Spring REST controller**

1. Add Maven dependency for Spring MVC and Jackson project
2. Add code for All Java Config: @Configuration
3. Add code for All java Config: Servlet Initializer
4. Create Spring REST Service using @RestController



_Step 1:Add Maven Dependencies_

File:pom.xml
```XML
<!--Add Spring MVC and REST Support-->
<dependency>
  <groupId>org.springframwork</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>...</version>
</dependency>

<!--Add Jackson for JSON converters-->
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>...</version>
</dependency>

<!--Add Servlet support for Spring's AbstractAnnotationConfigDispatcherServletInitializer-->
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>...</version>
</dependency>
```


_Step 2:All Java Config: @Configuration_

File:DemoAppConfig.Java
```JAVA
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.tilmeez.springdemo")
public class DemoAppConfig {

}
```


**Web App Initializer**

+ Spring MVC provides support for web app initialization
+ Make sure your code is automatically detected
+ Your code is used to initialize the servlet container

```JAVA
            AbstractAnnotationConfigDispatcherServletInitializer
```
+ TO DO list
    + Extend this abstract base class
    + Override required methods
    + Specify servlet mapping and location of ypur app config

_Step 3:All Java Config: Servlet Initializer_

File:MySpringMvcDispatcherServletInitializer.java
```JAVA
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DemoAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
```

### Retieve POJOs as JSON

**Convert Java POJO to JSON**
+ Our REST Service will return **LIST\<Student>**
+ Need to convert **List\<Student>** to JSON
+ **Jackson** can help us out with this ...


**Spring and Jackson Support**

+ Spring will automatically handle **Jackson** integration
+ As long as **Jackson** project is on the classpath or pom.xml
+ JSON data being passed to REST controller is converted to JAVA POJO
+ Java POJO being returned form REST controller is converted to JSON


**Development Process**
1. Create Java POJO class for **Student**
2. Create Spring REST Service using **@RestController**

## Spring REST - Using @PathVariable for REST Endpoint

**Path Variables**
+ Retrieve a single student by id
    + `GET` `/api/students/{srudentId}`  Retrieve a single student
        + `{studentId}` Known as a "path variable"
    + /api/students/0
    + /api/students/1
    + /api/students/2



**Development Process**
1. Add request mapping to Spring REST Service
    + Bind path variable to method parameter using @PathVariable


_Step 1:Add Request Mapping_

File:StudentRestController.java
```JAVA
@RestController
@RequestMapping("/api")
public class StudentRestController {
  
  // define endpoint for "/students/{studentId}" - return student at index
  
  @GetMapping("/student/{studentId}")
  public Student getStudent(@PathBariable int studnetId) {
    
    List<Student> theStudents = new ArrayList<>();
    
    // populate theStudents
    ...
    return theStudents.get(studentId);
    
  }
}
```
+ By default variables should match `@GetMapping("/student/{studentId}")` as  `(@PathBariable int studnetId`

## Spring REST - Exception Handling

**Development Process**
1. Create a custom error response class
2. Create a custom exception class
3. Update REST service to throw exception if student not found
4. Add an exception handler method using @ExceptionalHandler


_Step 1:Create custom error response class_

+ The custom error response class will be sent back to client as JSON
+ We will define as Java class (POJO)
+ Jackson will handle converting it to JSON

File:SudentErrorResponse.java
```JAVA
public class StudentErrorResponse {
  
  private int status;
  private String message;
  private long timeStamp;
  
  // constructor 
  
  // getter / setter
}
```


_Step 2:Create custom student exception_

+ The custom student exception will used by our REST service
+ In our code, if we can't find student, then we'll throw an exception
+ Need to define custom student exception class
  + StudentNotFoundException

File:StudentNotFoundException
```JAVA
public class StudentNotFoundException extends RuntimeException {
  
  public StudentNotFoundException(String message) {
    super(message);
  }
  
}
```

_Step 3:Update REST service to throw exception_

File:StudentRestController
```JAVA
@RestController
@RequestMapping("/api")
public class StudentRestController {
  
  @GetMapping("/student/{studentId}")
  public Student getStudent(@PathVariable int studentId) {
    
    // check th student against list size 
    
    if ((student >= theStudents.size())) || (studentId < 0 ) {
      throw new StudentNotFoundException("Student id not found - " + studentId);
    }
    return theStudents.get(studentId);
  }
  ...
}
```


_Step 4:Add exception handler method_

+ Define exception handler method(s) with @ExceptionHandler annotation
+ Exception handler will return a ResponseEntity
+ ResponseEntity is a wrapper for HTTP response object
+ ResponseEntity provides fine-grained control to specify:
  + HTTP status code, HTTP header and Response body


File:StudentController
```JAVA
@RestController
@RequestMapping("/api")
public class StudentRestController {
  
  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
    
    StudentErrorResponse error = new StudentErrorResponse();
    
    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());
    
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);


```

+ `@ExceptionHandler` Exception handler method
+ `StudentErrorResponse` Type of the response body
+ `StudentNotFoundException` Exception type to handle / catch
+ `return new ResponseEntity<>(error, HttpStatus.NOT_FOUND)` error is body , `HttpStatus.NOT_FOUND` Status code"404"

**Global Exception Handling**

+ Exception handler code is only for the specific REST controller
+ Can't be reused by other controllers, (large projects will have multiple controllers=
+ We need **global** exception handler
  + Promotes reuse
  + Centralizes exception handling


**Spring @ControllerAdvice**
+ @ControllerAdvice is similar to an interceptor / filter
+ Pre-process requests to controllers
+ Post-process responses to handle exceptions
+ Prefect for global exception handling

**Development Process**

1. Create new @ControllerAdvice
2. Refactor REST service ... remove exception handling code
3. Add exception handling code to @ControllerAdvice



