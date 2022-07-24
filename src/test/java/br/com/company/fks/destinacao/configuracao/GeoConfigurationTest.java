package br.com.company.fks.destinacao.configuracao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
public class GeoConfigurationTest{

    @InjectMocks
    private GeoConfiguration geoConfiguration;

    @Test
    public void jtsModule(){
        assertNotNull(geoConfiguration.jtsModule());
    }

}
