package com.example.ecom_proj.controller;

import com.example.ecom_proj.model.product;
import com.example.ecom_proj.service.productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class productcontroller {

    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

@Autowired
    private productservice service;

    @GetMapping("/products")
    public ResponseEntity<List <product>> getAllproducts(){

        return new ResponseEntity<>(service.getAllproducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<product> getproduct(@PathVariable int id){
        product product = service.getproductByid(id);
         if(product != null)
             return  new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/product")
    public ResponseEntity<?> addproduct(@RequestPart product product,
                                              @RequestPart MultipartFile imageFile){

  try {
      System.out.println(product);
      product product1 = service.addproduct(product, imageFile);
      return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

      }

  }
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id) {
        product product = service.getproductByid(id);

        if (product == null || product.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }

        String type = product.getImageType();
        MediaType mediaType = (type != null) ? MediaType.valueOf(type) : MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(product.getImageData());
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateproduct(@PathVariable int id ,@RequestPart product product,@RequestPart MultipartFile imageFile){
        product product1 = null;
        try {
            product1 = service.updateproduct(id,product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
        if(product1 !=null)
        return new ResponseEntity<>("Updated",HttpStatus.OK);
    else
        return new ResponseEntity<>("Failed to Updated",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteproduct(@PathVariable int id){
        product product = service.getproductByid(id);
        if(product != null) {
            service.deleteproduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }
//    @GetMapping("/products/search")
//    public ResponseEntity<List<product>> searchProduct(@RequestParam("keyword") String Keyword){
//    System.out.println("search with keyword"+Keyword);
//        List<product> products= service.searchproduct(Keyword);
//    return new ResponseEntity<>(products, HttpStatus.OK);
//    }





}


