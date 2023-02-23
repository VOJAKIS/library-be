package sk.umb.example.library.category.service;

import org.springframework.stereotype.Service;
import sk.umb.example.library.category.controller.CategoryRequestDataTransferObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final List<CategoryDataTransferObject> categories =  new ArrayList<>();
    private Long lastIndex = 0L;

    public List<CategoryDataTransferObject> getCategories() {
        return categories;
    }

    public CategoryDataTransferObject getCategoryById(Long categoryId) {
        if (categoryId < 0) { return new CategoryDataTransferObject(); }
        if (categoryId >= lastIndex) { return new CategoryDataTransferObject(); }

        for (CategoryDataTransferObject category : categories) {
			if (category.getId().equals(categoryId)) {
				return category;
			}
		}
		
		return new CategoryDataTransferObject();
    }

    public Long createCategory(CategoryRequestDataTransferObject category) {
        CategoryDataTransferObject categoryDataTransferObject = mapToCategoryDataTransferObject(category);
        categoryDataTransferObject.setId(lastIndex);

        increaseIndexByOne();
        printLastIndex();

        categories.add(categoryDataTransferObject);

        return categoryDataTransferObject.getId();
    }

    private void increaseIndexByOne() {
		lastIndex++;
	}
	private void printLastIndex() {
		System.out.println("Last index: " + lastIndex);
	}


    private CategoryDataTransferObject mapToCategoryDataTransferObject(CategoryRequestDataTransferObject category) {
        CategoryDataTransferObject categoryDataTransferObject = new CategoryDataTransferObject();
        categoryDataTransferObject.setName(category.getName());

        return categoryDataTransferObject;
    }

    public void updateCategory(Long categoryId, CategoryRequestDataTransferObject category) {
        for(CategoryDataTransferObject categoryDataTransferObject : categories) {
            if(categoryDataTransferObject.getId().equals(categoryId)) {
                categoryDataTransferObject = mapToCategoryDataTransferObject(category);
                return;
            }
        }
    }

    public void deleteCategory(Long categoryId) {
        if (categoryId < 0) { return; }
		if (categoryId >= lastIndex) { return; }

		for (CategoryDataTransferObject categoryFromList : categories) {
			if (categoryFromList.getId().equals(categoryId)) {
				categories.remove(categoryFromList);
				return;
			}
		}
    }


}
