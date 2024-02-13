package dev.jeffersonfreitas.shopapi.repository;

import dev.jeffersonfreitas.shopapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product	findByIdentifier(String	identifier);
}
