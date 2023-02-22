package sk.umb.example.library.category.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("api/categories")
    public List searchCategory(@RequestParam(required = false) String categoryName) {
        System.out.println("Searching for category: " + categoryName);
        return Collections.emptyList();
    }

    @GetMapping("api/categories/{categoryID}")
    public void getCategory(@PathVariable Long categoryID) {
        System.out.println("Retrieving details about category with ID " + categoryID);
    }

    @PostMapping("api/categories")
    public void createCategory() {
        System.out.println("Creating category ...");
    }

    @PutMapping("api/categories/{categoryID}")
    public void updateCategory(@PathVariable Long categoryID) {
        System.out.println("Updating category with ID" + categoryID);
    }

    @DeleteMapping("api/categories/{categoryID}")
    public void deleteCategory(@PathVariable Long categoryID) {
        System.out.println("Deleting category with ID" + categoryID);
    }

}
