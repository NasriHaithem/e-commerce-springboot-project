package tn.formalab.ecommerce.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.ecommerce.models.Category;
import tn.formalab.ecommerce.repository.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category savedCategory = this.categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Integer id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("category with id: " + id + " not found"));

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Integer id) {
        this.categoryRepository.deleteById(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("m", "Category deleted");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PutMapping("update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        try {
            //check if category exist, else findById returns an exception
            categoryRepository.findById(category.id);

            Category categoryToUpdate= this.categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(categoryToUpdate);
        } catch (Exception e) {
            // we have to return new Category() in the bidy,
            // because of the output type of the api ("ResponseEntity<Category>")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category());
        }

    }
}
