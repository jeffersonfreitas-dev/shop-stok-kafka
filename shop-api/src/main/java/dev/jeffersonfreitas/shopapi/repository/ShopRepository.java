package dev.jeffersonfreitas.shopapi.repository;

import dev.jeffersonfreitas.shopapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
