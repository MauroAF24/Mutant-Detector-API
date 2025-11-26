package com.example.MutantDetectorApplication.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Creamos esta clase con el fin de modificar Swagger para que se vea más amigable y cómodo a la vista
@Configuration
public class OpenAPIConfig {

    /* Le decimos a Spring Boot que me ejecute este método */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mutant Detector API")
                        .version("1.0.0")
                        .description("API REST para detectar mutantes basándose en secuencias de ADN. Un humano es considerado mutante si se encuentran más de una secuencia de cuatro letras iguales (A, T, C, G) en dirección horizontal, vertical o diagonal en su matriz de ADN NxN.")
                        .contact(new Contact()
                                .name("Mauro Fernández")
                                .email("mf149981@gmail.com")));
    }
}
