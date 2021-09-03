package tn.formalab.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.ecommerce.models.Product;
import tn.formalab.ecommerce.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Integer id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("product with id: " + id + " not found"));

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Integer id) {
        this.productRepository.deleteById(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("m", "Product deleted");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PutMapping("update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try {
            //check if product exist, else findById returns an exception
            productRepository.findById(product.id);

            Product productToUpdate= this.productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(productToUpdate);
        } catch (Exception e) {
            // we have to return new Product() in the bidy,
            // because of the output type of the api ("ResponseEntity<Product>")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Product());
        }

    }
}
