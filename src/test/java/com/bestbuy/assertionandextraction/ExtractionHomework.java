package com.bestbuy.assertionandextraction;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ExtractionHomework {
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
    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of limit is: " + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    // 2) Extract  the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The search query total is : " +total);
        System.out.println("------------------End of Test---------------------------");

    }
    //3 3. Extract the name of 5th store
    @Test
    public void test003(){
        String storename = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the 5th Store is: " + storename);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the names of all the store
    @Test
    public void test004(){
        List<HashMap<String, Object>> allstorename = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all Store are: " + allstorename);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the storeId of all the store
    @Test
    public void test005(){
        List<HashMap<String, Object>> allstoreid = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all Store are: " + allstoreid);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Print the size of the data list
    @Test
    public void test006(){
        List<HashMap<String, Object>> list =  response.extract().path("data");
        int size = list.size();

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the items is: " +size);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the value of the store where store name = Moline
    @Test
    public void test007(){
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='Debenhams'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for Store name is: " +values);
        System.out.println("------------------End of Test---------------------------");
    }
    //8. Get the address of the store where store name = Appleton
    @Test
    public void test008(){
        String storeadd = response.extract().path("data[5].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for Store name is: " +storeadd);
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the services of 8th store
    @Test
    public void test009(){
        List<HashMap<String,Integer >> eighthstore = new ArrayList<>();
               eighthstore = response.extract().path("data[8].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for Store name is: " +eighthstore);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get storeservices of the store where service name = Sony Experience
    @Test
    public void test010(){
        HashMap<String,Integer> storeServices = new HashMap<>();
        storeServices = response.extract().path( "data[0].services[1].storeservices" );
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for Store name is: " +storeServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get all the storeId of all the store
    @Test
    public void test011(){
        List<HashMap<String, Object>> allstoreid = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all Store are: " + allstoreid);
        System.out.println("------------------End of Test---------------------------");
    }
    //12. Get id of all the store
    @Test
    public void test012(){
        List<Integer> storeId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for Store name is: " +storeId);
        System.out.println("------------------End of Test---------------------------");
    }
    //13. Find the store names Where state = WI
    @Test
    public void test013(){
        List<String> name = response.extract().path( "data.findAll{it.state == 'MN'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for Store name is: " +name);
        System.out.println("------------------End of Test---------------------------");
    }
    //14. Find the Total number of services for the store where store name = Cedar Rapids
    public void test014(){
        List<HashMap<String,Integer>> services = response.extract().path("data[4],services");
        int size = services.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for Store name is: " +services);
        System.out.println("------------------End of Test---------------------------");


    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    public void test015(){
            List<HashMap<String,String>> createdAt = response.extract().path( "data.findAll{it.services.name =='Best Buy Mobile'}.createdAt ");
            System.out.println("------------------StartingTest---------------------------"  );
            System.out.println("The search query is: "   +createdAt);
            System.out.println("------------------End of Test---------------------------");
        }

    //16. Find the name of all services Where store name = “Molin”
    @Test
    public void test016(){
        List<HashMap<String,?> > names = response.extract().path( "data[1].services.name" );
        System.out.println("------------------StartingTest---------------------------"  );
        System.out.println("The search query is: "   +names);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test017(){
        List<String> zip = response.extract().path( "data.zip" );

        System.out.println("------------------StartingTest---------------------------"  );
        System.out.println("The search query is: "   +zip);
        System.out.println("------------------End of Test---------------------------");

    }
    //18. Find the zip of store name = Minnetonka
    @Test
    public void test018(){
        String zip = response.extract().path( "data[0].zip" );
        System.out.println("------------------StartingTest---------------------------"  );
        System.out.println("The search query is: "   +zip);
        System.out.println("------------------End of Test---------------------------");

    }
    //19. Find the storeservices details of the service name = Samsung Experience
    @Test
    public void test019(){
        List<HashMap<String,Integer>> storeServices = response.extract().path( "data[2].services.storeServices" );
        System.out.println("------------------StartingTest---------------------------"  );
        System.out.println("The search query is: "   +storeServices);
        System.out.println("------------------End of Test---------------------------");

    }
    //20. Find the lat of all the stores
    @Test
    public void test020(){
        List<Double> lat = response.extract().path( "data.lat" );
        System.out.println("------------------StartingTest---------------------------"  );
        System.out.println("The search query is: "   +lat);
        System.out.println("------------------End of Test---------------------------");

    }

    }






