package sk.umb.example.library.category.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
import sk.umb.example.library.category.persistence.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDetailDataTransferObject> getCategories() {
        return mapToDataTransferObjectList(categoryRepository.findAll());
    }

    public CategoryDetailDataTransferObject getCategoryById(Long categoryId) {
        return mapToDataTransferObject(getCategoryEntityById(categoryId));
    }

    @Transactional
    public Long createCategory(CategoryRequestDataTransferObject category) {
        CategoryEntity categoryEntity = mapToEntity(category);

        return categoryRepository.save(categoryEntity).getId();
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryRequestDataTransferObject categoryRequestDataTransferObject) {
        CategoryEntity categoryEntity = getCategoryEntityById(categoryId);
        
		if (! Strings.isEmpty(categoryRequestDataTransferObject.getName())) {
			categoryEntity.setName(categoryRequestDataTransferObject.getName());
		}
        
        categoryRepository.save(categoryEntity);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }


    private CategoryEntity mapToEntity(CategoryRequestDataTransferObject category) {
        CategoryEntity categoryEntity = new CategoryEntity();
		
        categoryEntity.setName(category.getName());

        return categoryEntity;
    }


    private CategoryEntity getCategoryEntityById(Long categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);

		// ERROR: Zlá podmienka
        if(categoryEntity.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find category with ID: " + categoryId);
        }
        
		return categoryEntity.get();
    }
    
    private List<CategoryDetailDataTransferObject> mapToDataTransferObjectList(Iterable<CategoryEntity> categoryEntities) {
        List<CategoryDetailDataTransferObject> categories = new ArrayList<>();

        categoryEntities.forEach(categoryEntity -> {
            CategoryDetailDataTransferObject categoryDetailDataTransferObject = mapToDataTransferObject(categoryEntity);
            categories.add(categoryDetailDataTransferObject);
        });

        return categories;
    }

    private CategoryDetailDataTransferObject mapToDataTransferObject(CategoryEntity categoryEntity) {
        CategoryDetailDataTransferObject category = new CategoryDetailDataTransferObject();

        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());

        return category;
    }
}
