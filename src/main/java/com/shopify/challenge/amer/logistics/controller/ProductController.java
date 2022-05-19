package com.shopify.challenge.amer.logistics.controller;

import com.shopify.challenge.amer.logistics.dto.Product;
//import com.shopify.challenge.amer.logistics.dto.ProductWarehouse;
import com.shopify.challenge.amer.logistics.exception.ApplicationExceptionMessage;
import com.shopify.challenge.amer.logistics.exception.CustomApplicationException;
import com.shopify.challenge.amer.logistics.exception.CustomExceptionMessage;
import com.shopify.challenge.amer.logistics.service.ProductService;
//import com.shopify.challenge.amer.logistics.service.ProductWarehouseService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.BadRequestException;
import java.util.List;

@RestController
@RequestMapping(path="/product")
public class ProductController {

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired(required = false)
    ProductService productService;


    @GetMapping(path = "/list", consumes = "application/json", produces = "application/json")
    public List<Product> getProduct() {
        try {
            return productService.getAllProducts();
        } catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "ProductController", "getProduct()");
        }
    }


    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public Mono<ResponseEntity<Product>> productPosting(@RequestBody(required = true) @Valid Product product)
            throws Exception {

        try {
            System.out.println("processProduct " + product);
            productService.saveOrUpdate(product);
            ResponseEntity response =
                    new ResponseEntity(product, HttpStatus.OK);
            Mono<ResponseEntity<Product>> result
                    = Mono.just(response);
            return result;
        } catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "ProductController", "productPosting()");
        }
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public Mono<ResponseEntity<Product>> updateProduct(@RequestBody(required = true) Product product)
            throws Exception {

        try {
            System.out.println("processProduct " + product);

            Product update = productService.saveOrUpdate(product);
            if (update != null) {
                ResponseEntity<Product> response =
                        new ResponseEntity(update, HttpStatus.OK);
                Mono<ResponseEntity<Product>> result
                        = Mono.just(response);
                return result;
            } else {
                ResponseEntity<Product> response =
                        new ResponseEntity(product, HttpStatus.BAD_REQUEST);
                Mono<ResponseEntity<Product>> result
                        = Mono.just(response);
                return result;
            }
        } catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "ProductController", "updateProduct()");
        }
    }


    @GetMapping(path = "/get/{sku}", consumes = "application/json", produces = "application/json")
    public Mono<ResponseEntity<Product>> productPathVariable(@PathVariable(value = "sku", required = true) @Min(1) @Max(10000)  int sku)
    {
       try {
           System.out.println("processPathMsg " + sku);

           Product product;
           product = productService.getProductByID(sku);
           if (product != null) {
               ResponseEntity<Product> response =
                       new ResponseEntity(product, HttpStatus.OK);
               Mono<ResponseEntity<Product>> result
                       = Mono.just(response);
               return result;
           } else {
               ResponseEntity<Product> response =
                       new ResponseEntity(Product.builder().SKU(sku).build(), HttpStatus.BAD_REQUEST);
               Mono<ResponseEntity<Product>> result
                       = Mono.just(response);
               return result;
           }
       }
       catch (Exception ex) {
           throw new CustomApplicationException(ex.getMessage(), "ProductController", "productPathVariable()");
       }
    }

    @DeleteMapping(value = "/delete/{sku}", consumes = "application/json", produces = "text/plain")
    public String productDelete(@PathVariable(value = "sku", required = true) @Min(1) @Max(10000) int sku) {
        try {
            System.out.println("deleting.. " + sku);
            productService.delete(sku);
            System.out.println("Product of SKU number " + sku + " has been deleted");
            return sku + " was deleted";
        }catch (Exception ex) {
            throw new CustomApplicationException(ex.getMessage(), "ProductController", "productDelete()");
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

