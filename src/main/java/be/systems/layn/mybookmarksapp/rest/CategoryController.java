package be.systems.layn.mybookmarksapp.rest;

import be.systems.layn.mybookmarksapp.dto.Category;
import be.systems.layn.mybookmarksapp.services.CategoryService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping(path = "")
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}

	@PostMapping(path = "")
	public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
		return categoryService.createCategory(category)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable(value = "id") String id) {
		return categoryService.getCategoryById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") String id,
												   @RequestHeader(value = "If-Unmodified-Since", required = false) LocalDateTime ifUnmodifiedSince,
												   @Valid @RequestBody Category category) {
		if (id.isEmpty() || !id.equals(category.id())) {
			return ResponseEntity.badRequest().build();
		}

		return categoryService.updateCategory(category)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<Category> patchCategory(@PathVariable(value = "id") String id,
//												  @RequestHeader(value = "If-Unmodified-Since", required = false) LocalDateTime ifUnmodifiedSince,
												  @Valid @RequestBody @Schema(implementation = Category.class, requiredProperties = {}) Map<String, Object> fields) {
		if (id.isEmpty() || (fields.containsKey("id") && !id.equals(fields.get("id")))) {
			return ResponseEntity.badRequest().build();
		}
		Set<String> keys = fields.keySet();
		Arrays.stream(Category.class.getFields()).toList().forEach(keys::remove);
		if (!keys.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		//TODO find a way to check time diff between header If-Unmodified-Since and updateAt stored in entity
		//     if the entity was modified since, return a 412 (Precondition Failed)
		//     Header is OPTIONAL!
		return categoryService.updateCategory(id, fields)
				.map(ResponseEntity::ok)
				.orElseGet( () -> ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") String id) {
		return categoryService.deleteCategory(id) ?
				ResponseEntity.noContent().build() :
				ResponseEntity.notFound().build();
	}
}
