package sk.umb.example.library.category.service;

import org.springframework.stereotype.Service;
import sk.umb.example.library.category.controller.CategoryRequestDataTransferObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final List<CategoryDataTransferObject> categories =  new ArrayList<>();

    public List<CategoryDataTransferObject> getCategories() {
        return categories;
    }

    public CategoryDataTransferObject getCategoryById(Long categoryId) {
        int index = categoryId.intValue();
        if(index >= categories.size())
            return new CategoryDataTransferObject();
        return categories.get(index);
    }

    public Long createCategory(CategoryRequestDataTransferObject category) {
        Long categoryId = (long) categories.size();
        CategoryDataTransferObject categoryDTO = mapToCategoryDTO(category);
        categoryDTO.setId(categoryId);

        categories.add(categoryDTO);
        return categoryDTO.getId();
    }

    private CategoryDataTransferObject mapToCategoryDTO(CategoryRequestDataTransferObject category) {
        CategoryDataTransferObject categoryDTO = new CategoryDataTransferObject();
        categoryDTO.setName(category.getName());
        categoryDTO.setCategoryIds(category.getCategoryIds());

        return categoryDTO;
    }

    public void updateCategory(Long categoryId, CategoryRequestDataTransferObject category) {
        for(CategoryDataTransferObject categoryDTO : categories) {
            if(categoryDTO.getId().equals(categoryId)) {
                categoryDTO.setId(categoryId);
                categoryDTO.setName(category.getName());
                categoryDTO.setCategoryIds(category.getCategoryIds());
                return;
            }
        }
    }

    public void deleteCategory(Long categoryId) {
        int indexFound = 0;
        for(CategoryDataTransferObject categoryDTO : categories) {
            if(categoryDTO.getId().equals(categoryId)) {
                indexFound = categories.indexOf(categoryDTO);
                categories.remove(indexFound);
                break;
            }
        }
        for(int i = indexFound; i < categories.size(); i++)
            categories.get(i).setId((long) i);
    }


}
