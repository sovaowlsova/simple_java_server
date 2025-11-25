package com.sovaowlsova.FoxyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.io.File;

@SpringBootApplication
@RestController
public class FoxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoxyServerApplication.class, args);
	}

	@GetMapping("/controls")
	public ControlData getControls() {
		File file = new File("test.json");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(file, ControlData.class);
	}
}
