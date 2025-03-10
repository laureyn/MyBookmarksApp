package be.systems.layn.mybookmarksapp.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class Category {

	@MongoId()
	private String id;
	@Min(0)
	private int order;
	@NotBlank
	@Indexed(unique = true)
	private String name;
	private String iconUrl;
	@DBRef
	private List<Bookmark> bookmarks;
	@NotNull
	private LocalDateTime updatedAt;
}
