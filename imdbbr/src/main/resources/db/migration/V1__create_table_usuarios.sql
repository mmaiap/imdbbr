CREATE TABLE usuarios(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    email varchar(50) not null,
    senha varchar(50) not null,
    ativo int(1) not null,
    primary key(id)
);