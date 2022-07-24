package br.com.company.fks.destinacao.dominio.enums;

import java.io.Serializable;

/**
 * Created by jonatas on 04/05/18.
 */
public enum DadosdoUsoEnum implements Serializable, BasicEnum {
    SEMUSOVAGO("Sem uso/Vago", 0),
    SEMINFORMACAO("Sem informação", 1),
    SEMUSODEFINIDO("Sem uso definido", 2),
    SEDEUNIDADEORGAO("Sede/Unidade de Entidade/Órgão (público ou privado)", 3),
    UNIDADEINSTALACAOSEGURANCADEFESA("Sede/Unidade de Entidade/Órgão (público ou privado)", 4),
    UNIDADEESPECIALPRESERVACAOPROTECAO("Unidade Especial de Preservação/Proteção", 5),
    UNIDADEINSTALACAOAGROPECUARIA("Unidade/instalação de Agropecuária (ex.: fazenda, sítio etc.)", 6),
    UNIDADEINSTALACAOINFRAESTRUTURAAQUICULTURAPISICICULTURA("Unidade/instalação de Aquicultura/Piscicultura", 7),
    UNIDADEINSTALACAOINFRAESTRUTURAURBANA("Unidade/instalação de Infraestrutura Urbana", 8),
    UNIDADEINSTALACAOINFRAESTRUTURAENERGETICA("Unidade/instalação de Infraestrutura Energética", 9),
    UNIDADEINSTALACAOINFRAESTRUTURACOMUNICACAO("Unidade/instalação de Infraestrutura de Comunicação", 10),
    UNIDADEINSTALACAOINFRAESTRUTURAAEROPORTUARIA("Unidade/instalação de Infraestrutura Aeroportuária", 11),
    UNIDADEINSTALACAOINFRAESTRUTURARODOVIARIA("Unidade/instalação de Infraestrutura Rodoviária", 12),
    UNIDADEINSTALACAOINFRAESTRUTURAFERROVIARIA("Unidade/instalação de Infraestrutura Ferroviária", 13),
    UNIDADEINSTALACAOINFRAESTRUTURAHIDROVIARIANAUTICA("Unidade/instalação de Infraestrutura Hidroviária e Náutica", 14),
    UNIDADEINSTALACAOSAUDEASSISTENCIASOCIAL("Unidade/instalação de Saúde e Assistência Social", 15),
    UNIDADEINSTALACAOEDUCACAOCULTURAESPORTELAZER("Unidade/instalação de Educação, Cultura, Esporte e Lazer", 16),
    UNIDADEINSTALACAOINDUSTRIALCOMERCIALSERVICOS("Unidade/instalação Industrial, Comercial e de Serviços", 17),
    RESIDENCIAL("Residencial", 18),
    COMUNIDADETRADICIONAL("Comunidade tradicional", 19),
    UNIDADEINSTALACAORELIGIOSO("Unidade/instalação de uso religioso", 20),
    UNIDADEINSTALACAOAPOIOADMINISTRACAOPUBLICA("Unidade/instalação de apoio à Administração Pública", 21);

    private String descricao;
    private Integer codigo;

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public Integer getCodigo() {
        return codigo;
    }

    DadosdoUsoEnum( String descricao, Integer codigo){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static DadosdoUsoEnum getByDescricao(String descricao){
        DadosdoUsoEnum dadosdoUsoEnum = null;
        for(DadosdoUsoEnum desc : values()){
            if(desc.getDescricao().equalsIgnoreCase(descricao)){
                dadosdoUsoEnum = desc;
                break;
            }
        }
        return dadosdoUsoEnum;
    }
}
