package aq.app.controllers;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Taco;
import aq.app.repositories.jpa_data.JpaTacoRepository;
import lombok.RequiredArgsConstructor;

@RestController //It was commented to use <<SPRING DATA REST>>
@RequestMapping(path = "api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://localhost:5082")
@RequiredArgsConstructor
public class TacoController {

	private final JpaTacoRepository tacoRepository;

	@GetMapping(params = "recent")
	public Iterable<Taco> recentTacos() {
		PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepository.findAll(pageRequest).getContent();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable Long id) {
		Optional<Taco> optTaco = tacoRepository.findById(id);
		if (optTaco.isEmpty())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepository.save(taco);
	}
}
