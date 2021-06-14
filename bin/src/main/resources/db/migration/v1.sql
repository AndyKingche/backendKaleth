create table roles
(
   id_roles             int not null auto_increment,
   rol                  varchar(20),
   descripcion          varchar(50),
   nombre               varchar(20),
   primary key (id_roles)
);

INSERT INTO roles (rol,descripcion,nombre) VALUES ('admin','Administrador de Importadora Kaleth','Administrador')