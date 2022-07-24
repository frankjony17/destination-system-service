package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by basis on 28/11/17.
 */
public enum DescricaoParentescoEnum implements BasicEnum {

    CONJUGE_COMPANHEIRO("Cônjuge/companheiro(a)", 1),
    PAI_MAE_PADRASTO_MADRASTA("Pai/mãe/padrasto/madrasta", 2),
    IRMAOS_SOLTEIROS("Irmãos solteiros", 3),
    FILHO_SOLTEIRO_ENTEADOS_SOLTEIROS("Filho(a) solteiro/enteados solteiros", 4),
    MENORES_TUTELADOS("Menores tutelados", 5),
    OUTRO("Outro", 6);

    private String descricao;
    private Integer codigo;

    DescricaoParentescoEnum(String descricao, Integer codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public Integer getCodigo() {
        return this.codigo;
    }
}
