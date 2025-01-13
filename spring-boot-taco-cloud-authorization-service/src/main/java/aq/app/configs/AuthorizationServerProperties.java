package aq.app.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "aq.app")
public class AuthorizationServerProperties {

	private Integer clientPort;
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private String issuerUrl;
}
