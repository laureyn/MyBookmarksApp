package be.systems.layn.mybookmarksapp;

import org.springframework.boot.SpringApplication;


public class MyBookmarksAppTestApplication {

	public static void main(String[] args) {
		SpringApplication
				.from(MyBookmarksAppApplication::main)
				.with(ContainersConfig.class)
				.run(args);
	}
}
