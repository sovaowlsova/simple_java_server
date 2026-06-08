package com.sovaowlsova.FoxyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
public class FoxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoxyServerApplication.class, args);
	}

	@GetMapping("/export-controls")
	public ResponseEntity<String> getControls() {

		try {
			Path filePath = Paths.get("test.csv");
			String content = Files.readString(filePath);

			return ResponseEntity.ok()
					.contentType(MediaType.TEXT_PLAIN)
					.body(content);

		} catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
