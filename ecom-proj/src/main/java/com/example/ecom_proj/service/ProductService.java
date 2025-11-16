package com.example.ecom_proj.service;

import com.example.ecom_proj.model.product;
import com.example.ecom_proj.repo.productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class productservice {
    @Autowired
    private productrepo repo;



    public List<product> getAllproducts() {
        return repo.findAll();
    }
    public  product getproductByid(int id) {
        return repo.findById(id). orElse(null);
    }

    public product addproduct(product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageName(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

         return repo.save(product);
    }

    public product updateproduct(int id, product product, MultipartFile imageFile) throws IOException {
       product.setImageName(imageFile.getOriginalFilename());
       product.setImageName(imageFile.getContentType());
       product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }
    public void deleteproduct(int id) {
        repo.deleteById(id);
    }

//    public List<product> searchproduct(String keyword) {
//        return repo.searchProducts(keyword);
//    }
}

