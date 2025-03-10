package be.systems.layn.mybookmarksapp.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorView(LocalDateTime timestamp, int statusCode, String error, String message, String path, Map<String, String> details) {
}
