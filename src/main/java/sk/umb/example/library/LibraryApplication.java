package sk.umb.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class LibraryApplication {
	public static void main(String[] args) {
		System.out.println("Hello Spring application");
		SpringApplication.run(LibraryApplication.class, args);
	}

	@GetMapping
	public long getBooks() {
		return 8L;
	}
}
