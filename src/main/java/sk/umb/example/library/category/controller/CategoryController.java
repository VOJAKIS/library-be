package sk.umb.example.library.category.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.category.service.CategoryDetailDataTransferObject;
import sk.umb.example.library.category.service.CategoryRequestDataTransferObject;
import sk.umb.example.library.category.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

	private final CategoryService categoryService;
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/api/categories")
	public List<CategoryDetailDataTransferObject> searchCategory(@RequestParam(required = false) String categoryName) {
		System.out.println("Search categories was called: " + categoryName);
		return categoryService.getCategories();
	}

	@GetMapping("/api/categories/{categoryId}")
	public CategoryDetailDataTransferObject getCategory(@PathVariable Long categoryId) {
		System.out.println("Get category was called, " + categoryId);
		return categoryService.getCategoryById(categoryId);
	}

	@PostMapping("/api/categories")
	public String createCategory(@RequestBody CategoryRequestDataTransferObject category) {
		System.out.println("Create category was called.");
		return "id: " + categoryService.createCategory(category);
	}

	@PutMapping("/api/categories/{categoryId}")
	public void updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDataTransferObject category) {
		System.out.println("Update category was called, " + categoryId);
		categoryService.updateCategory(categoryId, category);
	}

	@DeleteMapping("/api/categories/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		System.out.println("Delte category was called, " + categoryId);
		categoryService.deleteCategory(categoryId);
	}
}