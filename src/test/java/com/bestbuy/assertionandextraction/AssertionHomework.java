package com.bestbuy.assertionandextraction;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AssertionHomework {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then();
    }

    //http://localhost:3030/stores
//1. Verify the if the total is equal to 1561
    @Test
    public void test01() {
        response.body("total", equalTo(1561));
    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void test02() {
        response.body("limit", equalTo(10));
    }

    // 3. Check the single ‘Name’ in the Array list (Square83215)
    @Test
    public void test03() {
        response.body("data.name", hasItem("Roseville"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Square83215, Southridge, Green Bay)
    @Test
    public void test04() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));

    }

    //5. Verify the storied inside storeservices of the third store of second services
    @Test
    public void test05() {
        response.body("data[2].services[1].storeservices", hasKey("storeId"));
    }

    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Southridge
    @Test
    public void test06() {
        response.body("data[0].services[3]", hasKey("createdAt"));
    }

    //7. Verify the state = IA of third store
    @Test
    public void test07() {
        response.body("data[2].state", equalTo("MN"));
    }
    //8. Verify the name = Green Bay of 9th store
    @Test
    public void test08(){
        response.body("data[8].name",equalTo("Oakdale"));
    }
    //9. Verify the storeId = 23 for the 6th store
    @Test
    public void test09(){
        response.body("data[5].id",equalTo(12));
    }
    //10. Verify the serviceId = 14 for the 7th store
    @Test
    public void test10(){
        response.body("data[6].services[7].storeservices.serviceId",equalTo(15));
    }

}
