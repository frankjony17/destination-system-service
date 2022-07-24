/*INICIO INSERTS TB_TIPO_ATO_AUTORIZATIVO*/
INSERT INTO destinacao.tb_tipo_ato_autorizativo (id_tipo_ato_autorizativo, ds_tipo_ato_autorizativo)
    VALUES(1, 'Portaria');
INSERT INTO destinacao.tb_tipo_ato_autorizativo (id_tipo_ato_autorizativo, ds_tipo_ato_autorizativo)
    VALUES(2, 'Resolução');
INSERT INTO destinacao.tb_tipo_ato_autorizativo (id_tipo_ato_autorizativo, ds_tipo_ato_autorizativo)
    VALUES(3, 'Lei/Decreto-lei');
INSERT INTO destinacao.tb_tipo_ato_autorizativo (id_tipo_ato_autorizativo, ds_tipo_ato_autorizativo)
    VALUES(4, 'Decreto');
INSERT INTO destinacao.tb_tipo_ato_autorizativo (id_tipo_ato_autorizativo, ds_tipo_ato_autorizativo)
    VALUES(5, 'Despacho');
INSERT INTO destinacao.tb_tipo_ato_autorizativo (id_tipo_ato_autorizativo, ds_tipo_ato_autorizativo)
    VALUES(6, 'Outro (Especificar)');
/*FIM INSERTS TB_TIPO_ATO_AUTORIZATIVO*/


/*INICIO INSERTS TB_STATUS_DESTINACAO*/
INSERT INTO destinacao.tb_status_destinacao (id_status_destinacao, ds_status_destinacao)
    VALUES(1, 'Ativo');
INSERT INTO destinacao.tb_status_destinacao (id_status_destinacao, ds_status_destinacao)
    VALUES(2, 'Cancelado');
INSERT INTO destinacao.tb_status_destinacao (id_status_destinacao, ds_status_destinacao)
    VALUES(3, 'Pendente');
INSERT INTO destinacao.tb_status_destinacao (id_status_destinacao, ds_status_destinacao)
    VALUES(4, 'Rascunho');
/*FIM INSERTS TB_STATUS_DESTINACAO*/

/*INICIO INSERTS TB_DESPACHOS*/
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(1, 'DEFAULT', 'Atende aos requisitos');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(2, 'DEFAULT', 'Não atende aos requisitos');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(3, 'DEFAULT', 'Retornar pendência para o requerente');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(4, 'DEFAULT', 'Cancelar por erro/duplicidade');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(5, 'DEFAULT', 'Solicitar Manifestação de outra área');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(9, 'SECRETARIO', 'Aprovo a adesão do município. Publique-se o extrato no Diário Oficial da União');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(10, 'SECRETARIO', 'Aprovo o indeferimento');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(11, 'SECRETARIO', 'Retornar ao Superintendente');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(6, 'CHEFIA', 'De acordo com a avaliação técnica');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(7, 'CHEFIA', 'Retornar para análise técnica');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(8, 'CHEFIA', 'Alterar avaliação técnica');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(12, 'SUPERINTENDENTE', 'Retornar para análise chefia');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(13, 'SUPERINTENDENTE', 'De acordo com a avaliação técnica');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(14, 'SUPERINTENDENTE', 'Retornar para análise técnica');
INSERT INTO destinacao.tb_despacho (id_despacho, tp_despacho, ds_descricao)
    VALUES(15, 'SUPERINTENDENTE', 'Alterar avaliação técnica');
/*FIM INSERTS TB_DESPACHOS*/

/*INICIO INSERTS TB_TIPO_PAGAMENTO*/
INSERT INTO destinacao.tb_tipo_pagamento (id_tipo_pagamento, ds_tipo_pagamento)
    VALUES(1, 'À Vista');
INSERT INTO destinacao.tb_tipo_pagamento (id_tipo_pagamento, ds_tipo_pagamento)
    VALUES(2, 'Parcelado');
/*FIM INSERTS TB_TIPO_PAGAMENTO*/

/*INICIO INSERTS TB_TIPO_PERIOCIDADE*/
INSERT INTO destinacao.tb_tipo_periocidade (id_tipo_periocidade, ds_tipo_periocidade)
    VALUES(1, 'Anual');
INSERT INTO destinacao.tb_tipo_periocidade (id_tipo_periocidade, ds_tipo_periocidade)
    VALUES(2, 'Mensal');
/*FIM INSERTS TB_TIPO_PERIOCIDADE*/

/*INICIO INSERTS TB_TIPO_MOEDA*/
INSERT INTO destinacao.tb_tipo_moeda (id_tipo_moeda, ds_tipo_moeda)
    VALUES(1, 'Dolar');
INSERT INTO destinacao.tb_tipo_moeda (id_tipo_moeda, ds_tipo_moeda)
    VALUES(2, 'Real');
/*FIM INSERTS TB_TIPO_MOEDA*/

/*INICIO INSERTS TB_TIPO_REAJUSTE*/
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(1, 'IPCA');
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(2, 'IPCA-E');
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(3, 'IPC-BRASIL');
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(4, 'IPC-SP');
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(5, 'INPC');
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(6, 'IGP-M');
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(7, 'IGP-DI');
INSERT INTO destinacao.tb_tipo_reajuste (id_tipo_reajuste, ds_tipo_reajuste)
    VALUES(8, 'SELIC');
/*FIM INSTERTS TB_TIPO_REAJUSTE*/

