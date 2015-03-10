
-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.23-log - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para ludwen
DROP DATABASE IF EXISTS `ludwen`;
CREATE DATABASE IF NOT EXISTS `ludwen` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ludwen`;


-- Volcando estructura para tabla ludwen.cierrediario
DROP TABLE IF EXISTS `cierrediario`;
CREATE TABLE IF NOT EXISTS `cierrediario` (
  `SECUENCIA` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Campo llave',
  `CODIGO_TIENDA` int(11) NOT NULL DEFAULT '1',
  `FECHA_CIERRE` datetime NOT NULL COMMENT 'Fecha del Cierre Diario',
  `CAJA_INICIO` decimal(10,2) DEFAULT NULL COMMENT 'Monto inicial de la caja',
  `CANTIDAD_500` int(11) DEFAULT NULL COMMENT 'Cantidad billetes de 500',
  `CANTIDAD_100` int(11) DEFAULT NULL COMMENT 'Cantidad billetes de 100',
  `CANTIDAD_50` int(11) DEFAULT NULL COMMENT 'Cantidad billetes de 50',
  `CANTIDAD_20` int(11) DEFAULT NULL COMMENT 'Cantidad billetes de 20',
  `CANTIDAD_10` int(11) DEFAULT NULL COMMENT 'Cantidad billetes de 10',
  `CANTIDAD_5` int(11) DEFAULT NULL COMMENT 'Cantidad billetes de 5',
  `CANTIDAD_1` int(11) DEFAULT NULL COMMENT 'Cantidad billetes de 1',
  `MONTO_MONEDA` decimal(10,2) DEFAULT NULL COMMENT 'Monto en monedas',
  `MONTO_FACTURADO` decimal(10,2) DEFAULT NULL COMMENT 'Monto facturado del día',
  `MONTO_POS1` decimal(10,2) DEFAULT NULL COMMENT 'Monto registrado POS 1 (Continental)',
  `MONTO_POS2` decimal(10,2) DEFAULT NULL COMMENT 'Monto registrado POS 2 (Atlantida)',
  `MONTO_JUSTIFICACION` decimal(10,2) DEFAULT NULL COMMENT 'Monto justificación Diferencia',
  `JUSTIFICACION` varchar(2048) DEFAULT NULL COMMENT 'Leyenda de la justificación en la diferencia',
  `MONTO_DEPOSITO` decimal(10,2) DEFAULT NULL COMMENT 'Monto a depositar del día',
  `CAJA_FINAL` decimal(10,2) DEFAULT NULL COMMENT 'Monto final que queda en caja',
  PRIMARY KEY (`SECUENCIA`,`CODIGO_TIENDA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Registra los cierres diario Tiendas Ludwen';

-- Volcando datos para la tabla ludwen.cierrediario: ~0 rows (aproximadamente)
DELETE FROM `cierrediario`;
/*!40000 ALTER TABLE `cierrediario` DISABLE KEYS */;
/*!40000 ALTER TABLE `cierrediario` ENABLE KEYS */;


-- Volcando estructura para tabla ludwen.tiendas
DROP TABLE IF EXISTS `tiendas`;
CREATE TABLE IF NOT EXISTS `tiendas` (
  `CODIGO` int(11) NOT NULL DEFAULT '0',
  `NOMBRE` varchar(64) DEFAULT NULL,
  `UBICACION` varchar(1028) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ludwen.tiendas: ~0 rows (aproximadamente)
DELETE FROM `tiendas`;
/*!40000 ALTER TABLE `tiendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiendas` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
