package br.com.idosos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "API - Sistema Para Gerenciar pacientes .",
			version = "1.0",
			description = "Documentando uma API para um sistema que auxilia usuários a cuidar de pessoas doentes .",
			contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
	)
public class AjudaIdososApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjudaIdososApplication.class, args);
	}

}
