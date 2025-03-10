package be.systems.layn.mybookmarksapp.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Tag {
	@NotBlank
	private String name;
	@NotBlank
	private String color;
}
