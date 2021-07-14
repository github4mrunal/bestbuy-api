package com.bestbuy.categoriesinfo;

import com.bestbuy.CategoriesPojo;
import com.bestbuy.ProductsPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesTest extends TestBase {
    @Test
    public void getAllCategories() {
        Response response =
                given()
                        .when()
                        .get("http://localhost:3030/categories");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getCategoriesById() {
        Response response =
                given()
                        .pathParam("id", "pcmcat748300579354")
                        .when()
                        .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createNewProductInCategories() {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName("Glenfiddich");
        categoriesPojo.setId("Whisky");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void deleteProductInCategories() {
        Response response =
                given()
                        .pathParam("id", "abcat0020004")
                        .when()
                        .delete("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }

    @Test
    public void updateProductWithPatch() {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName("Golden Anniversaries");

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(categoriesPojo)
                        .when()
                        .patch("/pcmcat84000050001");
        response.then().statusCode(200);
        response.prettyPrint();

    }
}

