-- MySQL dump 10.13  Distrib 5.6.35, for macos10.12 (x86_64)
--
-- Host: localhost    Database: KeepHealthyDB
-- ------------------------------------------------------
-- Server version	5.6.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Actividad`
--

DROP TABLE IF EXISTS `Actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Actividad` (
  `idActividad` int(1) NOT NULL AUTO_INCREMENT,
  `descripcionActividad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idActividad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Actividad`
--

LOCK TABLES `Actividad` WRITE;
/*!40000 ALTER TABLE `Actividad` DISABLE KEYS */;
INSERT INTO `Actividad` VALUES (1,'Poca'),(2,'Media'),(3,'Mucha');
/*!40000 ALTER TABLE `Actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Alimento`
--

DROP TABLE IF EXISTS `Alimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Alimento` (
  `idAlimento` int(6) NOT NULL AUTO_INCREMENT,
  `idCategoria` int(1) DEFAULT NULL,
  `nombreAlimento` varchar(30) DEFAULT NULL,
  `Porcion` varchar(30) DEFAULT NULL,
  `Calorias` float DEFAULT NULL,
  PRIMARY KEY (`idAlimento`),
  KEY `idCategoria` (`idCategoria`),
  CONSTRAINT `alimento_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `CatAlimento` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alimento`
--

LOCK TABLES `Alimento` WRITE;
/*!40000 ALTER TABLE `Alimento` DISABLE KEYS */;
INSERT INTO `Alimento` VALUES (1,6,'Tortilla','2 pza',3),(2,6,'Arroz','250gr',3),(3,6,'Avena','250gr',3),(4,8,'Frijoles','200gr',4),(5,8,'Lentejas','200gr',4),(6,8,'Habas','200gr',4),(7,5,'Nopales','3 pza',5),(8,5,'Espinocas','3 pza',5),(9,5,'Calabaza','3 pza',5),(10,1,'Manzana','2 pza',6),(11,1,'Platano','2 pza',6),(12,1,'Uvas','12 pza',6),(13,4,'Pollo','1 filete',7),(14,4,'Pescado','1 filete',7),(15,4,'Carne','1 filete',7),(16,3,'Queso','150gr',8),(17,3,'Leche','1 vaso',8),(18,3,'Yogur','1 vaso',8),(19,2,'Agua','medio litro',9),(20,7,'Huevo','2 claras',10);
/*!40000 ALTER TABLE `Alimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AlimentoRestriccion`
--

DROP TABLE IF EXISTS `AlimentoRestriccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AlimentoRestriccion` (
  `idAlimentoRestriccion` int(6) NOT NULL AUTO_INCREMENT,
  `idAlimento` int(6) DEFAULT NULL,
  `idUsuario` int(6) DEFAULT NULL,
  `Restriccion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idAlimentoRestriccion`),
  KEY `idAlimento` (`idAlimento`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `alimentorestriccion_ibfk_1` FOREIGN KEY (`idAlimento`) REFERENCES `Alimento` (`idAlimento`),
  CONSTRAINT `alimentorestriccion_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AlimentoRestriccion`
--

LOCK TABLES `AlimentoRestriccion` WRITE;
/*!40000 ALTER TABLE `AlimentoRestriccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `AlimentoRestriccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CatAlimento`
--

