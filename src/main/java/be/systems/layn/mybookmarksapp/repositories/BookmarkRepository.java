package be.systems.layn.mybookmarksapp.repositories;

import be.systems.layn.mybookmarksapp.models.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends MongoRepository<Bookmark, UUID> {

	Optional<Bookmark> findByName(String name);
}
