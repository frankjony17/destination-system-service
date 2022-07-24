package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by raphael on 08/12/16.
 */
public enum PerfilEnum {

    TECNICO("DESTINACAO.TECNICO"),
    CHEFIA("DESTINACAO.CHEFIA"),
    SUPERINTENDENTE("DESTINACAO.SUPERINTENDENTE"),
    SECRETARIO("DESTINACAO.SECRETARIO");

    private String nome;

    /**
     * Método construtor da classe, que inicializa o atributo nome
     * @param nome
     */
    PerfilEnum(String nome){
        this.nome = nome;
    }

    /**
     * Método que busca um PerfilEnum pelo nome
     * @param nome
     * @return PerfilEnum encontrado pelo nome
     */
    public static PerfilEnum buscarPorNome(String nome){
        for (PerfilEnum perfilEnum : PerfilEnum.values()) {
            if(perfilEnum.getNome().equals(nome)){
                return perfilEnum;
            }
        }

        return null;
    }

    /**
     * Método get do atributo nome
     * @return
     */
    public String getNome() {
        return nome;
    }
}
