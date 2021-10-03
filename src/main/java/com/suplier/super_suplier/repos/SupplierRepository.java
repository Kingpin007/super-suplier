package com.suplier.super_suplier.repos;

import com.suplier.super_suplier.domain.Supplier;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
