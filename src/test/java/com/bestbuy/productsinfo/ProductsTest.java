package com.bestbuy.productsinfo;

import com.bestbuy.ProductsPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class ProductsTest extends TestBase {


@Test
    public void getAllProductsInfo(){
    Response response =
            given()
            .when()
            .get("http://localhost:3030/products");
    response.then().statusCode(200);
    response.prettyPrint();
}

@Test
    public void getProductById(){
    Response response =
            given()
                    .pathParam("id", 43900)
                    .when()
                    .get("http://localhost:3030/products/{id}");
    response.then().statusCode(200);
    response.prettyPrint();
}
    @Test
    public void addNewProduct() {

        ProductsPojo productspojo = new ProductsPojo();
        productspojo.setName("PS4");
        productspojo.setType("Gaming");
        productspojo.setPrice(499);
        productspojo.setShipping(5);
        productspojo.setUpc("Next Day");
        productspojo.setDescription("Gaming Console");
        productspojo.setManufacturer("Sony");
        productspojo.setModel("V4");
        productspojo.setUrl("sony@gmail.com");
        productspojo.setImage("SONY PS4");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(productspojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();

    }
    @Test
    public void updateProductWithPatch() {
        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setShipping(10);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(productsPojo)
                        .when()
                        .patch("/43900");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void deleteProductInfo() {

        Response response =
                given()
                        .pathParam("id", "150115")
                        .when()
                        .delete("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }

    }

