#drop database KeepHealthyDB;
create database KeepHealthyDB;
use KeepHealthyDB;

create table Usuario(
					idUsuario int(6) not null auto_increment primary key, 
					idPerfil int(1), 
                    idSexo int(1),
                    idAlimentoRestriccion int(6),
                    idActividad int(1),
                    nombreUsuario varchar (65), 
					nickname varchar(20),
                    email varchar(40), 
                    Contrasena varchar(25), 
                    fechaNacimiento datetime, 
                    pesoUsuario float(6), 
                    Estatura float(6),
                    Ocupacion varchar(20), 
                    fechaRegistro datetime, 
                    fechaUltVez datetime, 
                    caloriasDiarias float(20));
                    
create table Perfil(
					idPerfil int(1) not null auto_increment primary key,
                    descripcionPerfil varchar(20));
                    
create table Sexo(
					idSexo int(1) not null auto_increment primary key,
                    descripcionSexo varchar(20));
                    
create table Alimento(
					idAlimento int(6) not null auto_increment primary key,
                    idCategoria int(1),
                    nombreAlimento varchar(30),
                    Porcion varchar(30),
                    Calorias float(10));

create table Actividad(
					idActividad int(1) not null auto_increment primary key,
                    descripcionActividad varchar(50));

create table AlimentoRestriccion(
					idAlimentoRestriccion int(6) not null auto_increment primary key,
                    idAlimento int(6),
                    idUsuario int(6),
                    Restriccion varchar(10));
                    
create table CatAlimento(
					idCategoria int(1) not null auto_increment primary key,
                    descripcionCategoria varchar(20));
                    
create table EtapaMenu(
					idEtapaMenu int(1) not null auto_increment primary key, 
                    descripcionEtapa varchar(15),
                    horaEtapa varchar(6),
                    porcentajeCalorias float(5));
                    
create table MenuUsuario(
					idMenuUsuario int(6) not null auto_increment primary key,
                    idMenu int(6),
                    idUsuario int(6));
                    
create table HistorialMenu(
					idHistorialMenu int(6) not null auto_increment primary key,
                    idMenuUsuario int(6),
                    fechaCreacion datetime,
                    Calificacion float(4));
                    
create table Menu(
					idMenu int(6) not null auto_increment primary key,
                    idGrupoEtapa int(6),
                    noMenu int(6),
                    caloriasMenu float(10));
                    
create table GrupoEtapa(
					idGrupoEtapa int(6) not null auto_increment primary key,
                    idEtapaMenu int(1),
                    idAlimento int(6));
                    
create table HistorialPeso(
					idHistorialPeso int(6) not null auto_increment primary key,
                    idUsuario int(6),
                    idHistorialMenu int(6),
                    fechaHistorial datetime,
                    pesoHistorial float(5));

alter table Usuario add foreign key (idPerfil) references Perfil(idPerfil);
alter table Usuario add foreign key (idSexo) references Sexo(idSexo);
alter table Usuario add foreign key (idAlimentoRestriccion) references AlimentoRestriccion(idAlimentoRestriccion);
alter table Usuario add foreign key (idActividad) references Actividad(idActividad);
alter table Alimento add foreign key (idCategoria) references CatAlimento(idCategoria);
alter table AlimentoRestriccion add foreign key (idAlimento) references Alimento(idAlimento);
alter table AlimentoRestriccion add foreign key (idUsuario) references Usuario(idUsuario);
alter table MenuUsuario add foreign key (idMenu) references Menu(idMenu);
alter table MenuUsuario add foreign key (idUsuario) references Usuario(idUsuario);
alter table HistorialMenu add foreign key (idMenuUsuario) references MenuUsuario(idMenuUsuario);
alter table Menu add foreign key (idGrupoEtapa) references GrupoEtapa(idGrupoEtapa);
alter table GrupoEtapa add foreign key (idEtapaMenu) references EtapaMenu(idEtapaMenu);
alter table HistorialPeso add foreign key (idUsuario) references Usuario(idUsuario);
alter table HistorialPeso add foreign key (idHistorialMenu) references HistorialMenu(idHistorialMenu);

insert into Perfil (descripcionPerfil) values ('Administrador');
insert into Perfil (descripcionPerfil) values ('Usuario');
insert into Sexo (descripcionSexo) values ('Masculino');
insert into Sexo (descripcionSexo) values ('Femenino');
insert into Actividad (descripcionActividad) values ('Poca');
insert into Actividad (descripcionActividad) values ('Media');
insert into Actividad (descripcionActividad) values ('Mucha');
insert into CatAlimento(descripcionCategoria) values ('Fruta');
insert into CatAlimento(descripcionCategoria) values ('Agua');
insert into CatAlimento(descripcionCategoria) values ('Lacteo');
insert into CatAlimento(descripcionCategoria) values ('Carne');
insert into CatAlimento(descripcionCategoria) values ('Verdura');
insert into CatAlimento(descripcionCategoria) values ('Cereal');
insert into CatAlimento(descripcionCategoria) values ('Huevo');
insert into CatAlimento(descripcionCategoria) values ('Leguminosas');
insert into EtapaMenu(descripcionEtapa,horaEtapa,porcentajeCalorias) values ('Desayuno','9:00',20.0);
insert into EtapaMenu(descripcionEtapa,horaEtapa,porcentajeCalorias) values ('Almuerzo','12:00',20.0);
insert into EtapaMenu(descripcionEtapa,horaEtapa,porcentajeCalorias) values ('Comida','14:30',20.0);
insert into EtapaMenu(descripcionEtapa,horaEtapa,porcentajeCalorias) values ('Merienda','18:00',20.0);
insert into EtapaMenu(descripcionEtapa,horaEtapa,porcentajeCalorias) values ('Cena','21:00',20.0);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Tortilla',6,'2 pza',3);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Arroz',6,'250gr',3);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Avena',6,'250gr',3);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Frijoles',8,'200gr',4);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Lentejas',8,'200gr',4);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Habas',8,'200gr',4);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Nopales',5,'3 pza',5);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Espinocas',5,'3 pza',5);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Calabaza',5,'3 pza',5);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Manzana',1,'2 pza',6);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Platano',1,'2 pza',6);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Uvas',1,'12 pza',6);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Pollo',4,'1 filete',7);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Pescado',4,'1 filete',7);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Carne',4,'1 filete',7);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Queso',3,'150gr',8);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Leche',3,'1 vaso',8);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Yogur',3,'1 vaso',8);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Agua',2,'medio litro',9);
insert into Alimento(nombreAlimento,idCategoria,Porcion,Calorias) values ('Huevo',7,'2 claras',10);
