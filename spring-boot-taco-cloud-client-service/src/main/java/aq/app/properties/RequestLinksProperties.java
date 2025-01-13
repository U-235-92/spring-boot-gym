package aq.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "aq.app")
public class RequestLinksProperties {

	private String resourceServiceRequestLink;
}
