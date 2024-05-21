package sacola.api.sacola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Sacola", version = "1.0", description = "Documentação da API Sacola"))
@EnableWebMvc
public class SacolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SacolaApplication.class, args);
	}

}
