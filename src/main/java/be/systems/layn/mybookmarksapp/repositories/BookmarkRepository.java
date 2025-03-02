package be.systems.layn.mybookmarksapp.repositories;

import be.systems.layn.mybookmarksapp.entities.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "applications", path = "applications")
public interface BookmarkRepository extends MongoRepository<Bookmark, UUID> {

	Optional<Bookmark> findByName(String name);
}
