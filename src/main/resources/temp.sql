-- Table: servico.tb_servico
CREATE TABLE servico.tb_servico
(
  id_servico bigint UNIQUE NOT NULL PRIMARY KEY,
  ds_titulo character varying(100),
  ds_subtitulo character varying(200),
  ds_link character varying(200),
  ds_descricao character varying(500)
);


CREATE TABLE servico.tb_solicitantes (
    id_solicitante bigint UNIQUE NOT NULL PRIMARY KEY,
    id_servico bigint,
    ds_tipo character varying(100),
    ds_segmento character varying(200),
    FOREIGN KEY (id_servico) REFERENCES servico.tb_servico(id_servico)
);