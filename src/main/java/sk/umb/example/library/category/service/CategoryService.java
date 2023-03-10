package sk.umb.example.library.category.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final List<CategoryDetailDataTransferObject> categories =  new ArrayList<>();
    private Long lastIndex = 0L;

    public List<CategoryDetailDataTransferObject> getCategories() {
        return categories;
    }

    public CategoryDetailDataTransferObject getCategoryById(Long categoryId) {
        if (categoryId < 0) { return new CategoryDetailDataTransferObject(); }
        if (categoryId >= lastIndex) { return new CategoryDetailDataTransferObject(); }

        for (CategoryDetailDataTransferObject category : categories) {
			if (category.getId().equals(categoryId)) {
				return category;
			}
		}
		
		return new CategoryDetailDataTransferObject();
    }

    public Long createCategory(CategoryRequestDataTransferObject category) {
        CategoryDetailDataTransferObject categoryDataTransferObject = mapToCategoryDataTransferObject(category);
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


    private CategoryDetailDataTransferObject mapToCategoryDataTransferObject(CategoryRequestDataTransferObject category) {
        CategoryDetailDataTransferObject categoryDataTransferObject = new CategoryDetailDataTransferObject();
        
        categoryDataTransferObject.setName(category.getName());

        return categoryDataTransferObject;
    }

    public void updateCategory(Long categoryId, CategoryRequestDataTransferObject category) {
        if (categoryId < 0) { return; }
		if (categoryId >= lastIndex) { return; }

        for(CategoryDetailDataTransferObject categoryDataTransferObject : categories) {
            if(categoryDataTransferObject.getId().equals(categoryId)) {
                categoryDataTransferObject.setName(category.getName());
                return;
            }
        }
    }

    public void deleteCategory(Long categoryId) {
        if (categoryId < 0) { return; }
		if (categoryId >= lastIndex) { return; }

		for (CategoryDetailDataTransferObject categoryFromList : categories) {
			if (categoryFromList.getId().equals(categoryId)) {
				categories.remove(categoryFromList);
				return;
			}
		}
    }


}
