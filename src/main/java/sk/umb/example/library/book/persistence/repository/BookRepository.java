package sk.umb.example.library.book.persistence.repository;

import org.springframework.stereotype.Repository;

import sk.umb.example.library.book.persistence.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity,Long> {
    Iterable<BookEntity> findById(String bookId);   
}
