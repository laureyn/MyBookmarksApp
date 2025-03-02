package be.systems.layn.mybookmarksapp.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Link {
	private String name;
	private String url;
	private List<Tag> tags;
}
