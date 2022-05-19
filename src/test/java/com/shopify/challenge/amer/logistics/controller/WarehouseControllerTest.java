package com.shopify.challenge.amer.logistics.controller;

import com.shopify.challenge.amer.logistics.dto.Warehouse;
import com.shopify.challenge.amer.logistics.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
//@ContextConfiguration
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class WarehouseControllerTest {

    @Autowired
    WarehouseService warehouseService;


    @Test
    void warehousePathVariable() {
            WarehouseController controller = new WarehouseController();
            controller.setWarehouseService(warehouseService);
            WebTestClient.bindToController(controller)
                .build()
                .get()
                .uri("/warehouse/get/13")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                    .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                    .expectBody()
                    .jsonPath("$.id").isNotEmpty()
                    .jsonPath("$.id").isEqualTo("13");

        }
    @Test
    void warehouseGetAll() {
        WarehouseController controller = new WarehouseController();
        controller.setWarehouseService(warehouseService);
        WebTestClient.bindToController(controller)
                .build()
                .get()
                .uri("/warehouse/list")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(13)
                .jsonPath("$[0].name").isNotEmpty();

    }
    @Test
    void warehouseUpdateTest() {
        WarehouseController controller = new WarehouseController();
        controller.setWarehouseService(warehouseService);
        WebTestClient.bindToController(controller)
                .build()
                .put()
                .uri("/warehouse/update")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .body(Mono.just(new Warehouse(13, "warehouse de la canada", "Canada", "Ontario", "C1A 6D6", new ArrayList<>())),
                        Warehouse.class)
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.id").isEqualTo("13")
                .jsonPath("$.name").isEqualTo("warehouse de la canada");
    }
    @Test
    void warehouseAddDeleteTest() {
        WarehouseController controller = new WarehouseController();
        controller.setWarehouseService(warehouseService);
        WebTestClient webTestClient = WebTestClient.bindToController(controller)
                .build();

                webTestClient.post()
                .uri("/warehouse/create")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                        .header("Content-type", "application/json")
                .body(Mono.just(new Warehouse(200, "Narnia warehouse", "Myths and other stuff", "Narnia", "on god", null)),
                        Warehouse.class)
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.id").isEqualTo("200")
                .jsonPath("$.name").isEqualTo("Narnia warehouse");

        webTestClient
                .get()
                .uri("/warehouse/list")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(3);


        webTestClient.delete().uri("/warehouse/delete/200").header("Accept", "text/plain")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful();

        webTestClient
                .get()
                .uri("/warehouse/list")
                .accept(MediaType.APPLICATION_JSON).header("Accept", "application/json")
                .header("Content-type", "application/json")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2);




        //delete so that the number of Warehouses remains the same, so the get all would still work
    }
}

