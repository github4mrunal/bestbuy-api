package com.bestbuy.servicesinfo;

import com.bestbuy.CategoriesPojo;
import com.bestbuy.ServicesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesTest extends TestBase {

    @Test
    public void getAllServicesInfo() {
        Response response =
                given()
                        .when()
                        .get("http://localhost:3030/services");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getServicesById() {
        Response response =
                given()
                        .pathParam("id", "2")
                        .when()
                        .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void postNewServices() {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName("Best Buy Chocolates");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void deleteServices() {
        Response response =
                given()
                        .pathParam("id", "2")
                        .when()
                        .delete("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }
    @Test
    public void updateServicesWithPatch() {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName("Best Buy IceCream");

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(servicesPojo)
                        .when()
                        .patch("/5");
        response.then().statusCode(200);
        response.prettyPrint();

    }
}
