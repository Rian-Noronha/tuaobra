CREATE TABLE IF NOT EXISTS public.casa_construcao
(
    id bigint NOT NULL,
    avaliacao double precision NOT NULL,
    contato_whats_app character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    frete character varying(255) COLLATE pg_catalog."default",
    horario character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    endereco_id bigint,
    url_imagem_perfil character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT casa_construcao_pkey PRIMARY KEY (id),
    CONSTRAINT ukfqwffiw5r20h929abu1t2pkyp UNIQUE (endereco_id),
    CONSTRAINT fk37g7h03b6oy0b1nox9t000ay1 FOREIGN KEY (endereco_id)
        REFERENCES public.endereco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.cliente
(
    id bigint NOT NULL,
    contato_whats_app character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    url_imagem_perfil character varying(255) COLLATE pg_catalog."default",
    endereco_id bigint,
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    CONSTRAINT uk7v21uy9djyl7hh9464kkjsjg0 UNIQUE (endereco_id),
    CONSTRAINT fk64nr9yt889by5lufr1boo5i4s FOREIGN KEY (endereco_id)
        REFERENCES public.endereco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.cliente_casa_construcao
(
    cliente_id bigint NOT NULL,
    casa_construcao_id bigint NOT NULL,
    CONSTRAINT fk5et2qy4re23xwc46fb68svymo FOREIGN KEY (cliente_id)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk7a6d1enlh3pug658x5eq0ex6r FOREIGN KEY (casa_construcao_id)
        REFERENCES public.casa_construcao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.demanda
(
    id bigint NOT NULL,
    data_publicacao timestamp(6) without time zone,
    detalhes character varying(255) COLLATE pg_catalog."default",
    trabalho_ser_feito character varying(255) COLLATE pg_catalog."default",
    cliente_id bigint,
    CONSTRAINT demanda_pkey PRIMARY KEY (id),
    CONSTRAINT fklshxbeku0cmwq6ttoa7co7qr7 FOREIGN KEY (cliente_id)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS public.endereco
(
    id bigint NOT NULL,
    cep character varying(255) COLLATE pg_catalog."default",
    nome_lugar character varying(255) COLLATE pg_catalog."default",
    numero character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT endereco_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.especialidade
(
    id bigint NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT especialidade_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.pedreiro
(
    id bigint NOT NULL,
    avaliacao double precision NOT NULL,
    contato_whats_app character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    url_imagem_perfil character varying(255) COLLATE pg_catalog."default",
    endereco_id bigint,
    CONSTRAINT pedreiro_pkey PRIMARY KEY (id),
    CONSTRAINT uk1oncbsi5glreqhpeesjscqaou UNIQUE (endereco_id),
    CONSTRAINT fkg2yh6ib3ojjayacr2jtixr03w FOREIGN KEY (endereco_id)
        REFERENCES public.endereco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS public.pedreiro_demanda
(
    pedreiro_id bigint NOT NULL,
    demanda_id bigint NOT NULL,
    CONSTRAINT fk27a02lfw6n5l1ed57xafntj2c FOREIGN KEY (pedreiro_id)
        REFERENCES public.pedreiro (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk6ayityfabaq21d9r7rvmqsnf7 FOREIGN KEY (demanda_id)
        REFERENCES public.demanda (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS public.pedreiro_especialidade
(
    pedreiro_id bigint NOT NULL,
    especialidade_id bigint NOT NULL,
    CONSTRAINT fkrn41t8mf0ngg020t7gutn6ll8 FOREIGN KEY (especialidade_id)
        REFERENCES public.especialidade (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fks8xv8og1vb2bbpuxl1qq318n5 FOREIGN KEY (pedreiro_id)
        REFERENCES public.pedreiro (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
