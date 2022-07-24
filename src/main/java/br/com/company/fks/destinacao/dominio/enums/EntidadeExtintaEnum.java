package br.com.company.fks.destinacao.dominio.enums;

import lombok.Getter;
import java.io.Serializable;

@Getter
public enum EntidadeExtintaEnum implements Serializable{
    MINISTERIO_DA_ADM(1L, "Ministério da Adm. Federal e Reforma do Estado"),
    MINISTERIO_DA_AGRICULTURA(2L,"Ministério da Agricultura"),
    MINISTERIO_DO_BEM_ESTAR(3L,"Ministério do Bem-Estar Social"),
    MINISTERIO_DA_CIENCIA_TECNOLOGIA(4L, "Ministério da Ciência e Tecnologia"),
    MINISTERIO_DAS_COMUNICACOES(5L, "Ministério das Comunicações"),
    MINISTERIO_DA_CULTURA(6L, "Ministério da Cultura"),
    MINISTERIO_DESENVOLVIMENTO(7L, "Ministério do Desenvolvimento da Indústria e Comércio"),
    MINISTERIO_DA_FAZENDA(8L, "Ministério da Fazenda"),
    MINISTERIO_DA_INTEGRACAO(9L, "Ministério da Integração Regional"),
    MINISTERIO_DO_INTERIOR(10L, "Ministério do Interior"),
    MINISTERIO_DAS_MINAS(11L, "Ministério das Minas e Energia"),
    MINISTERIO_DA_PREVIDENICA(12L, "Ministério da Previdência e Assistência Social"),
    MINISTERIO_DO_TRABALHO(13L,"Ministério do Trabalho"),
    MINISTERIO_DO_TRABALHO_E_PREVIDENCIA(14L,"Ministério do Trabalho e Previdência Social"),
    MINISTERIO_DOS_TRANSPORTES(15L, "Ministério dos Transportes"),
    SEPLAN(16L, "SEPLAN – Secret. de Planejamento e Coordenação/PR"),
    SPE(17L, "SPE – Secretaria de Projetos Especiais"),
    TOCANTINS(18L, "TOCANTINS – Estrada de Ferro Tocantins"),
    DNOS(19L, "DNOS – Depart. Nacional de Obras e Saneamento"),
    IAA(20L, "IAA – Instituto do Açúcar e do Álcool"),
    IBC(21L, "IBC – Instituto Brasileiro do Café"),
    INAN(22L, "INAN – Instituto Nacional de Alimentação e Nutrição"),
    SUDESUL(23L, "SUDESUL - Super. Desenvolvimento da Região Sul"),
    SUNAB(24L,"SUNAB – Superintendência Nacional de Abastecimento Lei 9.618/98 Ministério Fazenda"),
    INDESP(25L,"INDESP – Inst. Nac. do Desenvolvimento do Desporto"),
    CBIA(26L, "CBIA – Fund. Centro Brasileiro p/ Infância e Adolescência"),
    FAE(27L, "FAE – Fundação de Assistência ao Estudante"),
    FCB(28L,"FCB – Fundação do Cinema Brasileiro"),
    FPS(29L, "FPS – Fundação das Pioneiras Sociais"),
    FUNARTE(30L, "FUNARTE – Fundação Nacional de Artes"),
    FUDACEN(31L, "FUNDACEN – Fundação Nacional de Artes Cênicas"),
    LBA(32L, "LBA – Fundação Legião Brasileira de Assistência"),
    MUSEU_DO_CAFE(33L, "MUSEU DO CAFÉ – Fundação Museu do Café"),
    PRO_LEITURA(34L, "PRO-LEITURA – Fundação Nacional Pró-Leitura"),
    PRO_MEMORIA(35L, "PRO-MEMÓRIA – Fundação Nacional Pró-Memória"),
    CTI(36L, "CTI – Fundação Centro Tecnológico para Informática"),
    CNCC(37L, "CNCC – Campanha Nacional de Combate ao Câncer"),
    CNCT(38L, "CNCT – Campanha Nacional contra a Tuberculose"),
    CNSM(39L, "CNSM – Campanha Nacional de Saúde Mental Dec.");

    private Long codigo;
    private String descricao;

    EntidadeExtintaEnum(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static EntidadeExtintaEnum getByCodigo(Long codigo) {
        EntidadeExtintaEnum entidadeExtintaEnum= null;
        for (EntidadeExtintaEnum value : values()) {
            if (value.getCodigo().equals(codigo)) {
                entidadeExtintaEnum = value;
                break;
            }
        }
        return entidadeExtintaEnum;
    }


}
