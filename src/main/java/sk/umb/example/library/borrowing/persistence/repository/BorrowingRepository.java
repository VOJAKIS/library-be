package sk.umb.example.library.borrowing.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sk.umb.example.library.borrowing.persistence.entity.BorrowingEntity;

@Repository
public interface BorrowingRepository extends CrudRepository<BorrowingEntity, Long> {
	@Override
	List<BorrowingEntity> findAll();

	List<BorrowingEntity> findByBookTitle(String bookTitle);

	Iterable<BorrowingEntity> findAllByCustomerId(Long customerId);

	Iterable<BorrowingEntity> findAllByBookId(Long bookId);

}
