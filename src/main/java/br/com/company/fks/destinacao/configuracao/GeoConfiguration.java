package br.com.company.fks.destinacao.configuracao;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração
 */
@Configuration
public class GeoConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public Module jtsModule() {
        return new JtsModule();
    }
}
