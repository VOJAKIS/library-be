package sk.umb.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		System.out.println("Swagger-UI:");
		System.out.println("http://localhost:8080/swagger-ui/index.html");
		SpringApplication.run(LibraryApplication.class, args);
	}

}
