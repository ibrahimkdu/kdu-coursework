package com.kdu.Assesment2.service;

import com.kdu.Assesment2.model.Order;
import com.kdu.Assesment2.model.Product;
import com.kdu.Assesment2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository=productRepository;
    }

    public void saveproduct(Product product)
    {
        productRepository.save(product);
    }
    public Product getProductById(Integer productId)
    {
       return productRepository.findById(productId).orElse(null);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
