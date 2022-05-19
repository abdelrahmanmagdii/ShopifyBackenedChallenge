package com.shopify.challenge.amer.logistics.controller;

import com.shopify.challenge.amer.logistics.dto.Product;
import com.shopify.challenge.amer.logistics.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
//@ContextConfiguration
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductService productService;


    @Test
    void productPathVariable() {
            ProductController controller = new ProductController();
            controller.setProductService(productService);
            WebTestClient.bindToController(controller)
                .build()
                .get()
                .uri("/product/get/13")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                    .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                    .expectBody()
                    .jsonPath("$.sku").isNotEmpty()
                    .jsonPath("$.sku").isEqualTo("13");

        }
    @Test
    void productGetAll() {
        ProductController controller = new ProductController();
        controller.setProductService(productService);
        WebTestClient.bindToController(controller)
                .build()
                .get()
                .uri("/product/list")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(4)
                .jsonPath("$[0].sku").isEqualTo(11)
                .jsonPath("$[0].name").isNotEmpty();

    }
    @Test
    void productUpdateTest() {
        ProductController controller = new ProductController();
        controller.setProductService(productService);
        WebTestClient.bindToController(controller)
                .build()
                .put()
                .uri("/product/update")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .body(Mono.just(new Product(13, "ZYX Laser deskjet 5000 lp", "Laser made for pets, have them go crazy", "Electronics", new ArrayList<>())),
                        Product.class)
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.sku").isNotEmpty()
                .jsonPath("$.sku").isEqualTo("13")
                .jsonPath("$.name").isEqualTo("ZYX Laser deskjet 5000 lp");
    }

    @Test
    void productPostTest() {
        ProductController controller = new ProductController();
        controller.setProductService(productService);
        WebTestClient webTestClient = WebTestClient.bindToController(controller)
                .build();

                webTestClient.post()
                .uri("/product/create")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                        .header("Content-type", "application/json")
                .body(Mono.just(new Product(205, "kendrick lamar", "best rapper ever ", "goats", new ArrayList<>())),
                        Product.class)
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.sku").isNotEmpty()
                .jsonPath("$.sku").isEqualTo("205")
                .jsonPath("$.name").isEqualTo("kendrick lamar");

        webTestClient
                .get()
                .uri("/product/list")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(5);


        webTestClient.delete().uri("/product/delete/205")
                .header("Accept", "text/plain")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful();

        webTestClient
                .get()
                .uri("/product/list")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(4);




        //delete so that the number of product remains the same, so the get all would still work
    }


//    @Test
//    void productPathVariableWrong() {
//        ProductController controller = new ProductController();
//        controller.setProductService(productService);
//        WebTestClient.bindToController(controller)
//                .build()
//                .get()
//                .uri("/product/products/143")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange().expectStatus().is2xxSuccessful()
//                .expectBody()
//                .jsonPath("$.sku").isNotEmpty()
//                .jsonPath("$.sku").isEqualTo("143");
//
//    }
}