DROP TABLE IF EXISTS `CatAlimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CatAlimento` (
  `idCategoria` int(1) NOT NULL AUTO_INCREMENT,
  `descripcionCategoria` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CatAlimento`
--

LOCK TABLES `CatAlimento` WRITE;
/*!40000 ALTER TABLE `CatAlimento` DISABLE KEYS */;
INSERT INTO `CatAlimento` VALUES (1,'Fruta'),(2,'Agua'),(3,'Lacteo'),(4,'Carne'),(5,'Verdura'),(6,'Cereal'),(7,'Huevo'),(8,'Leguminosas');
/*!40000 ALTER TABLE `CatAlimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EtapaMenu`
--

DROP TABLE IF EXISTS `EtapaMenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EtapaMenu` (
  `idEtapaMenu` int(1) NOT NULL AUTO_INCREMENT,
  `descripcionEtapa` varchar(15) DEFAULT NULL,
  `horaEtapa` varchar(6) DEFAULT NULL,
  `porcentajeCalorias` float DEFAULT NULL,
  PRIMARY KEY (`idEtapaMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EtapaMenu`
--

LOCK TABLES `EtapaMenu` WRITE;
/*!40000 ALTER TABLE `EtapaMenu` DISABLE KEYS */;
INSERT INTO `EtapaMenu` VALUES (1,'Desayuno','9:00',20),(2,'Almuerzo','12:00',20),(3,'Comida','14:30',20),(4,'Merienda','18:00',20),(5,'Cena','21:00',20);
/*!40000 ALTER TABLE `EtapaMenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GrupoEtapa`
--

DROP TABLE IF EXISTS `GrupoEtapa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GrupoEtapa` (
  `idGrupoEtapa` int(6) NOT NULL AUTO_INCREMENT,
  `idEtapaMenu` int(1) DEFAULT NULL,
  `idAlimento` int(6) DEFAULT NULL,
  PRIMARY KEY (`idGrupoEtapa`),
  KEY `idEtapaMenu` (`idEtapaMenu`),
  CONSTRAINT `grupoetapa_ibfk_1` FOREIGN KEY (`idEtapaMenu`) REFERENCES `EtapaMenu` (`idEtapaMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GrupoEtapa`
--

LOCK TABLES `GrupoEtapa` WRITE;
/*!40000 ALTER TABLE `GrupoEtapa` DISABLE KEYS */;
/*!40000 ALTER TABLE `GrupoEtapa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HistorialMenu`
--

DROP TABLE IF EXISTS `HistorialMenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HistorialMenu` (
  `idHistorialMenu` int(6) NOT NULL AUTO_INCREMENT,
  `idMenuUsuario` int(6) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `Calificacion` float DEFAULT NULL,
  PRIMARY KEY (`idHistorialMenu`),
  KEY `idMenuUsuario` (`idMenuUsuario`),
  CONSTRAINT `historialmenu_ibfk_1` FOREIGN KEY (`idMenuUsuario`) REFERENCES `MenuUsuario` (`idMenuUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HistorialMenu`
--

LOCK TABLES `HistorialMenu` WRITE;
/*!40000 ALTER TABLE `HistorialMenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `HistorialMenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HistorialPeso`
--

DROP TABLE IF EXISTS `HistorialPeso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HistorialPeso` (
  `idHistorialPeso` int(6) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(6) DEFAULT NULL,
  `idHistorialMenu` int(6) DEFAULT NULL,
  `fechaHistorial` datetime DEFAULT NULL,
  `pesoHistorial` float DEFAULT NULL,
  PRIMARY KEY (`idHistorialPeso`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idHistorialMenu` (`idHistorialMenu`),
  CONSTRAINT `historialpeso_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`),
  CONSTRAINT `historialpeso_ibfk_2` FOREIGN KEY (`idHistorialMenu`) REFERENCES `HistorialMenu` (`idHistorialMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HistorialPeso`
--

LOCK TABLES `HistorialPeso` WRITE;
/*!40000 ALTER TABLE `HistorialPeso` DISABLE KEYS */;
/*!40000 ALTER TABLE `HistorialPeso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Menu`
--

DROP TABLE IF EXISTS `Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Menu` (
  `idMenu` int(6) NOT NULL AUTO_INCREMENT,
  `idGrupoEtapa` int(6) DEFAULT NULL,
  `noMenu` int(6) DEFAULT NULL,
  `caloriasMenu` float DEFAULT NULL,
  PRIMARY KEY (`idMenu`),
  KEY `idGrupoEtapa` (`idGrupoEtapa`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`idGrupoEtapa`) REFERENCES `GrupoEtapa` (`idGrupoEtapa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Menu`
--

LOCK TABLES `Menu` WRITE;
/*!40000 ALTER TABLE `Menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MenuUsuario`
--

DROP TABLE IF EXISTS `MenuUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MenuUsuario` (
  `idMenuUsuario` int(6) NOT NULL AUTO_INCREMENT,
  `idMenu` int(6) DEFAULT NULL,
  `idUsuario` int(6) DEFAULT NULL,
  PRIMARY KEY (`idMenuUsuario`),
  KEY `idMenu` (`idMenu`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `menuusuario_ibfk_1` FOREIGN KEY (`idMenu`) REFERENCES `Menu` (`idMenu`),
  CONSTRAINT `menuusuario_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MenuUsuario`
--

LOCK TABLES `MenuUsuario` WRITE;
/*!40000 ALTER TABLE `MenuUsuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `MenuUsuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Perfil`
--

DROP TABLE IF EXISTS `Perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Perfil` (
  `idPerfil` int(1) NOT NULL AUTO_INCREMENT,
  `descripcionPerfil` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idPerfil`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Perfil`
--

LOCK TABLES `Perfil` WRITE;
/*!40000 ALTER TABLE `Perfil` DISABLE KEYS */;
INSERT INTO `Perfil` VALUES (1,'Administrador'),(2,'Usuario');
/*!40000 ALTER TABLE `Perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sexo`
--

DROP TABLE IF EXISTS `Sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sexo` (
  `idSexo` int(1) NOT NULL AUTO_INCREMENT,
  `descripcionSexo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idSexo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sexo`
--

LOCK TABLES `Sexo` WRITE;
/*!40000 ALTER TABLE `Sexo` DISABLE KEYS */;
INSERT INTO `Sexo` VALUES (1,'Masculino'),(2,'Femenino');
/*!40000 ALTER TABLE `Sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(6) NOT NULL AUTO_INCREMENT,
  `idPerfil` int(1) DEFAULT NULL,
  `idSexo` int(1) DEFAULT NULL,
  `idAlimentoRestriccion` int(6) DEFAULT NULL,
  `idActividad` int(1) DEFAULT NULL,
  `nombreUsuario` varchar(65) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `Contrasena` varchar(25) DEFAULT NULL,
  `fechaNacimiento` datetime DEFAULT NULL,
  `pesoUsuario` float DEFAULT NULL,
  `Estatura` float DEFAULT NULL,
  `Ocupacion` varchar(20) DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `fechaUltVez` datetime DEFAULT NULL,
  `caloriasDiarias` float DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idPerfil` (`idPerfil`),
  KEY `idSexo` (`idSexo`),
  KEY `idAlimentoRestriccion` (`idAlimentoRestriccion`),
  KEY `idActividad` (`idActividad`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idPerfil`) REFERENCES `Perfil` (`idPerfil`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`idSexo`) REFERENCES `Sexo` (`idSexo`),
  CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`idAlimentoRestriccion`) REFERENCES `AlimentoRestriccion` (`idAlimentoRestriccion`),
  CONSTRAINT `usuario_ibfk_4` FOREIGN KEY (`idActividad`) REFERENCES `Actividad` (`idActividad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-06  0:03:12
