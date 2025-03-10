package be.systems.layn.mybookmarksapp.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Data
public class Link {
	@NotBlank
	private String name;
	@URL
	@NotBlank
	private String url;
	private List<Tag> tags = new ArrayList<>();
}
