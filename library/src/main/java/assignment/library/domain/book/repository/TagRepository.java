package assignment.library.domain.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import assignment.library.domain.book.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagName(String tagName);
}
