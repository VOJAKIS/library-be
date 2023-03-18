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

	@GetMapping("/api/bookCategories")
	public List<CategoryDetailDataTransferObject> getCategories(@RequestParam(required = false) String categoryName) {
		System.out.println("Search book categories was called: " + categoryName);
		return (Strings.isEmpty(categoryName)) ? 
			categoryService.getAllCategories() :
			categoryService.searchCategoriesByName(categoryName);
	}

	@GetMapping("/api/bookCategories/{bookCategoryId}")
	public CategoryDetailDataTransferObject getCategory(@PathVariable Long bookCategoryId) {
		System.out.println("Get book category was called, " + bookCategoryId);
		return categoryService.getCategoryById(bookCategoryId);
	}

	@PostMapping("/api/bookCategories")
	public String createCategory(@RequestBody CategoryRequestDataTransferObject category) {
		System.out.println("Create book category was called.");
		return "id: " + categoryService.createCategory(category);
	}

	@PutMapping("/api/bookCategories/{bookCategoryId}")
	public void updateCategory(@PathVariable Long bookCategoryId, @RequestBody CategoryRequestDataTransferObject category) {
		System.out.println("Update book category was called, " + bookCategoryId);
		categoryService.updateCategory(bookCategoryId, category);
	}

	@DeleteMapping("/api/bookCategories/{bookCategoryId}")
	public void deleteCategory(@PathVariable Long bookCategoryId) {
		System.out.println("Delte book category was called, " + bookCategoryId);
		categoryService.deleteCategory(bookCategoryId);
	}
}