package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by jonatas on 03/05/18.
 */
public enum ClassificacaoImovelEnum implements  BasicEnum{
    SEMINFORMACAO("Sem Informação", 1),
    DOMINIAL("Dominial", 2),
    USOESPECIAL("Uso Especial", 3),
    USOCOMUMDOPOVO("Uso Comum do Povo", 4);

    private String descricao;
    private Integer codigo;

    public String getDescricao() {
        return descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    ClassificacaoImovelEnum(String descricao, Integer codigo) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static ClassificacaoImovelEnum getByCodigo(Long codigo) {
        ClassificacaoImovelEnum classificacaoImovelEnum = null;
        for (ClassificacaoImovelEnum value : values()) {
            if (value.getCodigo().longValue() == codigo) {
                classificacaoImovelEnum = value;
                break;
            }
        }
        return classificacaoImovelEnum;
    }
}
