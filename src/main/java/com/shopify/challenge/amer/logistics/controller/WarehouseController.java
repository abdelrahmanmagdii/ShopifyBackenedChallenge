package com.shopify.challenge.amer.logistics.controller;

import com.shopify.challenge.amer.logistics.dto.Warehouse;
import com.shopify.challenge.amer.logistics.exception.ApplicationExceptionMessage;
import com.shopify.challenge.amer.logistics.exception.CustomApplicationException;
import com.shopify.challenge.amer.logistics.exception.CustomExceptionMessage;
import com.shopify.challenge.amer.logistics.service.WarehouseService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.BadRequestException;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Autowired(required = false)
    WarehouseService warehouseService;




    @GetMapping(path="/list", consumes = "application/json", produces = "application/json")
    public List<Warehouse> getWarehouses(){
        try {
            return warehouseService.getAllwarehouses();
        }
        catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "WarehouseController", "getWarehouses()");
        }
    }


    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json" )
    public Mono<ResponseEntity<Warehouse>> warehousePosting(@RequestBody(required = true) Warehouse warehouse)
            throws  Exception
    {

        try {
            System.out.println("processWarehouse "+ warehouse);
            warehouseService.saveOrUpdate(warehouse);
            ResponseEntity response =
                    new ResponseEntity(warehouse, HttpStatus.OK);
            Mono<ResponseEntity<Warehouse>> result
                    = Mono.just(response);
            return result;
        }
        catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "WarehouseController", "warehousePosting()");
        }
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json" )
    public Mono<ResponseEntity<Warehouse>> updateWarehouse(@RequestBody(required = true) Warehouse warehouse)
            throws  Exception
    {

        try {
            System.out.println("processWarehouse "+ warehouse);
            Warehouse update = warehouseService.saveOrUpdate(warehouse);
            if(update != null) {
                ResponseEntity<Warehouse> response =
                        new ResponseEntity(update, HttpStatus.OK);
                Mono<ResponseEntity<Warehouse>> result
                        = Mono.just(response);
                return result;
            }else{
                ResponseEntity<Warehouse> response =
                        new ResponseEntity(warehouse, HttpStatus.BAD_REQUEST);
                Mono<ResponseEntity<Warehouse>> result
                        = Mono.just(response);
                return result;
            }
        }
        catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "WarehouseController", "updateWarehouse()");
        }
    }




    @GetMapping(path="/get/{id}", consumes = "application/json", produces = "application/json")
    public Mono<ResponseEntity<Warehouse>> warehousePathVariable(@PathVariable(value = "id", required = true) @Min(1) @Max(10000) int id)
    {
        try {
            System.out.println("processPathMsg " + id);

            Warehouse warehouse;
            warehouse = warehouseService.getWarehouseByID(id);
            if (warehouse != null) {
                ResponseEntity<Warehouse> response =
                        new ResponseEntity(warehouse, HttpStatus.OK);
                Mono<ResponseEntity<Warehouse>> result
                        = Mono.just(response);
                return result;
            } else {
                ResponseEntity<Warehouse> response =
                        new ResponseEntity(Warehouse.builder().id(id).build(), HttpStatus.BAD_REQUEST);
                Mono<ResponseEntity<Warehouse>> result
                        = Mono.just(response);
                return result;
            }
        }
        catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "WarehouseController", "warehousePathVariable()");
        }
    }

    @DeleteMapping(path="/delete/{id}", consumes = "application/json", produces = "text/plain")
    public String warehouseDelete( @PathVariable(value = "id", required = true) @Min(1) @Max(10000) int id)
    {
        try {
            System.out.println("deleting... " + id);
            warehouseService.delete(id);
            System.out.println("Warehouse ID number " + id + " has been deleted");
            return id + " was deleted";
        }
        catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "WarehouseController", "warehouseDelete()");
        }
    }

    @ExceptionHandler({CustomApplicationException.class})
    public ResponseEntity<CustomExceptionMessage> handleCustomApplicationException(final CustomApplicationException ex) {
        CustomExceptionMessage exceptionMessage = new CustomExceptionMessage(ex.getMessage(), ex.getClassName(), ex.getMethodName());

        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<CustomExceptionMessage> handleBadRequestException(final BadRequestException ex) {
        CustomExceptionMessage exceptionMessage = new CustomExceptionMessage(ex.getMessage(), ex.getLocalizedMessage(), "OK1");

        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TypeMismatchException.class})
    public ResponseEntity<CustomExceptionMessage> handleTypeMismatchException(final TypeMismatchException ex) {
        CustomExceptionMessage exceptionMessage = new CustomExceptionMessage(ex.getMessage(), ex.getLocalizedMessage(), "OK2");

        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WebExchangeBindException.class})
    public ResponseEntity<ApplicationExceptionMessage> handleWebExchangeBindException(final WebExchangeBindException ex) {
        try {
            ObjectError error = ex.getBindingResult().getAllErrors().get(0);
            ApplicationExceptionMessage exceptionMessage = new ApplicationExceptionMessage(error.getDefaultMessage());

            return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
        } catch (Exception ex2) {
            ApplicationExceptionMessage exceptionMessage = new ApplicationExceptionMessage("Validation Error");

            return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
        }
    }

}



