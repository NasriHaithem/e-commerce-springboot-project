package tn.formalab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.ecommerce.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
