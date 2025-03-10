package be.systems.layn.mybookmarksapp.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "bookmarks")
public class Bookmark {

	@MongoId()
	private String id;
	@NotBlank
	private String name;
	private String description;
//	@DocumentReference
	@DBRef
	private Category category;
	private Image image;
	@NotEmpty
	private List<Link> links = new ArrayList<>();
}
