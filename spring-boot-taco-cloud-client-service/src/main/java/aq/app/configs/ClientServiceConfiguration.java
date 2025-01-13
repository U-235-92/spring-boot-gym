package aq.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

import aq.app.properties.ClientServiceProperties;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ClientServiceConfiguration {

	private final ClientServiceProperties properties;
	
	@Bean
	ClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistration client = ClientRegistration
				.withRegistrationId(properties.getClientId())
				.clientId(properties.getClientId())
				.clientSecret(properties.getClientSecret())
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri(properties.getRedirectUri())
				.scope("writeIngredients", "deleteIngredients", OidcScopes.OPENID)
				.issuerUri(properties.getIssuerUri())
				.authorizationUri(properties.getAuthorizationUri())
				.tokenUri(properties.getTokenUri())
				.jwkSetUri(properties.getJwkSetUri())
				.userInfoUri(properties.getUserInfoUri())
				.build();
		InMemoryClientRegistrationRepository сlientRegistrationRepository = new InMemoryClientRegistrationRepository(client);
		return сlientRegistrationRepository;
	}
}
