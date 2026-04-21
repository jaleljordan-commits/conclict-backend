package com.conflicttracker.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    static {
        // Cargar variables desde .env si existe
        Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();
        
        // Establecer las variables como propiedades del sistema
        dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
