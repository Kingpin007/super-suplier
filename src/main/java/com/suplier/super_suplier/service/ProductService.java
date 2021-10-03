package com.suplier.super_suplier.service;

import com.suplier.super_suplier.domain.Product;
import com.suplier.super_suplier.domain.Supplier;
import com.suplier.super_suplier.model.ProductDTO;
import com.suplier.super_suplier.repos.ProductRepository;
import com.suplier.super_suplier.repos.SupplierRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(final ProductRepository productRepository,
            final SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final UUID id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final UUID id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final UUID id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setSupplierId(product.getSupplierId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProducts(product.getProducts() == null ? null : product.getProducts().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setSupplierId(productDTO.getSupplierId());
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        if (productDTO.getProducts() != null && (product.getProducts() == null || !product.getProducts().getId().equals(productDTO.getProducts()))) {
            final Supplier products = supplierRepository.findById(productDTO.getProducts())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "products not found"));
            product.setProducts(products);
        }
        return product;
    }

}
