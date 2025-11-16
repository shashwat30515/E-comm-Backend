package com.example.ecom_proj.repo;

import com.example.ecom_proj.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final DynamoDbClient dynamoDbClient;

    private DynamoDbTable<Product> getTable() {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        return enhancedClient.table("Products", TableSchema.fromBean(Product.class));
    }

    // CREATE / UPDATE
    public void save(Product product) {
        getTable().putItem(product);
    }

    // READ by ID
    public Product findById(Long id) {
        return getTable().getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    // READ all
    public List<Product> findAll() {
        return StreamSupport.stream(getTable().scan().items().spliterator(), false)
                .collect(Collectors.toList());
    }

    // DELETE
    public void delete(Long id) {
        getTable().deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
