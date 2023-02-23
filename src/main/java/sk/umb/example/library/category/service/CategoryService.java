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
        CategoryDataTransferObject categoryDTO = new CategoryDataTransferObject();
        categoryDTO.setName(category.getName());
        categoryDTO.setCategoryIds(category.getCategoryIds());

        return categoryDTO;
    }

    public void updateCategory(Long categoryId, CategoryRequestDataTransferObject category) {
        for(CategoryDataTransferObject categoryDTO : categories) {
            if(categoryDTO.getId().equals(categoryId)) {
                categoryDTO.setName(category.getName());
                categoryDTO.setCategoryIds(category.getCategoryIds());
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
