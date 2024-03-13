package com.StudDept.confoguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition
        (
        info = @Info(
                title = "Class-Management",
                description = "it will provided all th operation related Student and department.",
                contact = @Contact(
                        name = "Gauri Tambakhe",
                        email = "tambakhegauri@gmail.com",
                        url = "gauriTambakhe.com"

                ),
                license = @License(
                        name = "Apache2.0",
                        url = "weeTechSolution.com"
                ),
                version = "1.0",
                termsOfService = "Terms Of Security"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local Server"
                ),
                @Server(
                        url = "WeeTechSolution.com",
                        description = "private Server"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Bearer Token",
        bearerFormat = "JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {

}
