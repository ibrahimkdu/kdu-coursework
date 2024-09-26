package com.kdu.Assesment2.controller;
import com.kdu.Assesment2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.kdu.Assesment2.model.Product;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @PostMapping("/product")
    public ResponseEntity<String> saveShift(@RequestBody Product product) {
        productService.saveproduct(product);
        return new ResponseEntity<>("Product saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getShiftById(@PathVariable Integer orderId) {
        Product retrievedShift = productService.getProductById(orderId);
        if (retrievedShift != null) {
            return new ResponseEntity<>(retrievedShift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllUsers() {
        List<Product> allShifts = productService.getAllProducts();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }
}
