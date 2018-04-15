package io.falcon.challenge.repository;

import io.falcon.challenge.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);

    List<Product> findAll();

}
