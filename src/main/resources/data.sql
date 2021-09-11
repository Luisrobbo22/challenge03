CREATE TABLE Usuario(
                        id_usuario bigint primary key auto_increment,
                        nm_usuario varchar(100) not null,
                        ds_email varchar(100) not null,
                        ds_senha varchar(100) not null,
                        dt_nascimento date not null,
                        nr_rg varchar(15),
                        nr_cpf varchar(15) not null,
                        id_genero bigint
);

CREATE TABLE Estado(
                       id_estado bigint primary key auto_increment,
                       nr_atualizacao int,
                       id_usuario bigint,
                       dt_atualizacao timestamp,
                       md_pressao_arterial varchar(5) not null,
                       md_monitoramento_cardiaco varchar(50) not null,
                       md_temperatura varchar(5),
                       foreign key (id_usuario) references Usuario(id_usuario)
);

CREATE TABLE INFORMACAO_USUARIO(
                                  id_usuario_info bigint primary key auto_increment,
                                  nr_atualizacao int,
                                  dt_atualizacao timestamp,
                                  nr_altura double(5) not null,
                                  nr_peso double(5) not null,
                                  nr_imc double(10),
                                  id_usuario bigint,
                                  foreign key (id_usuario) references Usuario(id_usuario)
);

CREATE TABLE Genero(
                       id_genero bigint primary key auto_increment,
                       ds_genero varchar(20) not null ,
                       id_usuario bigint,
                       foreign key (id_usuario) references Usuario(id_usuario)
);


INSERT INTO Genero(DS_GENERO,
                   ID_USUARIO)
VALUES ('Masculino',
        null);

INSERT INTO Usuario(nm_usuario,
                    ds_email,
                    ds_senha,
                    dt_nascimento,
                    nr_rg,
                    nr_cpf,
                    id_genero)
    VALUES ('Luis',
            'luis@email.com',
            '12345678',
            '2021-12-22',
            NULL,
            '470.716.708-03',
            1);


INSERT INTO Estado(nr_atualizacao,
                   id_usuario,
                   dt_atualizacao,
                   md_pressao_arterial,
                   md_monitoramento_cardiaco,
                   md_temperatura)
        VALUES (1,
                1,
                '2021-09-09',
                '13/7',
                '85',
                '37.2');

INSERT INTO INFORMACAO_USUARIO(nr_atualizacao,
                              dt_atualizacao,
                              nr_altura,
                              nr_peso,
                              nr_imc,
                              id_usuario)
        VALUES (1,
                '2021-09-09',
                1.83,
                90.0,
                0,
                1);

UPDATE Genero
SET id_usuario = (select u.id_usuario from Usuario u where u.id_genero = 1)
WHERE id_genero = 1;