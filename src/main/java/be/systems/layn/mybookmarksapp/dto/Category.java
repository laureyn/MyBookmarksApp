package be.systems.layn.mybookmarksapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Category(
		String id,
		@NotBlank String name,
		@NotNull @Min(0)  Integer order,
		String iconUrl) {
}
