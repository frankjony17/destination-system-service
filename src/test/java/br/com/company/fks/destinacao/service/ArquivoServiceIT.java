package br.com.company.fks.destinacao.service;

import br.com.company.fks.destinacao.dominio.dto.ArquivoDTO;
import br.com.company.fks.destinacao.dominio.entidades.Arquivo;
import br.com.company.fks.destinacao.exceptions.NegocioException;
import br.com.company.fks.destinacao.repository.ArquivoRepository;
import br.com.company.fks.destinacao.utils.ArquivoUtils;
import com.vividsolutions.jts.geom.Geometry;
import lombok.SneakyThrows;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.store.ContentFeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.opengis.feature.simple.SimpleFeature;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by diego on 23/05/17.
 */
@IntegrationTest("server.port:0")
@RunWith(PowerMockRunner.class)
@PrepareForTest({FileDataStoreFinder.class, ArquivoUtils.class, com.google.common.io.Files.class})
public class ArquivoServiceIT {

    @InjectMocks
    private ArquivoService arquivoService;

    @Mock
    private Geometry geometry;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private File file;

    @Mock
    private SimpleFeatureSource simpleFeatureSource;

    @Mock
    private FileDataStore fileDataStore;

    @Mock
    private SimpleFeatureCollection simpleFeatureCollection;

    @Mock
    private ContentFeatureCollection contentFeatureCollection;

    @Mock
    private SimpleFeatureIterator simpleFeatureIterator;

    @Mock
    private SimpleFeature simpleFeature;

    @Mock
    private ArquivoDTO arquivoDTO;

    @Mock
    private ArquivoRepository arquivoRepository;

    @Mock
    private Arquivo arquivo;

    @Mock
    private ZipInputStream zipInputStream;

    @Mock
    private ZipEntry zipEntry;

    @Test
    public void validarConteudoDoArquivoVazio() {
        when(multipartFile.isEmpty()).thenReturn(true);
        Boolean valorEsperado = arquivoService.validarConteudoDoArquivo(multipartFile);
        assertFalse(valorEsperado);
    }

    @Test
    public void validarConteudoDoArquivoVazioGerandoErro() {
        when(multipartFile.isEmpty()).thenThrow(IOException.class);
        Boolean valorEsperado = arquivoService.validarConteudoDoArquivo(multipartFile);
        assertFalse(valorEsperado);
    }

    @Test(expected = NegocioException.class)
    @SneakyThrows
    public void montarArquivoShapeFileGeometryPoligonoInvalido() {
        iniciarMocksValidadarShape(false, "abc");
        Method metodo = ArquivoService.class.getDeclaredMethod("validarArquivoContemGeometriaPoligono", File.class);
        metodo.setAccessible(true);
        metodo.invoke(arquivoService, file);
    }

    @Test
    @SneakyThrows
    public void montarArquivoShapeFileGeometryValidaPoligonoInvalido() {
        iniciarMocksValidadarShape(true, "abc");
        Method metodo = ArquivoService.class.getDeclaredMethod("validarArquivoContemGeometriaPoligono", File.class);
        metodo.setAccessible(true);
        assertNotNull(metodo.invoke(arquivoService, file));
    }

    @Test
    @SneakyThrows
    public void montarArquivoShapeFileGeometryInvalidaPoligonoValido() {
        iniciarMocksValidadarShape(false, "Polygon");
        Method metodo = ArquivoService.class.getDeclaredMethod("validarArquivoContemGeometriaPoligono", File.class);
        metodo.setAccessible(true);
        assertNotNull(metodo.invoke(arquivoService, file));
    }

    @SneakyThrows
    private void iniciarMocksValidadarShape(Boolean geometriaValida, String tipoPoligono) {
        mockStatic(FileDataStoreFinder.class);
        when(FileDataStoreFinder.getDataStore(any(File.class))).thenReturn(fileDataStore);
        when(fileDataStore.getFeatureSource()).thenReturn(simpleFeatureSource);
        when(simpleFeatureSource.getFeatures()).thenReturn(simpleFeatureCollection);
        when(simpleFeatureCollection.features()).thenReturn(simpleFeatureIterator);
        when(simpleFeatureIterator.hasNext()).thenReturn(true, false);
        when(simpleFeatureIterator.next()).thenReturn(simpleFeature);
        when(simpleFeature.getAttribute(anyInt())).thenReturn(geometry);
        when(geometry.isValid()).thenReturn(geometriaValida);
        when(geometry.getGeometryType()).thenReturn(tipoPoligono);
    }