/*INICIO INSERTS TB_TIPO_JUROS*/
INSERT INTO destinacao.tb_tipo_juro (id_tipo_juro, ds_tipo_juro)
    VALUES(1, 'Manual');
INSERT INTO destinacao.tb_tipo_juro (id_tipo_juro, ds_tipo_juro)
    VALUES(2, 'Índice');
/*FIM INSERTS TB_TIPO_JUROS*/

/*INICIO INSERTS TB_TIPO_LICITACAO*/
INSERT INTO destinacao.tb_tipo_licitacao (id_tipo_licitacao, ds_tipo_licitacao)
    VALUES(1, 'Dispensa');
INSERT INTO destinacao.tb_tipo_licitacao (id_tipo_licitacao, ds_tipo_licitacao)
    VALUES(2, 'Inexigibilidade');
INSERT INTO destinacao.tb_tipo_licitacao (id_tipo_licitacao, ds_tipo_licitacao)
    VALUES(3, 'Concorrência');
INSERT INTO destinacao.tb_tipo_licitacao (id_tipo_licitacao, ds_tipo_licitacao)
    VALUES(4, 'Leilão');
/*FIM INSERTS TB_TIPO_LICITACAO*/

/*INICIO INSERTS TB_TIPO_POSSE*/
INSERT INTO destinacao.tb_tipo_posse (id_tipo_posse, ds_tipo_posse)
    VALUES(1, 'Individual');
INSERT INTO destinacao.tb_tipo_posse (id_tipo_posse, ds_tipo_posse)
    VALUES(2, 'Coletivo sem entidade representativa');
INSERT INTO destinacao.tb_tipo_posse (id_tipo_posse, ds_tipo_posse)
    VALUES(3, 'Coletivo com entidade representativa');
/*FIM INSERTS TB_TIPO_POSSE*/

/*INICIO INSERTS TB_TIPO_MODALIDADE*/
INSERT INTO destinacao.tb_tipo_modalidade (id_tipo_modalidade, ds_tipo_modalidade)
    VALUES(1, 'Individual');
INSERT INTO destinacao.tb_tipo_modalidade (id_tipo_modalidade, ds_tipo_modalidade)
    VALUES(2, 'Coletivo');
/*FIM INSERTS TIPO MODALIDADE*/

/*INICIO INSERTS TB_TIPO_CONCESSAO*/
INSERT INTO destinacao.tb_tipo_concessao (id_tipo_concessao, ds_tipo_concessao)
    VALUES(1, 'Gratuito');
INSERT INTO destinacao.tb_tipo_concessao (id_tipo_concessao, ds_tipo_concessao)
    VALUES(2, 'Oneroso');
/*FIM INSERTS TIPO CONCESSAO*/

/*INICIO INSERTS TB_TIPO_INSTRUMENTO*/
INSERT INTO destinacao.tb_tipo_instrumento (id_tipo_instrumento, ds_tipo_instrumento)
    VALUES(1, 'Transferência ');
INSERT INTO destinacao.tb_tipo_instrumento (id_tipo_instrumento, ds_tipo_instrumento)
    VALUES(2, 'Doação');
/*FIM INICIO INSERTS TB_TIPO_INSTRUMENTO*/

/*INICIO INSERTS TB_TIPO_DESTINACAO*/
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(1, 'Doação');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(3, 'Posse Informal');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(2, 'Venda');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(4, 'Cuem');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(5, 'Cdru');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(6, 'Cessão Gratuita');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(7, 'Cessão Onerosa-Em Condições Especiais');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(8, 'Entrega');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(9, 'Locação');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(10, 'Termo Entrega');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(11, 'Transferência de Gestão/Titularidade');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(12, 'Sem Utilização');
INSERT INTO destinacao.tb_tipo_destinacao (id_tipo_destinacao, ds_tipo_destinacao)
    VALUES(13, 'Uso Próprio');
/*FIM INSERTS TB_TIPO_DESTINACAO*/


/*INICIO INSERTS TB_ITEM_VERIFICACAO_CHECK_LIST*/
INSERT INTO destinacao.tb_item_verificacao_check_list (id_item_verificacao_check_list, ds_item_verificacao_check_list, co_fundamento_legal)
    VALUES(1, 'Os dados está de acordo com todas as exigencias?', 11);
INSERT INTO destinacao.tb_item_verificacao_check_list (id_item_verificacao_check_list, ds_item_verificacao_check_list, co_fundamento_legal)
    VALUES(2, 'Os dados está de acordo com todas as exigencias?', 12);
/*FIM INSERTS TB_ITEM_VERIFICACAO_CHECK_LIST*/

/*INICIO INSERTS TB_STATUS_ANALISE_TECNICA*/
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(1, 'Aguardando Análise Técnica');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(2, 'Rascunho');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(3, 'Em Análise Técnica');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(4, 'Aguardando Manifestação da Chefia');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(5, 'Aguardando Manifestação do Superintendente');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(6, 'Aguardando Manifestação do Secretário');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(7, 'Enviado para publicação');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(8, 'Publicado');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(10, 'Cancelado');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(11, 'Indeferido');
INSERT INTO destinacao.tb_status_analise_tecnica (id_status_analise_tecnica, ds_descricao)
    VALUES(9, 'Aguardando Requerente');
/*FIM INSERTS TB_STATUS_ANALISE_TECNICA*/

