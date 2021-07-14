package com.bestbuy.storesinfo;

import com.bestbuy.ServicesPojo;
import com.bestbuy.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.codehaus.groovy.util.ListHashMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class StoresTest extends TestBase {

@Test
public void getAllStoresInfo() {
        Response response =
                given()
                        .when()
                        .get("http://localhost:3030/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getStoresById(){
        Response response =
                given()
                        .pathParam("id", "9")
                        .when()
                        .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void postNewStores(){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Hamleys");
        storesPojo.setType("Toys Store");
        storesPojo.setAddress("Oxford Street");
        storesPojo.setAddress2("West End");
        storesPojo.setCity("London");
        storesPojo.setState("Westminister");
        storesPojo.setZip("WC1");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
    @Test
    public void deleteStoresData() {
        Response response =
                given()
                        .pathParam("id", "4")
                        .when()
                        .delete("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }
    @Test
    public void updateServicesWithPatch() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Best Buy MilkShakes");
        storesPojo.setType("Deserts");


        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(storesPojo)
                        .when()
                        .patch("/6");
        response.then().statusCode(200);
        response.prettyPrint();

    }
 }

