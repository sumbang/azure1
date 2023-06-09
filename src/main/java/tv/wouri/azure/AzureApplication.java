package tv.wouri.azure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.adc.sygem.models"})
@EnableJpaRepositories(basePackages = {"tv.wouri.azure.repositories"})
@ComponentScan({"tv.wouri.azure.controllers","tv.wouri.azure.services","tv.wouri.azure.security"})
public class AzureApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AzureApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AzureApplication.class, args);
	}

}
