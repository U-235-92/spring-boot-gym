package aq.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import aq.app.models.Ingredient;
import aq.app.properties.ClientServiceProperties;
import aq.app.properties.RequestLinksProperties;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
public class IngredientService {
	
	private final RestTemplate restTemplate;
	private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
	private final ClientServiceProperties clientServiceProperties;
	private final RequestLinksProperties requestLinksProperties;
	
	public List<Ingredient> getAllIngredients() {
		addBearerTokenToRestTemplate();
		Ingredient[] ingredients = restTemplate
				.getForEntity(requestLinksProperties.getResourceServiceRequestLink(), Ingredient[].class)
				.getBody();
		return Arrays.asList(ingredients);
	}
	
	public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
		addBearerTokenToRestTemplate();
		return restTemplate
				.postForObject(requestLinksProperties.getResourceServiceRequestLink(), ingredient, Ingredient.class);
	}
	
	private void addBearerTokenToRestTemplate() {
		restTemplate.getInterceptors().add(tokenInterceptor());
	}
	
	private ClientHttpRequestInterceptor tokenInterceptor() {
		return (request, body, execution) -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String accessToken = oAuth2AuthorizedClientService
					.loadAuthorizedClient(clientServiceProperties.getClientId(), authentication.getName())
					.getAccessToken()
					.getTokenValue();
			request.getHeaders().add("Authorization", "Bearer "  + accessToken);
			return execution.execute(request, body);
		};
	}
}
