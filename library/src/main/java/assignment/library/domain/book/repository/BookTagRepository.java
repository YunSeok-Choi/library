package assignment.library.domain.book.repository;

import assignment.library.domain.book.entity.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {
}
