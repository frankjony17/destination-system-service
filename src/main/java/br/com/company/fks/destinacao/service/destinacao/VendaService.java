package br.com.company.fks.destinacao.service.destinacao;

import br.com.company.fks.destinacao.dominio.entidades.Venda;
import br.com.company.fks.destinacao.repository.VendaRepository;
import br.com.company.fks.destinacao.service.CancelarImovelService;
import br.com.company.fks.destinacao.service.DadosResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável por salvar dados Vendas
 * Created by Basis Tecnologia on 20/10/2016.
 */
@Service
public class VendaService extends DestinacaoService<Venda> {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private DadosResponsavelService dadosResponsavelService;

    @Autowired
    private CancelarImovelService cancelarImovelService;

    /**
     * Salva dados Venda
     * @param venda
     * @return Venda gravada no banco de dados
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Venda salvarDadosEspecificos(Venda venda) {
        venda.setDadosResponsavel(dadosResponsavelService.salvar(venda.getDadosResponsavel(),false));
        Venda vendaSalva = vendaRepository.save(venda);
        cancelarImovelService.cancelarImovel(venda);
        return vendaSalva;
    }
}
