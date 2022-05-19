package com.shopify.challenge.amer.logistics.controller;

import com.shopify.challenge.amer.logistics.dto.Product;
import com.shopify.challenge.amer.logistics.dto.ProductWarehouse;
import com.shopify.challenge.amer.logistics.dto.ProductWarehouseKey;
import com.shopify.challenge.amer.logistics.dto.Warehouse;
import com.shopify.challenge.amer.logistics.exception.CustomApplicationException;
import com.shopify.challenge.amer.logistics.model.ProductWarehouseModel;
import com.shopify.challenge.amer.logistics.service.ProductWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/product_warehouse")
public class ProductWarehouseController {

    public void setWarehouseService(ProductWarehouseService productWarehouseService) {
        this.productWarehouseService = productWarehouseService;
    }

    @Autowired(required = false)
    ProductWarehouseService productWarehouseService;




    @GetMapping("/list")
    public List<ProductWarehouse> getProductWarehouses(){return productWarehouseService.getAllProductWarehouses(); }


    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json" )
    public Mono<ResponseEntity<ProductWarehouse>> productWarehousePosting(@RequestBody(required = true) ProductWarehouseModel productWarehouseModel)
            throws  Exception
    {

        try {
            System.out.println("processProductWarehouse "+ productWarehouseModel);
            Product product = Product.builder().SKU(productWarehouseModel.getProductSku()).build();
            Warehouse warehouse = Warehouse.builder().id(productWarehouseModel.getWarehouseId()).build();
            ProductWarehouse productWarehouse = ProductWarehouse.builder()
                    .productQuantity(productWarehouseModel.getQuantity())
                    .key(new ProductWarehouseKey(product, warehouse))
                    .build();


            productWarehouseService.saveOrUpdate(productWarehouse);
            ResponseEntity response =
                    new ResponseEntity(productWarehouse, HttpStatus.OK);
            Mono<ResponseEntity<ProductWarehouse>> result
                    = Mono.just(response);
            return result;
        }
        catch (Exception ex)
        {
            throw new CustomApplicationException(ex.getMessage(), "ProductWarehouseController", "productWarehousePosting");
        }
    }

        @GetMapping("/get/{sku}/{id}")
    public Mono<ResponseEntity<ProductWarehouse>> productWarehousePathVariable(
            @PathVariable(value = "sku", required = true)@Min(1) @Max(10000) int pid,
            @PathVariable(value = "id", required = true)@Min(1) @Max(10000) int wid)
    {
        System.out.println("processPathMsg "+ pid+"/"+wid);
        Product product = Product.builder().SKU(pid).build();
        Warehouse warehouse = Warehouse.builder().id(wid).build();
        ProductWarehouse productWarehouse = ProductWarehouse.builder()
                .key(new ProductWarehouseKey(product, warehouse))
                .build();


        ProductWarehouse productWarehouseResult = productWarehouseService.getProductWarehouseByID(productWarehouse);
        if(productWarehouseResult != null) {
            ResponseEntity<ProductWarehouse> response =
                    new ResponseEntity(productWarehouseResult, HttpStatus.OK);
            Mono<ResponseEntity<ProductWarehouse>> result
                    = Mono.just(response);
            return result;
        }else{
            ResponseEntity<ProductWarehouse> response =
                    new ResponseEntity(productWarehouse, HttpStatus.BAD_REQUEST);
            Mono<ResponseEntity<ProductWarehouse>> result
                    = Mono.just(response);
            return result;
        }

    }


//    @PutMapping(path = "/productwarehouseupdate", consumes = "application/json", produces = "application/json" )
//    public Mono<ResponseEntity<ProductWarehouse>> updateProductWarehouse(@RequestBody(required = false) ProductWarehouse productWarehouse)
//            throws  Exception
//    {
//
//        try {
//            System.out.println("processWarehouse "+ productWarehouse);
//            ProductWarehouse update = productWarehouseService.saveOrUpdate(productWarehouse);
//            if(update != null) {
//                ResponseEntity<ProductWarehouse> response =
//                        new ResponseEntity(update, HttpStatus.OK);
//                Mono<ResponseEntity<ProductWarehouse>> result
//                        = Mono.just(response);
//                return result;
//            }else{
//                ResponseEntity<ProductWarehouse> response =
//                        new ResponseEntity(productWarehouse, HttpStatus.BAD_REQUEST);
//                Mono<ResponseEntity<ProductWarehouse>> result
//                        = Mono.just(response);
//                return result;
//            }
//        }
//        catch (Exception ex)
//        {
//            throw ex;
//        }
//    }


//    @DeleteMapping("/delete/{id}")
//    public void warehouseDelete( @PathVariable(value = "id", required = false) int id)
//    {
//        System.out.println("processPathMsg "+ id);
//        warehouseService.delete(id);
//        System.out.println("Warehouse ID number " + id +" has been deleted" );
//    }

}


