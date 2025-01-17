package aq.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aq.app.integration.FileWriterGateway;
import aq.app.models.IntegrationText;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/integration")
@RequiredArgsConstructor
public class FileWriterController {

	private final FileWriterGateway fileWriterGateway;
	
	@PostMapping(path = "/write_string_to_file", consumes = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void writeStringToFile(@RequestBody IntegrationText integrationText) {
		fileWriterGateway.writeToFile(integrationText.getFilename(), integrationText.getData());
	}
}