/*INICIO INSERTS TB_ANALISE_TECNICA*/
INSERT INTO destinacao.tb_analise_tecnica (id_analise_tecnica, ic_documento_pendente, ds_obs_documento_pendente, ds_info_complementar, ds_info_complementar_espeficia, co_fundamento_legal, co_requerimento, co_status_analise_tecnica, co_usuario, co_publicacao, dt_envio_publicacao)
    VALUES(125, true, 'fasdf', NULL, 'fsadfasdf', 11, 286, 5, 1, NULL, NULL);
INSERT INTO destinacao.tb_analise_tecnica (id_analise_tecnica, ic_documento_pendente, ds_obs_documento_pendente, ds_info_complementar, ds_info_complementar_espeficia, co_fundamento_legal, co_requerimento, co_status_analise_tecnica, co_usuario, co_publicacao, dt_envio_publicacao)
    VALUES(126, true, 'fasdf', NULL, 'fsadfasdf', 12, 287, 5, 1, NULL, NULL);
INSERT INTO destinacao.tb_analise_tecnica (id_analise_tecnica, ic_documento_pendente, ds_obs_documento_pendente, ds_info_complementar, ds_info_complementar_espeficia, co_fundamento_legal, co_requerimento, co_status_analise_tecnica, co_usuario, co_publicacao, dt_envio_publicacao)
    VALUES(127, true, 'fasdf', NULL, 'fsadfasdf', 12, 287, 5, 6, NULL, NULL);
/*FIM INSERTS TB_ANALISE_TECNICA*/

/*INICIO INSERTS TB_ITEM_VERIFICACAO_ESPECIFICA*/
INSERT INTO destinacao.tb_item_verificacao_especificacao (id_item_verificacao_especifica, co_item_verif_check_list, ic_resposta, ds_observacao, co_analise_tecnica)
    VALUES(6, 1, true, 'fasdf', 125);
INSERT INTO destinacao.tb_item_verificacao_especificacao (id_item_verificacao_especifica, co_item_verif_check_list, ic_resposta, ds_observacao, co_analise_tecnica)
    VALUES(7, NULL, true, 'fasdf', 126);
/*FIM INSERTS TB_ITEM_VERIFICACAO_ESPECIFICA*/

/*INICIO INSERTS TB_HISTORICO_ANALISE_TECNICA*/
INSERT INTO destinacao.tb_historico_analise_tecnica (id_historico_analise_tecnica, co_analise_tecnica, dt_alteracao, ds_justificativa, co_usuario, ds_nome_usuario, co_status_anaise_tecnica_anterior, co_status_analise_tecnica_atual)
    VALUES(285, 125, '2016-12-29 09:09:47.186', 'fasdfadsf', 1, 'Lucas Bitencourt', NULL, 5);
/*FIM INSERTS TB_HISTORICO_ANALISE_TECNICA*/

/*INICIO INSERTS TB_ARQUIVO*/
INSERT INTO destinacao.tb_arquivo (id_arquivo, ds_nome, ds_extensao, ds_content_type, nu_tamanho, dt_data, dir_arquivo)
    VALUES(1, 'teste.jpeg', '.jpeg', NULL, 27109, '2016-12-01', 'servico/src/test/resources/arquivos');
INSERT INTO destinacao.tb_arquivo (id_arquivo, ds_nome, ds_extensao, ds_content_type, nu_tamanho, dt_data, dir_arquivo)
    VALUES(7, 'teste.jpeg', '.jpeg', NULL, 27109, '2016-12-01', 'resources/arquivos');
INSERT INTO destinacao.tb_arquivo (id_arquivo, ds_nome, ds_extensao, ds_content_type, nu_tamanho, dt_data, dir_arquivo)
    VALUES(8, 'teste.jpeg', '.jpeg', NULL, 27109, '2016-12-01', 'resources/arquivos');
INSERT INTO destinacao.tb_arquivo (id_arquivo, ds_nome, ds_extensao, ds_content_type, nu_tamanho, dt_data, dir_arquivo)
    VALUES(9, 'teste.jpeg', '.jpeg', NULL, 27109, '2016-12-01', 'resources/arquivos');
INSERT INTO destinacao.tb_arquivo (id_arquivo, ds_nome, ds_extensao, ds_content_type, nu_tamanho, dt_data, dir_arquivo)
    VALUES(10, 'teste.jpeg', '.jpeg', NULL, 27109, '2016-12-01', 'resources/arquivos');
INSERT INTO destinacao.tb_arquivo (id_arquivo, ds_nome, ds_extensao, ds_content_type, nu_tamanho, dt_data, dir_arquivo, ds_descricao, ds_nome_real)
    VALUES(11, 'teste.jpeg', '.mp4', 'video/mp4', 1594476, '2017-08-16', 'resources/arquivos/', '2121', '1491307989990.mp4');
INSERT INTO destinacao.tb_arquivo (id_arquivo, ds_nome, ds_extensao, ds_content_type, nu_tamanho, dt_data, dir_arquivo)
    VALUES(12, 'teste.jpeg', '.jpeg', NULL, 27109, '2016-12-01', 'resources/arquivos/');
/*FIM INSERTS TB_ARQUIVO*/

/*INICIO INSERTS TB_CONTRATO*/
INSERT INTO destinacao.tb_contrato (id_contrato, nu_contrato, dt_inicio, co_arquivo, dt_final)
    VALUES(1, '21212', '2017-01-01', 10, '2017-12-01');
/*FIM INSERTS TB_CONTRATO*/

