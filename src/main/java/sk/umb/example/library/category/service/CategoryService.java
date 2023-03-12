package sk.umb.example.library.category.service;

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


    private final List<CategoryDetailDataTransferObject> categories =  new ArrayList<>();
    private Long lastIndex = 0L;

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

    private void increaseIndexByOne() {
		lastIndex++;
	}
	private void printLastIndex() {
		System.out.println("Last index: " + lastIndex);
	}

    @Transactional
    public void updateCategory(Long categoryId, CategoryRequestDataTransferObject category) {
        CategoryEntity categoryEntity = getCategoryEntityById(categoryId);
        
        //TODO: Pridat validaciu ze je aktualizovany subor spravny

        
        categoryRepository.save(categoryEntity);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }


    private CategoryEntity mapToEntity(CategoryRequestDataTransferObject category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(getCategoryById(lastIndex).getName());

        return categoryEntity;
    }


    private CategoryEntity getCategoryEntityById(Long categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);

        if(!categoryEntity.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find category with ID:" + categoryId);
        }
        return categoryEntity.get();

    }
    
    private List<CategoryDetailDataTransferObject> mapToDataTransferObjectList(Iterable<CategoryEntity> categoryEntities) {
        List<CategoryDetailDataTransferObject> categories = new ArrayList<>();

        categoryEntities.forEach(categoryEntity -> {
            CategoryDetailDataTransferObject categoryDDTO = mapToDataTransferObject(categoryEntity);
            categories.add(categoryDDTO);
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
