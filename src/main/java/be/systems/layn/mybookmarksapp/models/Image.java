package be.systems.layn.mybookmarksapp.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class Image {
	@URL
	@NotBlank
	private String url;
	@NotBlank
	private String alt;
}