/*INICIO INSERTS TB_ATO_AUTORIZATIVO*/
INSERT INTO destinacao.tb_ato_autorizativo (id_ato_autorizativo, dt_publicacao, co_tp_ato_autorizativo, nu_ato)
    VALUES(1, '2017-01-01', 4, 21);
/*FIM INSERTS TB_ATO_AUTORIZATIVO*/

/*INICIO INSERTS TB_DESTINACAO*/
INSERT INTO destinacao.tb_destinacao (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_contrato)
    VALUES(1, 'DF0001/2017', '000000', NULL, 11, 'VENDA', 2, NULL, NULL, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
    VALUES(3, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
    VALUES(4, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
    VALUES(60, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
    VALUES(61, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao,  co_contrato)
VALUES(6, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(7, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(8, NULL, NULL, NULL, NULL, 'VENDA', 2, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(9, NULL, NULL, NULL, NULL, 'VENDA', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(20, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(21, NULL, NULL, NULL, NULL, 'VENDA', 2, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(22, NULL, NULL, NULL, NULL, 'VENDA', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(23, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(24, NULL, NULL, NULL, NULL, 'VENDA', 2, NULL, NULL, 1, 1);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(25, NULL, NULL, NULL, NULL, 'VENDA', 2, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(11, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1,  NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato)
VALUES(26, NULL, NULL, NULL, NULL, 'SEM_UTILIZACAO', 12, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato, )
VALUES(62, NULL, NULL, NULL, NULL, 'USO_PROPRIO', 13, NULL, NULL, 3, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato, )
VALUES(27, NULL, NULL, NULL, NULL, 'TERMO_ENTREGA', 10, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato, )
VALUES(28, NULL, NULL, NULL, NULL, 'TERMO_ENTREGA', 10, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato, )
VALUES(29, NULL, NULL, NULL, NULL, 'DOACAO', 1, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato, )
VALUES(30, NULL, NULL, NULL, NULL, 'CESSAO_GRATUITA', 6, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato, )
VALUES(31, NULL, NULL, NULL, NULL, 'CUEM', 4, NULL, NULL, 1, NULL);
INSERT INTO destinacao.tb_destinacao
    (id_destinacao, nu_atendimento, nu_processo, co_utilizacao, co_fundamento_legal, ds_instrumento, co_tipo_destinacao, co_licitacao, co_financeiro, co_status_destinacao, co_contrato, )
VALUES(32, NULL, NULL, NULL, NULL, 'CDRU', 5, NULL, NULL, 1, NULL);
/*FIM INSERTS TB_DESTINACAO*/

/*INICIO INSERTS TB_USO_PROPRIO*/
INSERT INTO destinacao.tb_uso_proprio (id_uso_proprio, ic_homologado, co_responsavel_cadastro, ds_observacao)
    VALUES(62, false, 1, NULL);
/*FIM INSERTS TB_USO_PROPRIO*/

/*INICIO INSERTS TB_DOACAO*/
INSERT INTO destinacao.tb_doacao (id_doacao)
    VALUES(29);
/*FIM INSERTS TB_DOACAO*/

/*INICIO INSERTS TB_CESSAO_GRATUITA*/
INSERT INTO destinacao.tb_cessao_gratuita (id_cessao_gratuita)
    VALUES(30);
/*FIM INSERTS TB_CESSAO_GRATUITA*/

/*INICIO INSERTS TB_SEM_UTILIZACAO*/
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(3);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(4);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(60);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(6);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(7);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(20);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(23);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(25);
INSERT INTO destinacao.tb_sem_utilizacao (id_sem_utilizacao)
    VALUES(26);
/*FIM INSERTS TB_SEM_UTILIZACAO*/

/*INICIO INSERTS TB_VENDA*/
INSERT INTO destinacao.tb_venda (id_venda)
    VALUES(8);
INSERT INTO destinacao.tb_venda (id_venda)
    VALUES(22);
INSERT INTO destinacao.tb_venda (id_venda)
    VALUES(24);
INSERT INTO destinacao.tb_venda (id_venda)
    VALUES(26);
/*FIM INSERTS TB_VENDA*/

/*INICIO INSERTS TB_TERMO_ENTREGA*/
INSERT INTO destinacao.tb_termo_entrega (id_termo_entrega)
    VALUES(27);
INSERT INTO destinacao.tb_termo_entrega (id_termo_entrega)
    VALUES(28);
/*FIM INSERTS TB_TERMO_ENTREGA*/

/*INICIO INSERTS TB_CUEM*/
INSERT INTO destinacao.tb_cuem (id_cuem, co_tipo_modalidade)
    VALUES(31, 1);
/*FIM INSERTS TB_CUEM*/

/*INICIO INSERTS TB_CDRU*/
INSERT INTO destinacao.tb_cdru (id_cdru, co_tipo_concessao)
    VALUES(32, 1);
/*FIM INSERTS TB_CDRU*/

/*INICIO INSERTS TB_PENDENCIA*/
INSERT INTO destinacao.tb_pendencia (id_pendencia, ds_pendencia, ds_modulo)
    VALUES(1, 'Comprovar registro cartorial da transferência de direito real', 'DESTINACAO');
INSERT INTO destinacao.tb_pendencia (id_pendencia, ds_pendencia, ds_modulo)
    VALUES(2, 'Ajuste da destinação por alteração do imóvel', 'DESTINACAO');
INSERT INTO destinacao.tb_pendencia (id_pendencia, ds_pendencia, ds_modulo)
    VALUES(3, 'Ajuste da parcela por alteração do imóvel', 'DESTINACAO');
INSERT INTO destinacao.tb_pendencia (id_pendencia, ds_pendencia, ds_modulo)
    VALUES(4, 'Comprovar registro de uso próprio', 'DESTINACAO');
/*FIM INSERTS TB_PENDENCIA*/

/*INICIO INSERTS TB_ENDERECO*/
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
    VALUES(60, '38703750', 'Rua', 'abc', '1', NULL, 'Brasilia', 'Asa Sul', 'DF', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
    VALUES(61, '38703750', 'Rua', 'abc', '1', NULL, 'Brasilia', 'Asa Sul', 'DF', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
    VALUES(62, '38703750', 'Rua', 'abc', '1', NULL, 'Brasilia', 'Asa Sul', 'DF', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
    VALUES(63, '38703750', 'Rua', 'abc', '1', NULL, 'Brasilia', 'Asa Sul', 'DF', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(6, '38703750', 'Rua', 'Laura Fonsêca', '1', NULL, 'Patos de Minas', 'Residencial Monjolo', 'MG', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(7, '38703750', 'Rua', 'Laura Fonsêca', '1', NULL, 'Patos de Minas', 'Residencial Monjolo', 'MG', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(8, '38703750', 'Rua', 'Laura Fonsêca', '1', NULL, 'Patos de Minas', 'Residencial Monjolo', 'SE', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(9, '38703750', 'Rua', 'Laura Fonsêca', '1', NULL, 'Patos de Minas', 'Residencial Monjolo', 'SE', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(10, '38703750', 'Rua', 'Laura Fonsêca', '1', NULL, 'Patos de Minas', 'Residencial Monjolo', 'RJ', NULL, NULL, NULL);
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(11, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RJ', 'Alemanha', 'Berlim', '12020');
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(12, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RN', 'Brasil', 'Natal', '12123');
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(27, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RN', 'Brasil', 'Natal', '12123');
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(28, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RN', 'Brasil', 'Natal', '12123');
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(29, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RN', 'Brasil', 'Natal', '12123');
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(30, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RN', 'Brasil', 'Natal', '12123');
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(31, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RN', 'Brasil', 'Natal', '12123');
INSERT INTO destinacao.tb_endereco
    (id_endereco, ds_cep, ds_tipo_logradouro, ds_logradouro, ds_numero, ds_complemento, ds_municipio, ds_bairro, ds_uf, ds_pais, ds_cidade_exterior, ds_codigo_postal)
VALUES(32, NULL, NULL, NULL, '1', NULL, NULL, NULL, 'RN', 'Brasil', 'Natal', '12123');
/*FIM INSERTS TB_ENDERECO*/

/*INICIO INSERT TB_UNIDADE_AUTONOMA*/
INSERT INTO destinacao.tb_unidade_autonoma (id_unidade_autonoma, id_unidade_autonoma_cad_imovel, nu_area, nu_area_disponivel)
    VALUES(1, 33, 10000, 10000.11);
INSERT INTO destinacao.tb_unidade_autonoma (id_unidade_autonoma, id_unidade_autonoma_cad_imovel, nu_area, nu_area_disponivel)
    VALUES(2, 33, 10000, 10000.11);
/*FIM INSERTTB_UNIDADE_AUTONOMA*/

/*INICIO INSERTS TB_IMOVEL*/
INSERT INTO destinacao.tb_imovel (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
    VALUES(3, '00000007', 19, 1, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 61, 2112.12, true);
INSERT INTO destinacao.tb_imovel (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
    VALUES(4, '00000008', 20, 1, 'UNIDADE', 1, 1, 1, 1, 'UNIAO', 63, 2112.12, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
    VALUES(60, '00000123', 21, NULL, 'BENFEITORIA', 1, 1, 2, 1, '00000000191;00000000192', 60, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
    VALUES(61, '00000010', 22, 2, 'BENFEITORIA', 1, 1, 1, 1, '00000000191;00000000192', 60, 242.42, true);
INSERT INTO destinacao.tb_imovel
   (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(6, '00000005', 10, NULL, 'BENFEITORIA', 1, 2, 1, 1, '00000000191;00000000192', 6, 50.0, true);
INSERT INTO destinacao.tb_imovel
   (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(7, '00000009', 7, NULL, 'BENFEITORIA', 1, 2, 1, 1, '00000000191;00000000192', 7, 50.0, true);
INSERT INTO destinacao.tb_imovel
   (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(8, '00000004', 5, NULL, 'BENFEITORIA', 1, 2, 1, 1, '00000000191;00000000192', 6, 50.0, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(9, '00000003', 23, 2, 'BENFEITORIA', 1, 1, 1, 1, '00000000191;00000000192', 60, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(10, '00000002', 24, 2, 'BENFEITORIA', 1, 1, 1, 1, '00000000191;00000000192', 8, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(20, '00000020', 20, null, 'BENFEITORIA', 1, 1, 1, 1, '00000000191;00000000192', 9, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(23, '00000023', 23, null, 'BENFEITORIA', 1, 1, 1, 1, '00000000191;00000000192', 10, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(11, '00000011', 11, null, 'BENFEITORIA', 1, 1, 1, 1, '64-SENADO', 10, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(12, '00000012', 12, null, 'BENFEITORIA', 1, 1, 1, 1, '64-SENADO', 11, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(27, '00000027', 27, null, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 27, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(28, '00000028', 28, null, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 28, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(29, '00000029', 29, null, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 29, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(30, '00000030', 30, null, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 30, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(70, '00000070', 70, null, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 11, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(31, '00000031', 31, null, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 11, 242.42, true);
INSERT INTO destinacao.tb_imovel
    (id_imovel, nu_rip, id_cadastro_imovel, co_unidade_autonoma, ds_indicador_unidade_benfeitoria, co_tipo_imovel, co_situacao_incorporacao, co_natureza_imovel, co_tipo_proprietario, ds_proprietario, co_endereco, nu_area_terreno, ic_ativo)
VALUES(32, '00000032', 32, null, 'BENFEITORIA', 1, 1, 1, 1, 'UNIAO', 11, 242.42, true);
/*FIM INSERTS TB_IMOVEL*/


/*INICIO INSERTS TB_DESTINACAO_IMOVEL*/
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
    VALUES(3, 3, 3, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
    VALUES(4, 4, 4, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
    VALUES(56, 60, 60, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
    VALUES(57, 61, 61, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(6, 6, 6, '0000', NULL, NULL, NULL, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(7, 7, 7, '0000', NULL, NULL, NULL, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(8, 8, 7, '0001', NULL, NULL, NULL, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(61, 61, 61, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(9, 9, 9, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(20, 20, 20, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(21, 21, 20, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(23, 23, 23, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(24, 24, 23, '0002', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(25, 25, 23, '0001', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(11, 11, 11, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(26, 26, 12, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(62, 62, 3, '0000', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(27, 27, 27, '0001', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(28, 28, 28, '0001', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(29, 29, 29, '0001', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(30, 30, 30, '0001', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(31, 31, 31, '0001', NULL, NULL, 1000, true);
INSERT INTO destinacao.tb_destinacao_imovel
    (id_destinacao_imovel, co_destinacao, co_imovel, nu_codigo_utilizacao, nu_area_terreno_destinada, ds_memorial_desc_area_construida, nu_fracao_ideal, ic_ultima_destinacao)
VALUES(32, 32, 32, '0001', NULL, NULL, 1000, true);
/*FIM INSERTS TB_DESTINACAO_IMOVEL*/

/*INICIO INSERTS TB_FOTO_VIDEO_IMOVEL_DESTINADO*/
INSERT INTO destinacao.tb_foto_video_imovel_destinado (id_destinacao_imovel, id_arquivo)
    VALUES(27, 12);
INSERT INTO destinacao.tb_foto_video_imovel_destinado (id_destinacao_imovel, id_arquivo)
    VALUES(27, 11);
/*INICIO INSERTS TB_FOTO_VIDEO_IMOVEL_DESTINADO*/


/*INICIO INSERTS TB_PARCELA*/
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(1, 'P0', 242.42, 242.42, true, 3, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(3, 'P0', 242.42, 242.42, true, 4, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(75, 'P0', 242.42, 242.42, true, 60, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(76, 'P0', 242.42, 242.42, true, 61, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(77, 'P5', 242.42, 242.42, false, 61, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(36, 'P0', 50.0, 49.5499954999549995, false, 6, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(37, 'P1', 50.0, 20.0, true, 6, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(38, 'P2', 50.0, 29.5499954999549995, true, 6, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(39, 'P0', 50.0, 29.5499954999549995, true, 7, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(40, 'P0', 50.0, 49.5499954999549995, false, 8, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(41, 'P0', 242.42, 242.42, true, 9, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(42, 'P0', 242.42, 242.42, true, 10, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(43, 'P0', 242.42, 242.42, true, 20, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
    VALUES(44, 'P1', 242.42, 242.42, true, 20, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(45, 'P1', 242.42, 242.42, true, 23, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(11, 'P0', 242.42, 242.42, true, 11, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(46, 'P0', 242.42, 242.42, true, 12, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(27, 'P0', 242.42, 242.42, true, 27, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(28, 'P0', 242.42, 242.42, true, 28, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(29, 'P0', 242.42, 242.42, true, 29, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(30, 'P0', 242.42, 242.42, true, 30, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(50, 'P0', 242.42, 242.42, true, 70, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(31, 'P0', 242.42, 242.42, true, 31, NULL);
INSERT INTO destinacao.tb_parcela
    (id_parcela, ds_sequencial, nu_area_terreno, nu_area_diponivel, ic_ativa, co_imovel, ds_memorial_descritivo)
VALUES(32, 'P0', 242.42, 242.42, true, 32, NULL);
/*FIM INSERTS TB_PARCELA*/

/*INICIO INSERTS TB_DESTINACAO_IMOVEL_PARCELA*/
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(1, 3);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(75, 56);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(76, 57);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(3, 4);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(36, 6);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(37, 6);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(38, 7);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(40, 8);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(41, 9);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(39, 8);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(43, 20);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(44, 20);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(44, 21);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(45, 23);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(45, 24);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(45, 25);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(11, 11);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(46, 26);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(27, 27);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(28, 28);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(29, 29);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(30, 30);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(31, 31);
INSERT INTO destinacao.tb_destinacao_imovel_parcela (id_parcela, id_destinacao_imovel)
    VALUES(32, 32);
/*FIM INSERTS TB_DESTINACAO_IMOVEL_PARCELA*/


/*INICIO INSERTS TB_BENFEITORIA*/
INSERT INTO destinacao.tb_benfeitoria (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(5, 2, 'E1', true, 3242.34, 3242.34, 3, 'teste', 'abc', 60);
INSERT INTO destinacao.tb_benfeitoria (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(6, 1, 'C1', true, 234.23, 234.23, 3, 'teste', 'abc', 60);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(72, 2, 'E1', true, 3242.34, 3242.34, 76, 'teste', 'abc', 61);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(73, 1, 'C1', true, 234.23, 234.23, 76, 'teste', 'abc', 61);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(7, 2, 'E1', true, 111.11, 103.01, 36, 'fasd fasdfasdfasdf', 'Casa/Residência', 6);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(8, 4, 'C1', true, 111.11, 103.01, 36, 'fasd fasdfasdfasdf', 'Casa/Residência', 6);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(9, 2, 'E1', true, 111.11, 103.01, 37, 'fasd fasdfasdfasdf', 'Casa/Residência', 6);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(10, 3, 'E1', true, 111.11, 103.01, 37, 'fasd fasdfasdfasdf', 'Casa/Residência', 6);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(11, 4, 'C1', true, 111.11, 103.01, NULL, 'fasd fasdfasdfasdf', 'Casa/Residência', 6);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(12, 5, 'E1', true, 0, 0, 41, 'fasd fasdfasdfasdf', 'Casa/Residência', 9);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(13, 6, 'E1', true, 0, 0, 39, 'fasd fasdfasdfasdf', 'Casa/Residência', 7);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(80, 20, 'E1', true, 234.23, 234.23, 44, 'teste', 'abc', 20);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(81, 21, 'E2', true, 111.11, 103.01, 43, 'fasd fasdfasdfasdf', 'Casa/Residência', 20);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
    VALUES(82, 22, 'E3', true, 111.11, 103.01, 44, 'fasd fasdfasdfasdf', 'Casa/Residência', 20);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(83, 83, 'E3', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 23);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(84, 84, 'E3', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 12);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(27, 27, 'E1', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 27);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(28, 28, 'E1', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 28);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(29, 29, 'E1', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 29);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(30, 30, 'E1', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 30);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(85, 85, 'E3', true, 111.11, 0, 50, 'fasd fasdfasdfasdf', 'Casa/Residência', 70);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(31, 31, 'E1', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 31);
INSERT INTO destinacao.tb_benfeitoria
    (id_benfeitoria, id_benfeitoria_cad_imovel, ds_codigo_identificador, ic_ativa, nu_area_construida, nu_area_disponivel, co_parcela, ds_nome_benfeitoria, ds_especializacao, co_imovel)
VALUES(32, 32, 'E1', true, 111.11, 103.01, 45, 'fasd fasdfasdfasdf', 'Casa/Residência', 32);
/*FIM INSERTS TB_BENFEITORIA*/

/*INICIO INSERTS TB_TIPO_TITULARIDADE*/
INSERT INTO destinacao.tb_tipo_transferencia (id_tipo_transferencia, ds_tipo_transferencia)
    VALUES(1, 'Gestão');
INSERT INTO destinacao.tb_tipo_transferencia (id_tipo_transferencia, ds_tipo_transferencia)
    VALUES(2, 'Titularidade');
/* FIM INSERTS TB_TIPO_TITULARIDADE*/

/*INICIO INSERTS TB_BASE_LEGAL*/
INSERT INTO destinacao.tb_base_legal (id_base_legal, ds_base_legal)
    VALUES(1, 'Base Legal 1');
INSERT INTO destinacao.tb_base_legal (id_base_legal, ds_base_legal)
    VALUES(2, 'Base Legal 2');
/*FIM INSERTS TB_BASE_LEGAL*/

/*INICIO INSERTS TB_TIPO_DESTINATARIO*/
INSERT INTO destinacao.tb_tipo_destinatario (id_tipo_destinatario, ds_tipo_destinatario)
    VALUES(1, 'União');
INSERT INTO destinacao.tb_tipo_destinatario (id_tipo_destinatario, ds_tipo_destinatario)
    VALUES(2, 'Autarquia/Fundação');
INSERT INTO destinacao.tb_tipo_destinatario (id_tipo_destinatario, ds_tipo_destinatario)
    VALUES(3, 'Estatal');
/*FIM INSERTS TB_TIPO_DESTINATARIO*/

/*INICIO INSERTS TB_TIPO_DOCUMENTO*/
INSERT INTO destinacao.tb_tipo_documento (id_tipo_documento, ds_tipo_documento)
  VALUES(1, 'Extrato');
/*FIM INSERTS TB_TIPO_DOCUMENTO*/

/*INICIO INSERTS TB_SUB_TIPO_DOCUMENTO*/
INSERT INTO destinacao.tb_sub_tipo_documento (id_sub_tipo_documento, ds_sub_tipo_documento, co_tipo_documento)
  VALUES(1, 'Dispensa', 1);
/*FIM INSERTS TB_SUB_TIPO_DEOCUMENTO*/

/*INICIO INSERTS TB_TIPO_UTILIZACAO*/
INSERT INTO destinacao.tb_tipo_utilizacao (id_tipo_utilizacao, num_tipo_utilizacao, ds_tipo_utilizacao, ic_ativo)
    VALUES(1, 0, 'Sem uso/Vago', false);
INSERT INTO destinacao.tb_tipo_utilizacao (id_tipo_utilizacao, num_tipo_utilizacao, ds_tipo_utilizacao, ic_ativo)
    VALUES(2, 1, 'Sem informação', false);
INSERT INTO destinacao.tb_tipo_utilizacao (id_tipo_utilizacao, num_tipo_utilizacao, ds_tipo_utilizacao, ic_ativo)
    VALUES(3, 2, 'Sem uso definido', true);
INSERT INTO destinacao.tb_tipo_utilizacao (id_tipo_utilizacao, num_tipo_utilizacao, ds_tipo_utilizacao, ic_ativo)
    VALUES(4, 3, 'Sede/Unidade de Entidade/Órgão (público ou privado)', true);
INSERT INTO destinacao.tb_tipo_utilizacao (id_tipo_utilizacao, num_tipo_utilizacao, ds_tipo_utilizacao, ic_ativo)
    VALUES(5, 4, 'Unidade/instalação de Segurança e Defesa', true);
/*FIM INSERTS TB_TIPO_UTILIZACAO*/

/*INICIO INSERTS TB_SUB_TIPO_UTILIZACAO*/
INSERT INTO destinacao.tb_sub_tipo_utilizacao (id_sub_tipo_utilizacao, ds_sub_tipo_utilizacao, co_tipo_utilizacao,ic_ativo)
  VALUES(1,'teste', 3,true);
/*FIM INSERTS TB_SUB_TIPO_UTILIZACAO*/

/*INICIO INSERTS TB_UTILIZACAO*/
INSERT INTO destinacao.tb_utilizacao (id_utilizacao, ds_finalidade, nu_familias_beneficiadas, nu_servidores, nu_area_servidor,
co_tipo_utilizacao, co_sub_tipo_utilizacao, ds_especificacao_utilizacao)
  VALUES(1,'teste', 1,1,20.0, 3,1,'teste');
INSERT INTO destinacao.tb_utilizacao (id_utilizacao, co_tipo_utilizacao)
  VALUES(2, 1);
/*FIM INSERTS TB_UTILIZACAO*/

/*INICIO INSERTS TB_BENFEITORIA_DESTINADA*/
INSERT INTO destinacao.tb_benfeitoria_destinada (id_benfeitoria_destinada, nu_area_utilizar, co_benfeitoria, co_destinacao_imovel)
  VALUES(1,100.0, 5,7);
INSERT INTO destinacao.tb_benfeitoria_destinada (id_benfeitoria_destinada, nu_area_utilizar, co_benfeitoria, co_destinacao_imovel)
  VALUES(2,100.0, 5,8);
INSERT INTO destinacao.tb_benfeitoria_destinada (id_benfeitoria_destinada, nu_area_utilizar, co_benfeitoria, co_destinacao_imovel)
  VALUES(3,100.0, 5,7);
INSERT INTO destinacao.tb_benfeitoria_destinada (id_benfeitoria_destinada, nu_area_utilizar, co_benfeitoria, co_destinacao_imovel)
  VALUES(4, 100.0, 5, 62);

/*FIM INSERTS TB_BENFEITORIA_DESTINADA*/

/*INICIO INSERTS TB_DESTINACAO_PENDENCIA*/
INSERT INTO destinacao.tb_destinacao_pendencia(id_destinacao, id_pendencia, dt_data_geracao)
VALUES(3, 1, '2017-05-14');

INSERT INTO destinacao.tb_destinacao_pendencia(id_destinacao, id_pendencia, dt_data_geracao)
VALUES(62, 4, '2017-07-17');

INSERT INTO destinacao.tb_destinacao_pendencia(id_destinacao, id_pendencia, dt_data_geracao)
VALUES(62, 3, '2017-07-17');
/*FIM INSERTS TB_DESTINACAO_PENDENCIA*/

/*INICIO INSERTS TB_PERMISSAO*/
INSERT INTO destinacao.tb_permissao(id_permissao,ds_permissao)
VALUES(1,'DESTINACAOMANTERDOACAO');
/*FIM INSERTS TB_PERMISSAO*/

/*INICIO INSERTS TB_PERMISSAO_PENDENCIA*/
INSERT INTO destinacao.tb_permissao_pendencia(id_pendencia, id_permissao)
VALUES(1,1);
/*FIM INSERTS TB_PERMISSAO_PENDENCIA*/

/*INICIO INSERTS TB_DOCUMENTO*/
INSERT INTO destinacao.tb_documento
(id_documento, dt_final_vigencia, dt_inicial_vigencia, dt_publicacao, ic_dispensado, ds_especificar, ds_folhas, ds_justificativa, ds_livro, ds_numero_termo, ds_pagina, ds_secao, co_arquivo, co_destinacao, co_sub_tipo_documento, co_tipo_documento)
VALUES(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1);
/*FIM INSERTS TB_DOCUMENTO*/

/*INICIO INSERTS TB_DOCUMENTO_ANALISE*/
INSERT INTO destinacao.tb_documento_analise
(id_documento_analise, id_documento, ds_observacao, ic_resposta, ds_tipo_documento, co_analise_tecnica)
VALUES(1, 1, NULL, NULL, 'OBRIGATORIO', 125);
/*FIM INSERTS TB_DOCUMENTO_ANALISE*/

/*INICIO INSERTS TB_ITEM_VERIFICACAO*/
INSERT INTO destinacao.tb_item_verificacao
(id_item_verificacao, id_item, ds_observacao, ic_resposta, co_analise_tecnica)
VALUES(1, 2, 'teste', true, 125);
/*FIM INSERTS TB_ITEM_VERIFICACAO*/