    @Test(expected = Exception.class)
    @SneakyThrows
    public void montarArquivoShapeFileGerandoErro() {
        when(multipartFile.isEmpty()).thenThrow(IOException.class);
        String shapeEsperado = arquivoService.montarArquivoShapeFile(multipartFile);
        assertNotNull(shapeEsperado);
    }

    @Test
    @SneakyThrows
    public void validarPolygonTipoMultiPoligono() {
        Method metodo = ArquivoService.class.getDeclaredMethod("validarPolygon", String.class);
        metodo.setAccessible(true);
        assertTrue((Boolean) metodo.invoke(arquivoService, "MultiPolygon"));
    }

    @Test
    @SneakyThrows
    public void validarPolygonTipoPoligono() {
        Method metodo = ArquivoService.class.getDeclaredMethod("validarPolygon", String.class);
        metodo.setAccessible(true);
        assertTrue((Boolean) metodo.invoke(arquivoService, "Polygon"));
    }

    @Test
    @SneakyThrows
    public void remover() {
        mockStatic(ArquivoUtils.class);
        when(arquivoRepository.findOne(anyLong())).thenReturn(arquivo);
        when(ArquivoUtils.remover(anyString(), anyString())).thenReturn(true);
        arquivoService.remover(asList(arquivoDTO));
    }

    @Test
    @SneakyThrows
    public void  gravarArquivosTemporariosVazio() {
        mockStatic(com.google.common.io.Files.class);
        when(multipartFile.isEmpty()).thenReturn(true);
        when(com.google.common.io.Files.createTempDir()).thenReturn(file);
        Method metodo = ArquivoService.class.getDeclaredMethod("gravarArquivosTemporarios", MultipartFile.class);
        metodo.setAccessible(true);
        List<File> arquivosEsperados = (List<File>) metodo.invoke(arquivoService, multipartFile);
        assertTrue(arquivosEsperados.isEmpty());
    }

    @Test
    @SneakyThrows
    public void lerShapefileZipVazio() {
        when(zipInputStream.getNextEntry()).thenReturn(zipEntry, null);
        when(zipEntry.isDirectory()).thenReturn(true);
        Method metodo = ArquivoService.class.getDeclaredMethod("lerShapefileZip", List.class, File.class, ZipInputStream.class);
        metodo.setAccessible(true);
        metodo.invoke(arquivoService, asList(file), file, zipInputStream);
    }

    @Test
    @SneakyThrows
    public void validarArquivoContemGeometriaPoligonoGerandoErro() {
        mockStatic(FileDataStoreFinder.class);
        when(FileDataStoreFinder.getDataStore(any(File.class))).thenThrow(IOException.class);
        Method metodo = ArquivoService.class.getDeclaredMethod("validarArquivoContemGeometriaPoligono", File.class);
        metodo.setAccessible(true);
        assertNull(metodo.invoke(arquivoService, file));
    }

    @Test
    @SneakyThrows
    public void validarArquivoContemGeometriaPoligonoIteratorNulo() {
        mockStatic(FileDataStoreFinder.class);
        when(FileDataStoreFinder.getDataStore(any(File.class))).thenReturn(fileDataStore);
        when(fileDataStore.getFeatureSource()).thenReturn(simpleFeatureSource);
        when(simpleFeatureSource.getFeatures()).thenReturn(simpleFeatureCollection);
        when(simpleFeatureCollection.features()).thenReturn(null);
       /* when(simpleFeatureIterator.hasNext()).thenReturn(true, false);
        when(simpleFeatureIterator.next()).thenReturn(simpleFeature);*/
        Method metodo = ArquivoService.class.getDeclaredMethod("validarArquivoContemGeometriaPoligono", File.class);
        metodo.setAccessible(true);
        assertNull(metodo.invoke(arquivoService, file));
    }


}
