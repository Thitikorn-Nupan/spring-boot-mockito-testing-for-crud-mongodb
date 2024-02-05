package com.ttknpdev.learnspringbootcrudmongodb.cmd;

import com.ttknpdev.learnspringbootcrudmongodb.entity.Employee;
import com.ttknpdev.learnspringbootcrudmongodb.log.LogBack;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestClient {
    /*
      RestTemplate is a class within the Spring framework we will understand how to use RestTemplate for invoking REST APIs of different shapes.
      Once the above spring application is up and running,
      test all Spring rest APIs and logging results with the below Spring rest client
      (Making an HTTP GET Request to Obtain the JSON Response)
      to honestly we can use them(this class) instead Postman! ???
    * */
    private final RestTemplate RESTTEMPLATE;
    private HttpHeaders headers;
    private HttpEntity httpEntity;
    private ResponseEntity responseEntity;
    private LogBack logBack;
    private final String POST[] = {
            "http://localhost:8000/api/em/create"
    };
    private final String PUT[] = {
            "http://localhost:8000/api/em/update/{id}"
    };
    private final String GET[] = {
            "http://localhost:8000/api/em/reads"
    };

    private final String DELETE[] = {
            "http://localhost:8000/api/em/delete/{id}"
    };

    public RequestClient() {
        RESTTEMPLATE = new RestTemplate();
        logBack = new LogBack(RequestClient.class);
    }

    private void readsEmployee () {
        // Use HttpHeaders to set the Request Headers.
        headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        // Use HttpEntity to wrap the request object.
        httpEntity = new HttpEntity<>("parameters", headers);
        logBack.log.info("entity stores {}" , httpEntity);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        responseEntity = RESTTEMPLATE.exchange(GET[0] , HttpMethod.GET , httpEntity , String.class);
        logBack.log.info("employees stores {}" , responseEntity);

    }

    private void updateEmployee() {
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", "1"); // key id value 1 for path read/{id}
        Employee updateEmployee = new Employee("tony swicher",(short)26,30000F,"female");
        RESTTEMPLATE.put(PUT[0],updateEmployee,params);
        logBack.log.info("updated successfully!!");
    }

    private void deleteEmployee() {
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", "1"); // key id value 1 for path read/{id}
        RESTTEMPLATE.delete(DELETE[0],params);
        logBack.log.info("deleted successfully!!");
    }
    private void createEmployee() {
        headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON)); // importance for returning like json object
        Employee employee = new Employee();
        employee.setId(1L); // again mongo db dont auto increment
        employee.setAge((short)32);
        employee.setFullname("stone clod");
        employee.setGender("female");
        httpEntity = new HttpEntity<>(employee); // for request to server
        responseEntity = RESTTEMPLATE.exchange(POST[0] , HttpMethod.POST , httpEntity , String.class);
        logBack.log.info("response stores {}" , responseEntity);

    }

    public static void main(String[] args) {
        new RequestClient().deleteEmployee();
    }
}
