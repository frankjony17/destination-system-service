package br.com.company.fks.destinacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Classe que inicializa a aplicação, que contém o método main da aplicação
 */
@EntityScan(basePackageClasses = { SistemaUnificadoApplication.class, Jsr310JpaConverters.class })
@EnableConfigurationProperties
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "br.gov.mpog" })
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SistemaUnificadoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SistemaUnificadoApplication.class);
    }

    /**
     * Método main da aplicação
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        SpringApplication.run(SistemaUnificadoApplication.class, args);
    }

}
