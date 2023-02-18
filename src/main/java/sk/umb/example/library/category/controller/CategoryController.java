package sk.umb.example.library.category.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
	@GetMapping("/api/categories")
	public void searchCategory(@RequestParam(required = false) String categoryName) {
		System.out.println("Search categories was called" + ((!categoryName.isEmpty() ? ", " + categoryName : ".")));
		
	}

	@GetMapping("/api/categories/{categoryId}")
	public void getCategory(@PathVariable Long categoryId) {
		System.out.println("Get category was called, " + categoryId);
	}

	@PostMapping("/api/categories")
	public void createCategory() {
		System.out.println("Create category was called.");
	}

	@PutMapping("/api/categories/{categoryId}")
	public void updateCategory(@PathVariable Long categoryId) {
		System.out.println("Update category was called, " + categoryId);
	}

	@DeleteMapping("/api/categories/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		System.out.println("Delte category was called, " + categoryId);
	}
}