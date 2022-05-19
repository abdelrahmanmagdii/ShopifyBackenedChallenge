package com.shopify.challenge.amer.cucumber.runner;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class StepDefinition {

//    @Before
//    public void setup() {
//        RestAssured.reset();
//        RestAssured.baseURI = "http://127.0.0.1";
//        RestAssured.port = 8313;
//    }

    String msg;
    @Given("message to send {string}")
    public void message_to_send(String msg) {
        this.msg = msg;
    }

    String prefix;
    @Given("with prefix {string}")
    public void with_prefix(String prefix) {
        this.prefix = prefix;
    }

    Response response;
    @When("request is sent via post")
    public void request_is_sent_via_post() {
//        RequestSpecification request = RestAssured.given().baseUri("http://127.0.0.1:8313")
//                .basePath("/processMsg")
//                .port(8313)
//                .accept("application/json")
//                .contentType("application/json");

        try {
             RequestSpecification request = RestAssured.given().baseUri("http://127.0.0.1:8080")
                .basePath("/message/processMsg")
                .port(8080)
                .accept("application/json")
                .contentType("application/json");
//            RequestSpecification request= new RequestSpecBuilder()
//                    .setBaseUri("http://127.0.0.1")
//                    .setBasePath("/processMsg")
//                    .setPort(8080)
//                    .setAccept("application/json")
//                    .build();
//            this.response = request.body("{    \"msg\" : \"" + msg + "\"  }").post("processMsg");
            this.response = request.body("{    \"message\" : \"" + msg + "\", \"prefix\" : \"" +prefix+
                    "\"  }").post();
            System.out.println("OK");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Then("status_code equals {int}")
    public void status_code_equals(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("response message equals {string}")
    public void response_message_equals(String expectedMsg) {
        String msg2 = response.jsonPath().getString("message");
        Assert.assertEquals(expectedMsg, msg2);
    }

    // Product get
    String sku;
    @Given("{string} of a product")
    public void of_a_product(String sku) {
        this.sku = sku;
    }
    @When("request to get the product")
    public void request_to_get_the_product() {
        RequestSpecification request = RestAssured.given().baseUri("http://127.0.0.1:8080")
                .basePath("/product/get/"+this.sku)
                .port(8080)
                .accept("application/json")
                .contentType("application/json");
        this.response = request.get();
    }
    @Then("product name is {string}")
    public void product_name_is(String expectedName) {
        String name = response.jsonPath().getString("name");
        Assert.assertEquals(expectedName, name);
    }


    String wid;
    @Given("{string} of a warehouse")
    public void of_a_warehouse(String id) {
        this.wid = id;
    }
    @When("request to get the warehouse")
    public void request_to_get_the_warehouse() {
        RequestSpecification request = RestAssured.given().baseUri("http://127.0.0.1:8080")
                .basePath("/warehouse/get/"+this.wid)
                .port(8080)
                .accept("application/json")
                .contentType("application/json");
        this.response = request.get();
    }
    @Then("warehouse name is {string}")
    public void warehouse_name_is(String nameExpected) {
        String name = response.jsonPath().getString("name");
        Assert.assertEquals(nameExpected, name);
    }


    @When("request to get the product_warehouse")
    public void request_to_get_the_product_warehouse() {
        RequestSpecification request = RestAssured.given().baseUri("http://127.0.0.1:8080")
                .basePath("/product_warehouse/get/"+this.sku+"/"+this.wid)
                .port(8080)
                .accept("application/json")
                .contentType("application/json");
        this.response = request.get();
    }
    @Then("product_warehouse quantity is {string}")
    public void product_warehouse_quantity_is(String quantityExpected) {
        String quantity = response.jsonPath().getString("productQuantity");
        Assert.assertEquals(quantityExpected, quantity);
    }

}
