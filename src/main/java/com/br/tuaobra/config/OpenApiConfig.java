package com.br.tuaobra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(name = "Equipe TuaObra", url = "https://github.com/Rian-Noronha/tuaobra"),
        description = "O TuaObra é uma aplicação para conectar clientes, pedreiros e casas de construção.",
        title = "TuaObra API", 
        version = "1.0", 
        license = @License(name = "Apache-2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")), 
        servers = {
        @Server(description = "Local ENV", url = "http://localhost:8080"), 
        @Server(description = "Server ENV", url = "http://0.0.0.0:8080")
})
public class OpenApiConfig {

}
