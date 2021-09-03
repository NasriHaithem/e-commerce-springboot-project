package tn.formalab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
