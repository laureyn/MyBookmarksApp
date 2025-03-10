package be.systems.layn.mybookmarksapp.services;

import be.systems.layn.mybookmarksapp.dto.Category;
import be.systems.layn.mybookmarksapp.exceptions.AlteredEntityException;
import be.systems.layn.mybookmarksapp.exceptions.ConflictException;
import be.systems.layn.mybookmarksapp.repositories.CategoryRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final Clock clock;

	public CategoryService(CategoryRepository categoryRepository, Clock clock) {
		this.categoryRepository = categoryRepository;
		this.clock = clock;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll().stream()
				.map(this::mapCategory)
				.toList();
	}

	public Optional<Category> getCategoryById(String id) {
		return categoryRepository.findById(id)
				.map(this::mapCategory);
	}

	@Transactional
	public Optional<Category> createCategory(Category category) {
		if (categoryRepository.findByName(category.name()).isPresent()) {
			throw new ConflictException("A category named '%s' already exists".formatted(category.name()));
		}

		AtomicInteger counter = new AtomicInteger(category.order());
		categoryRepository.saveAll(
				categoryRepository
					.findByOrderGreaterThanEqual(counter.get()).stream()
					.sorted(Comparator.comparingInt(be.systems.layn.mybookmarksapp.models.Category::getOrder).reversed())
					.peek(entity -> entity.setOrder(counter.incrementAndGet()))
					.toList()
				);

		be.systems.layn.mybookmarksapp.models.Category entity = mapCategory(category, LocalDateTime.now(clock));
		return Optional.of(mapCategory(categoryRepository
					.save(entity)
				));
	}

	@Transactional
	public Optional<Category> updateCategory(Category category) {
		return categoryRepository
				.findById(category.id())
				.map(entity -> {
					entity.setName(category.name());
					entity.setIconUrl(category.iconUrl());

					if(entity.getOrder() != category.order()) {
						entity.setOrder(category.order());

						AtomicInteger counter = new AtomicInteger(category.order());
						categoryRepository.saveAll(
								categoryRepository
										.findByOrderGreaterThanEqual(counter.get()).stream()
										.sorted(Comparator.comparingInt(be.systems.layn.mybookmarksapp.models.Category::getOrder).reversed())
										.peek(otherCategory -> otherCategory.setOrder(counter.incrementAndGet()))
										.toList()
						);
					}

					entity.setUpdatedAt(LocalDateTime.now(clock));
					be.systems.layn.mybookmarksapp.models.Category savedCat = categoryRepository.save(entity);
					return mapCategory(savedCat);
				});
	}

	@Transactional
	public Optional<Category> updateCategory(String id, Map<String, Object> fields) {
		fields.remove("id");

		return categoryRepository.findById(id)
				.map(categoryEntitiy -> {
//					if (categoryEntitiy.getUpdatedAt() != null && categoryEntitiy
//							.getUpdatedAt()
//							.isAfter(ifUnmodifiedSince)) {
//						throw new AlteredEntityException("entity '%s' has been modified on %s".formatted(categoryEntitiy.getName(), categoryEntitiy.getUpdatedAt()));
//					}
					fields.forEach((key, value) -> {
						Field field = ReflectionUtils.findField(be.systems.layn.mybookmarksapp.models.Category.class, key);
						if (field != null) {
							field.setAccessible(true);
							ReflectionUtils.setField(field, categoryEntitiy, value);
						}
					});

					Integer order = (Integer) fields.remove("order");
					if (order != null) {
						AtomicInteger counter = new AtomicInteger(order);
						categoryRepository.saveAll(
								categoryRepository
										.findByOrderGreaterThanEqual(counter.get()).stream()
										.sorted(Comparator.comparingInt(be.systems.layn.mybookmarksapp.models.Category::getOrder).reversed())
										.peek(entity -> entity.setOrder(counter.incrementAndGet()))
										.toList()
						);
					}

					categoryEntitiy.setUpdatedAt(LocalDateTime.now(clock));
					return mapCategory(categoryRepository.save(categoryEntitiy));
				});
	}

	public boolean deleteCategory(@NotBlank String id) {
		return categoryRepository.findById(id)
				.map(category -> {
					categoryRepository.delete(category);
					return true;
				}).orElse(false);
	}

	protected final Category mapCategory(be.systems.layn.mybookmarksapp.models.Category entity) {
		return new Category(
				entity.getId(),
				entity.getName(),
				entity.getOrder(),
				entity.getIconUrl()
		);
	}

	protected final be.systems.layn.mybookmarksapp.models.Category mapCategory(Category dto, LocalDateTime updatedAt) {
		return new be.systems.layn.mybookmarksapp.models.Category(
				dto.id(),
				dto.order(),
				dto.name(),
				dto.iconUrl(),
				null,
				updatedAt);
	}
}
