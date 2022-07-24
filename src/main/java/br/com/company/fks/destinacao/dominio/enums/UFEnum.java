package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by basis on 14/12/16.
 */
public enum UFEnum {

    AC("AC","Acre"),
    AL("AL","Alagoas"),
    AM("AM","Amazonas"),
    AP("AP","Amapá"),
    BA("BA","Bahia"),
    CE("CE","Ceará"),
    DF("DF","Distrito Federal"),
    ES("ES","Espírito Santo"),
    GO("GO","Goiás"),
    MA("MA","Maranhão"),
    MG("MG","Minas Gerais"),
    MS("MS","Mato Grosso do Sul"),
    MT("MT","Mato Grosso"),
    PA("PA","Pará"),
    PB("PB","Paraíba"),
    PE("PE","Pernambuco"),
    PI("PI","Piauí"),
    PR("PR","Paraná"),
    RJ("RJ","Rio de Janeiro"),
    RN("RN","Rio Grande do Norte"),
    RO("RO","Rondônia"),
    RR("RR","Roraima"),
    RS("RS","Rio Grande do Sul"),
    SC("SC","Santa Catarina"),
    SE("SE","Sergipe"),
    SP("SP","São Paulo"),
    TO("TO","Tocantins");

    private String sigla;
    private String descricao;

    /**
     * Método Construtor que inicializa os valores
     * @param sigla
     * @param descricao
     */
    UFEnum(String sigla, String descricao) {
        this.descricao = descricao;
        this.sigla = sigla;
    }

    /**
     * Método que retorna um UFEnum pela sigla informada no parâmetro
     * @param sigla
     * @return
     */
    public static UFEnum getPorCodigo(String sigla) {
        for (UFEnum tipo : UFEnum.values()) {
            if(tipo.sigla.equals(sigla)){
                return tipo;
            }
        }
        return null;
    }

    /**
     * Método get do atributo descricao
     * @return
     */
    public String getDescricao(){
        return this.descricao;
    }

    /**
     * Método get do atributo sigla
     * @return
     */
    public String getSigla() {
        return this.sigla;
    }
}
