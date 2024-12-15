create table pacientes(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento_identidad varchar(14) not null unique,
    telefono varchar(20) not null,
    urbanización varchar(100) null,
    distrito varchar(100) null,
    codigoPostal varchar(9) null,
    complemento varchar(100),
    numero varchar(20),
    provincia varchar(100) null,
    ciudad varchar(100) null,

    primary key(id)
);