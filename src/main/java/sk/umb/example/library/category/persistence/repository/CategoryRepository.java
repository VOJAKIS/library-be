package sk.umb.example.library.category.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {
    //FIXME: Optional → Iterable, len aktualne to nefunguje
    Iterable<CategoryEntity> findById(String categoryId);
}
