package be.systems.layn.mybookmarksapp.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "bookmarks")
public class Bookmark {

	@MongoId()
	private String id;
	private String name;
	private String description;
	@DBRef
	private Category category;
	private Image image;
	private List<Link> links;
}
