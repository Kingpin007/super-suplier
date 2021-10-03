package com.suplier.super_suplier.repos;

import com.suplier.super_suplier.domain.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, UUID> {
}
