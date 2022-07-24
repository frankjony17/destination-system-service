package br.com.company.fks.destinacao.dominio.enums;

import java.io.Serializable;

public enum AutarquiasFundacoesEnum implements Serializable, BasicEnum{


    AGENCIA_NACIONAL_DE_AGUAS(1 ,"Agência Nacional de Águas (ANA)","1"),
    AGENCIA_NACIONAL_DE_AVIACAO_CIVIL(2 ,"Agência Nacional de Aviação Civil (ANAC)","2"),
    AGENCIA_NACIONAL_DE_TELECOMUNICACOES(3,"Agência Nacional de Telecomunicações (ANATEL)","3" ),
    AGENCIA_NACIONAL_DO_CINEMA(4,"Agência Nacional do Cinema (ANCINE)","4"),
    AGENCIA_NACIONAL_DE_ENERGIA_ELETRICA(5,"Agência Nacional de Energia Elétrica (ANEEL)","5"),
    AGENCIA_NACIONAL_DO_PETROLEO_GAS_NATURAL_E_BIOCOMBUSTIVEIS(6,"Agência Nacional do Petróleo, Gás Natural e Biocombustíveis (ANP)","6"),
    AGENCIA_NACIONAL_DE_SAUDE_SUPLEMENTAR(7,"Agência Nacional de Saúde Suplementar (ANS)","7"),
    AGENCIA_NACIONAL_DE_TRANSPORTES_AQUAVIARIOS(8,"Agência Nacional de Transportes Aquaviários (ANTAQ)","8"),
    AGENCIA_NACIONAL_DE_TRANSPORTES_TERRESTRES(9,"Agência Nacional de Transportes Terrestres (ANTT)","9"),
    AGENCIA_NACIONAL_DE_VIGILANCIA_SANITARIA(10,"Agência Nacional de Vigilância Sanitária (ANVISA)","10"),
    AGENCIA_NACIONAL_DE_MINERACAO(11,"Agência Nacional de Mineração (ANM)","11"),
    AGENCIA_DE_DESENVOLVIMENTO_DA_AMAZONIA(12,"Agência de Desenvolvimento da Amazônia (SUDAM)","12"),
    AGENCIA_ESPACIAL_BRASILEIRA(13,"Agência Espacial Brasileira (AEB)","13"),

    CONSELHO_DE_ARQUITETURA_E_URBANISMO_DO_BRASIL(14,"Conselho de Arquitetura e Urbanismo do Brasil","14"),
    CONSELHO_FEDERAL_DE_ADMINISTRACAO(15,"Conselho Federal de Administração","15"),
    CONSELHO_FEDERAL_DE_BIBLIOTECONOMIA(16,"Conselho Federal de Biblioteconomia","16"),
    CONSELHO_FEDERAL_DE_BIOLOGIA(17,"Conselho Federal de Biologia","17"),
    CONSELHO_FEDERAL_DE_BIOMEDICINA(18,"Conselho Federal de Biomedicina","18"),
    CONSELHO_FEDERAL_DE_CONTABILIDADE(19,"Conselho Federal de Contabilidade","19"),
    CONSELHO_FEDERAL_DE_CORRETORES_DE_IMOVEIS(20,"Conselho Federal de Corretores de Imóveis","20"),
    CONSELHO_FEDERAL_DE_ECONOMIA(21,"Conselho Federal de Economia","21"),
    CONSELHO_FEDERAL_DE_ECONOMISTAS_DOMESTICOS(22,"Conselho Federal de Economistas Domésticos","22"),
    CONSELHO_FEDERAL_DE_EDUCACAO_FISICA(23,"Conselho Federal de Educação Física","23"),
    CONSELHO_FEDERAL_DE_ENFERMAGEM(24,"Conselho Federal de Enfermagem","24"),
    CONSELHO_FEDERAL_DE_ENGENHARIA_E_AGRONOMIA(25,"Conselho Federal de Engenharia e Agronomia","25"),
    CONSELHO_FEDERAL_DE_ESTATISTICA(26,"Conselho Federal de Estatística","26"),
    CONSELHO_FEDERAL_DE_FARMACIA(27,"Conselho Federal de Farmácia","27"),
    CONSELHO_FEDERAL_DE_FISIOTERAPIA_E_TERAPIA_OCUPACIONAL(28,"Conselho Federal de Fisioterapia e Terapia Ocupacional","28"),
    CONSELHO_FEDERAL_DE_FONOAUDIOLOGIA(29,"Conselho Federal de Fonoaudiologia","29"),
    CONSELHO_FEDERAL_DE_MEDICINA_VETERINARIA(30,"Conselho Federal de Medicina Veterinária","30"),
    CONSELHO_FEDERAL_DE_MEDICINA(31,"Conselho Federal de Medicina","31"),
    CONSELHO_FEDERAL_DE_MUSEOLOGIA(32,"Conselho Federal de Museologia","32"),
    CONSELHO_FEDERAL_DE_NUTRICIONISTAS(33,"Conselho Federal de Nutricionistas","33"),
    CONSELHO_FEDERAL_DE_ODONTOLOGIA(34,"Conselho Federal de Odontologia","34"),
    CONSELHO_FEDERAL_DE_PSICOLOGIA(35,"Conselho Federal de Psicologia","35"),
    CONSELHO_FEDERAL_DE_QUIMICA(36,"Conselho Federal de Química","36"),
    CONSELHO_FEDERAL_DE_SERVICO_SOCIAL(37,"Conselho Federal de Serviço Social","37"),
    CONSELHO_FEDERAL_DOS_DESPACHANTES_DOCUMENTALISTAS_DO_BRASIL(38,"Conselho Federal dos Despachantes Documentalistas do Brasil","38"),
    CONSELHO_FEDERAL_DOS_REPRESENTANTES_COMERCIAIS(39,"Conselho Federal dos Representantes Comerciais","39"),
    CONSELHO_FEDERAL_DE_PROFISSIONAIS_DE_RELACOES_PUBLICAS(40,"Conselho Federal de Profissionais de Relações Públicas","40"),
    CONSELHO_NACIONAL_DE_TECNICOS_EM_RADIOLOGIA(41,"Conselho Nacional de Técnicos em Radiologia","41"),
    ORDEM_DOS_ADVOGADOS_DO_BRASIL(42,"Ordem dos Advogados do Brasil","42"),
    ASSOCIACAO_BRASILEIRA_DE_AGENCIAS_DE_PUBLICIDADE(43,"Associação Brasileira de Agências de Publicidade (ABAP)","43"),
    ORDEM_DOS_MUSICOS_DO_BRASIL(44,"Ordem dos Músicos do Brasil","44");




    private Integer codigo;
    private String codigoUg;
    private String descricao;


    AutarquiasFundacoesEnum(Integer codigo, String descricao, String codigoUg) {
        this.codigo = codigo;
        this.codigoUg = codigoUg;
        this.descricao = descricao;
    }


    @Override
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoUg() {
        return codigoUg;
    }

    public void setCodigoUg(String codigoUg) {
        this.codigoUg = codigoUg;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
