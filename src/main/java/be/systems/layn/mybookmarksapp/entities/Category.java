package be.systems.layn.mybookmarksapp.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document(collection = "categories")
public class Category {

	@MongoId
	private String id;
	private int order;
	private String name;
	private String iconUrl;
}
