package aq.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "aq.app")
public class ClientServiceProperties {

	private String clientId;
	private String clientSecret;
	private String issuerUri;
	private Integer issuerPort;
	private String redirectUri;
	private String authorizationUri;
	private String tokenUri;
	private String jwkSetUri;
	private String userInfoUri;
}
