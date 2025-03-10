package be.systems.layn.mybookmarksapp.rest;

import be.systems.layn.mybookmarksapp.dto.Application;
import be.systems.layn.mybookmarksapp.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/applications")
public class ApplicationController {

	@Autowired
	private BookmarkRepository bookmarkRepository;

	@GetMapping(path = "/")
	public ResponseEntity<List<Application>> getAll() {
		List<Application> applications = bookmarkRepository
				.findAll()
				.stream()
				.map(b -> new Application(
						b.getId(),
						b.getName(),
						b.getDescription(),
						b.getCategory() != null ? b.getCategory().getId().toString() : null,
						null,
						new ArrayList<>())
				).toList();

		return ResponseEntity.ok(applications);
	}
}
