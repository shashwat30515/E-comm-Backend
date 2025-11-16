package com.example.ecom_proj.repo;

import com.example.ecom_proj.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface productrepo extends JpaRepository<product,Integer> {

//    @Query("SELECT p FROM Product p WHERE " +
//            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//
//
//
//    List<product> searchProducts(  @Param("keyword") String Keyword);
}

