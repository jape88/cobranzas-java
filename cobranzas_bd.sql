-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.17-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para bd_cobranza
CREATE DATABASE IF NOT EXISTS `bd_cobranza` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bd_cobranza`;

-- Volcando estructura para tabla bd_cobranza.acreedores
CREATE TABLE IF NOT EXISTS `acreedores` (
  `id` bigint(20) DEFAULT NULL,
  `cedula` bigint(20) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `celular` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  UNIQUE KEY `Índice 1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla paga guardar los acreedores de los creditos';

-- Volcando datos para la tabla bd_cobranza.acreedores: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `acreedores` DISABLE KEYS */;
INSERT INTO `acreedores` (`id`, `cedula`, `nombre`, `apellido`, `celular`, `correo`) VALUES
	(210317144234353, 123456, 'Juan', 'Perez 23', '3113244566', 'treee@gff.com'),
	(210317150424109, 12345, 'Juan 23', 'Peres Lopes', '3113455667', 'rtrt@sdfds.com');
/*!40000 ALTER TABLE `acreedores` ENABLE KEYS */;

-- Volcando estructura para tabla bd_cobranza.ciudad
CREATE TABLE IF NOT EXISTS `ciudad` (
  `id` bigint(20) DEFAULT NULL,
  `id_departamento` bigint(20) DEFAULT NULL,
  `codigo_ciudad` int(11) DEFAULT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  UNIQUE KEY `Índice 1` (`id`),
  KEY `FK_ciudad_departamento` (`id_departamento`),
  CONSTRAINT `FK_ciudad_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bd_cobranza.ciudad: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` (`id`, `id_departamento`, `codigo_ciudad`, `nombre`) VALUES
	(676657776, 4566677, 1, 'Armenia');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;

-- Volcando estructura para tabla bd_cobranza.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` bigint(20) DEFAULT NULL,
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` bigint(20) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `celular` varchar(50) DEFAULT NULL,
  `celular2` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `id_ciudad` bigint(20) DEFAULT NULL,
  `ruta_imagen` varchar(150) DEFAULT NULL COMMENT 'Ruta fisica que debe ser creada en el pc donde se instala',
  UNIQUE KEY `Índice 1` (`id`),
  KEY `FK_clientes_ciudad` (`id_ciudad`),
  KEY `Índice 3` (`codigo`),
  CONSTRAINT `FK_clientes_ciudad` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bd_cobranza.clientes: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` (`id`, `codigo`, `cedula`, `nombre`, `apellido`, `celular`, `celular2`, `correo`, `direccion`, `id_ciudad`, `ruta_imagen`) VALUES
	(210317114947174, 1, 1094929449, 'Hector', 'Sama', '323345667', '123', 'hector.67', 'Barrio el olvido', 676657776, 'null');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

-- Volcando estructura para tabla bd_cobranza.cobranza
CREATE TABLE IF NOT EXISTS `cobranza` (
  `id` bigint(20) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `nit` varchar(50) DEFAULT NULL,
  `dirrecion` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(30) DEFAULT NULL,
  UNIQUE KEY `Ãndice 1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bd_cobranza.cobranza: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cobranza` DISABLE KEYS */;
INSERT INTO `cobranza` (`id`, `nombre`, `nit`, `dirrecion`, `telefono`, `correo`) VALUES
	(200408160540388, 'Gestor de Cobranzas', '123', 'Calle 2 norte', '318', 'gestor_cobranza@hotmail.com');
/*!40000 ALTER TABLE `cobranza` ENABLE KEYS */;

-- Volcando estructura para tabla bd_cobranza.cobros
CREATE TABLE IF NOT EXISTS `cobros` (
  `id` bigint(20) DEFAULT NULL,
  `id_credito` bigint(20) DEFAULT NULL,
  `fecha_de_pago_oportuno` date DEFAULT NULL,
  `fecha_de_pago` date DEFAULT NULL,
  `cuota_capital` double DEFAULT NULL,
  `intereses` double DEFAULT NULL,
  `honorarios` double DEFAULT NULL,
  `mora` double DEFAULT NULL,
  `cuota_total` double DEFAULT NULL,
  `abonos` double DEFAULT NULL,
  `dias_vencido` int(11) DEFAULT NULL,
  `estado` enum('Pendiente','Vencido','Pago') DEFAULT 'Pendiente',
  UNIQUE KEY `Índice 1` (`id`),
  KEY `FK__creditos` (`id_credito`),
  CONSTRAINT `FK__creditos` FOREIGN KEY (`id_credito`) REFERENCES `creditos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Coutas de los creditos';

-- Volcando datos para la tabla bd_cobranza.cobros: ~12 rows (aproximadamente)
/*!40000 ALTER TABLE `cobros` DISABLE KEYS */;
INSERT INTO `cobros` (`id`, `id_credito`, `fecha_de_pago_oportuno`, `fecha_de_pago`, `cuota_capital`, `intereses`, `honorarios`, `mora`, `cuota_total`, `abonos`, `dias_vencido`, `estado`) VALUES
	(210317150650307, 210317150649996, '2021-04-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650389, 210317150649996, '2021-05-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650397, 210317150649996, '2021-06-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650405, 210317150649996, '2021-07-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650407, 210317150649996, '2021-08-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650408, 210317150649996, '2021-09-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650410, 210317150649996, '2021-10-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650411, 210317150649996, '2021-11-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650413, 210317150649996, '2021-12-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650414, 210317150649996, '2022-01-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650416, 210317150649996, '2022-02-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente'),
	(210317150650417, 210317150649996, '2022-03-17', NULL, 166666.66666666666, 16666.666666666668, 33333.333333333336, 0, 216666.66666666666, NULL, NULL, 'Pendiente');
/*!40000 ALTER TABLE `cobros` ENABLE KEYS */;

-- Volcando estructura para tabla bd_cobranza.creditos
CREATE TABLE IF NOT EXISTS `creditos` (
  `id` bigint(20) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `id_acreedor` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `documento` varchar(100) DEFAULT NULL,
  `modelo` enum('Quincenal','Mensual') DEFAULT NULL COMMENT '2 quincenal, 1 mensual',
  `estado` enum('Pendiente','Vencido','Pago') DEFAULT NULL COMMENT '1 activo, 0 inactivo',
  `cuotas` int(11) DEFAULT NULL,
  `deuda` double DEFAULT NULL,
  `interes` double DEFAULT NULL,
  `honorarios` double DEFAULT NULL,
  `mora` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `valor_cuota` double DEFAULT NULL,
  `valor_extraordinario` double DEFAULT NULL,
  UNIQUE KEY `Índice 1` (`id`),
  KEY `FK__clientes` (`id_cliente`),
  CONSTRAINT `FK__clientes` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tablas para guardar los creditos de los clientes';

-- Volcando datos para la tabla bd_cobranza.creditos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `creditos` DISABLE KEYS */;
INSERT INTO `creditos` (`id`, `id_cliente`, `id_acreedor`, `fecha`, `documento`, `modelo`, `estado`, `cuotas`, `deuda`, `interes`, `honorarios`, `mora`, `total`, `valor_cuota`, `valor_extraordinario`) VALUES
	(210317150649996, 210317114947174, 210317150424109, '2021-03-17', 'Cheque', 'Mensual', 'Pendiente', 12, 2000000, 10, 20, 5, 2600000, 216666.66666666666, 227500);
/*!40000 ALTER TABLE `creditos` ENABLE KEYS */;

-- Volcando estructura para tabla bd_cobranza.departamento
CREATE TABLE IF NOT EXISTS `departamento` (
  `id` bigint(20) DEFAULT NULL,
  `codigo` int(11) DEFAULT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  UNIQUE KEY `Índice 1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bd_cobranza.departamento: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` (`id`, `codigo`, `nombre`) VALUES
	(4566677, 1, 'Quindio');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;

-- Volcando estructura para tabla bd_cobranza.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) DEFAULT NULL,
  `id_cobranza` bigint(20) DEFAULT NULL,
  `tipo` enum('Administrador','Empleado') DEFAULT NULL,
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `celular` varchar(50) DEFAULT NULL,
  `codigo_seguridad` int(4) DEFAULT NULL,
  UNIQUE KEY `Ãndice 1` (`id`),
  KEY `FK_usuarios_cobranza` (`id_cobranza`),
  KEY `Índice 3` (`codigo`),
  CONSTRAINT `FK_usuarios_cobranza` FOREIGN KEY (`id_cobranza`) REFERENCES `cobranza` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bd_cobranza.usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `id_cobranza`, `tipo`, `codigo`, `usuario`, `password`, `nombre`, `apellido`, `correo`, `celular`, `codigo_seguridad`) VALUES
	(200408160809781, 200408160540388, 'Administrador', 1, 'admin', '123admin', 'Administrador', 'de Cobranzas', 'admin_cobranza@paramosoft.com', '3113454567', NULL),
	(210317113919022, 200408160540388, 'Empleado', 2, 'andres', '123admin', 'Andres', 'Paramo', 'jape.8888@hotmail.com', '3202460380', NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
