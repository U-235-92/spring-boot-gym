package aq.app.configs;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

@Configuration
public class FileWriterIntegrationConfig {

	@Bean
	@Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
	GenericTransformer<String, String> upperCaseTransformer() {
		return sourceText -> sourceText.toUpperCase(); 
	}
	
	@Bean
	@ServiceActivator(inputChannel = "fileWriterChannel")
	FileWritingMessageHandler fileWriter() {
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("src/main/resources/files"));
		handler.setExpectReply(false);
		handler.setFileExistsMode(FileExistsMode.APPEND);
		handler.setAppendNewLine(true);
		return handler;
	}
	
//	It's not necessary to create this bean, because spring boot will create it's one automatically if it doesn't exist;
//	The name of bean the same as the name specifies in @Transformer and @ServiceActivator
	@Bean
	MessageChannel textInChannel() {
		return new DirectChannel();
	}

//	It's not necessary to create this bean, because spring boot will create it's one automatically if it doesn't exist;
//	The name of bean the same as the name specifies in @Transformer and @ServiceActivator
	@Bean
	MessageChannel fileWriterChannel() {
		return new DirectChannel();
	}
}
