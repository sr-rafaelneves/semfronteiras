package br.com.rnconsulting.semfronteiras;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SemFronteiras API",version = "1.0", description = "API do sistema Sem Fronteiras"))
public class SemfronteirasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemfronteirasApplication.class, args);
	}

}
