package com.suplier.super_suplier.service;

import com.suplier.super_suplier.domain.Supplier;
import com.suplier.super_suplier.model.SupplierDTO;
import com.suplier.super_suplier.repos.SupplierRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(final SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll()
                .stream()
                .map(supplier -> mapToDTO(supplier, new SupplierDTO()))
                .collect(Collectors.toList());
    }

    public SupplierDTO get(final UUID id) {
        return supplierRepository.findById(id)
                .map(supplier -> mapToDTO(supplier, new SupplierDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final SupplierDTO supplierDTO) {
        final Supplier supplier = new Supplier();
        mapToEntity(supplierDTO, supplier);
        return supplierRepository.save(supplier).getId();
    }

    public void update(final UUID id, final SupplierDTO supplierDTO) {
        final Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(supplierDTO, supplier);
        supplierRepository.save(supplier);
    }

    public void delete(final UUID id) {
        supplierRepository.deleteById(id);
    }

    private SupplierDTO mapToDTO(final Supplier supplier, final SupplierDTO supplierDTO) {
        supplierDTO.setId(supplier.getId());
        supplierDTO.setSupplierName(supplier.getSupplierName());
        return supplierDTO;
    }

    private Supplier mapToEntity(final SupplierDTO supplierDTO, final Supplier supplier) {
        supplier.setSupplierName(supplierDTO.getSupplierName());
        return supplier;
    }

}
