package com.suplier.super_suplier.rest;

import com.suplier.super_suplier.model.SupplierDTO;
import com.suplier.super_suplier.service.SupplierService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(final SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplier(@PathVariable final UUID id) {
        return ResponseEntity.ok(supplierService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createSupplier(@RequestBody @Valid final SupplierDTO supplierDTO) {
        return new ResponseEntity<>(supplierService.create(supplierDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSupplier(@PathVariable final UUID id,
            @RequestBody @Valid final SupplierDTO supplierDTO) {
        supplierService.update(id, supplierDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable final UUID id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
