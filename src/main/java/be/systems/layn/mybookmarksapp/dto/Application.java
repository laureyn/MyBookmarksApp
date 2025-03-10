package be.systems.layn.mybookmarksapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated

public record Application(
		String id,
		@NotBlank String name,
		String description,
		String categoryId,
		Image image,
		@NotNull List<Link> links) {
}
