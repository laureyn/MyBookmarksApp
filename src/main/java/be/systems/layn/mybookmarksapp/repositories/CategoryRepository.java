package be.systems.layn.mybookmarksapp.repositories;

import be.systems.layn.mybookmarksapp.models.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
	Optional<Category> findByName(@NotBlank String name);
	List<Category> findByOrderGreaterThanEqual(@Min(0) int orderIsGreaterThan);
}
