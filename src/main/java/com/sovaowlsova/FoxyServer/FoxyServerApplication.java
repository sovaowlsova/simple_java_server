package com.sovaowlsova.FoxyServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
public class FoxyServerApplication {
	@Value("${foxy.password}")
	private String validPassword;

	private static final String COMPANY_NAME = "DefaultCompany";
	private static final String PROJECT_NAME = "Foxy";

	private static final Path CSV_FILE = getCsvPath();

	public static void main(String[] args) {
		SpringApplication.run(FoxyServerApplication.class, args);
	}

	private static Path getCsvPath() {
		String os = System.getProperty("os.name").toLowerCase();
		String home = System.getProperty("user.home");

		String commandsFolder = "";
		if (os.contains("win")) {
			commandsFolder = home + "\\AppData\\LocalLow\\" + COMPANY_NAME + "\\" + PROJECT_NAME;
		} else if (os.contains("lin")) {
			commandsFolder = home + "/.config/unity3d/" + COMPANY_NAME + "/" + PROJECT_NAME;
		} else {
			System.out.println("Your OS is not supported.");
			System.exit(1);
		}

		return Paths.get(commandsFolder, "active_command.csv");
	}

	@GetMapping("/export-controls")
	public ResponseEntity<String> getControls(@RequestParam(value = "password", defaultValue = "0") String password) {
		if (!password.equals(validPassword)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
		}
		try {
			if (!Files.exists(CSV_FILE)) {
				return ResponseEntity.notFound().build();
			}
			String content = Files.readString(CSV_FILE);

			return ResponseEntity.ok()
					.contentType(MediaType.TEXT_PLAIN)
					.body(content);

		} catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

	@DeleteMapping("/remove-controls")
	public ResponseEntity<String> removeControls(@RequestParam(value = "password", defaultValue = "0") String password) {
		if (!password.equals(validPassword)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
		}
		try {
			if (!Files.deleteIfExists(CSV_FILE)) {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

		return ResponseEntity.ok().build();
    }
}
