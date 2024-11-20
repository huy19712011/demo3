package com.example.demo3.DTO;

import com.example.demo3.entity.Product;
import com.example.demo3.repository.ProductRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductDTOService {
    ProductRepository productRepository = new ProductRepository();

    public ArrayList<ProductDTO> getProducts() {

        return productRepository.getProducts()
                .stream()
                .map(product -> new ProductDTO(product.getId(), product.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ProductDTO getProductById(Long id) {

        Product product = productRepository.getProductById(id);
        return new ProductDTO(product.getId(), product.getName());
    }

    public void updateProduct(ProductDTO productDTO) {

        Product existingProduct = productRepository.getProductById(productDTO.getId());
        existingProduct.setName(productDTO.getName());

        productRepository.updateProduct(existingProduct);
    }

    public void deleteProduct(long id) {

        productRepository.deleteProduct(id);
    }

    public void addProduct(ProductDTO productDTO) {

        Product product = new Product(productDTO.getId(), productDTO.getName());
        productRepository.addProduct(product);
    }

}
