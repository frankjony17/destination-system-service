package br.com.company.fks.destinacao.dominio.enums;

/**
 * Created by diego on 09/12/16.
 */
public enum EmailEnum {

    ANALISE_ENVIADA_SUPERINTENDENTE("email/analiseEnviadaSuperintendente.html",
            "[e-SPU] [Confirmação de recebimento]  $nome_servico$",
            "servidor",
            "data_envio",
            "hora_envio"),
    ANALISE_AGUARDANDO_REQUERENTE("email/pendeciaRequerimento.html",
                                  "[e-SPU] [Notificação de pendência] $nome_servico$",
                                  "nome_requerimento",
                                  "numero_atendimento",
                                  "nome_requerente"),
    CANCELAMENTO("email/cancelamentoRequerimento.html",
            "[e-SPU] [Notificação de cancelamento]  $nome_servico$",
            "nome_requerimento",
            "numero_atendimento",
            "nome_requerente");

    private String path;

    private String assunto;

    private String [] params;

    EmailEnum(String path, String assunto, String... params){
        this.path = path;
        this.assunto = assunto;
        this.params = params;
    }

    public void setAssunto(String assunto){
        this.assunto = assunto;
    }


    public String getPath() {
        return path;
    }

    public String getAssunto() {
        return assunto;
    }

    public String[] getParams() {
        return params.clone();
    }
}
