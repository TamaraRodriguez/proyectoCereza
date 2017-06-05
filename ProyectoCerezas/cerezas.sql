-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-06-2017 a las 11:00:27
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cerezas`
--
CREATE DATABASE IF NOT EXISTS `cerezas` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `cerezas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agricultores`
--

DROP TABLE IF EXISTS `agricultores`;
CREATE TABLE `agricultores` (
  `n_socio` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `baja` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `albaranes_entrada`
--

DROP TABLE IF EXISTS `albaranes_entrada`;
CREATE TABLE `albaranes_entrada` (
  `n_albaran` int(11) NOT NULL,
  `n_socio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `n_factura` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `albaranes_salida`
--

DROP TABLE IF EXISTS `albaranes_salida`;
CREATE TABLE `albaranes_salida` (
  `n_albaran` int(11) NOT NULL,
  `n_cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `n_factura` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `albaranes_salida`
--

INSERT INTO `albaranes_salida` (`n_albaran`, `n_cliente`, `fecha`, `n_factura`) VALUES
(2, 1, '2017-06-01', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `n_cliente` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `baja` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`n_cliente`, `id_persona`, `baja`) VALUES
(1, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_e`
--

DROP TABLE IF EXISTS `factura_e`;
CREATE TABLE `factura_e` (
  `precio_bruto` double(10,2) NOT NULL,
  `iva` int(11) NOT NULL,
  `precio_neto` double(10,2) NOT NULL,
  `n_factura` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_s`
--

DROP TABLE IF EXISTS `factura_s`;
CREATE TABLE `factura_s` (
  `n_factura` int(11) NOT NULL,
  `precio_bruto` double(10,2) NOT NULL,
  `iva` int(11) NOT NULL,
  `precio_neto` double(10,2) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura_s`
--

INSERT INTO `factura_s` (`n_factura`, `precio_bruto`, `iva`, `precio_neto`, `fecha`) VALUES
(1, 20.00, 21, 25.00, '2017-06-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lineas_albaranes_e`
--

DROP TABLE IF EXISTS `lineas_albaranes_e`;
CREATE TABLE `lineas_albaranes_e` (
  `n_albaran` int(11) NOT NULL,
  `id_linea_e` int(11) NOT NULL,
  `tipo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `peso` double(10,2) NOT NULL,
  `precio_kg` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lineas_albaranes_s`
--

DROP TABLE IF EXISTS `lineas_albaranes_s`;
CREATE TABLE `lineas_albaranes_s` (
  `n_albaran` int(11) NOT NULL,
  `id_linea` int(11) NOT NULL,
  `tipo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `numero_cajas` int(11) NOT NULL,
  `peso_caja` double(10,2) NOT NULL,
  `precio_caja` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

DROP TABLE IF EXISTS `personas`;
CREATE TABLE `personas` (
  `id_persona` int(11) NOT NULL,
  `cif_nif` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `nombre_razon_social` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellidos` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(12) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id_persona`, `cif_nif`, `nombre_razon_social`, `apellidos`, `direccion`, `telefono`, `email`) VALUES
(1, '123', 'prueba de insercion', 'asdg', 'direc', 'telef', '@');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `variedades`
--

DROP TABLE IF EXISTS `variedades`;
CREATE TABLE `variedades` (
  `tipo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `precio_kg` double(10,2) NOT NULL,
  `peso_caja` double(10,2) NOT NULL,
  `precio_caja` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agricultores`
--
ALTER TABLE `agricultores`
  ADD PRIMARY KEY (`n_socio`),
  ADD KEY `id_persona` (`id_persona`);

--
-- Indices de la tabla `albaranes_entrada`
--
ALTER TABLE `albaranes_entrada`
  ADD PRIMARY KEY (`n_albaran`),
  ADD KEY `n_socio` (`n_socio`),
  ADD KEY `fk_albaranes_entrada_factura` (`n_factura`);

--
-- Indices de la tabla `albaranes_salida`
--
ALTER TABLE `albaranes_salida`
  ADD PRIMARY KEY (`n_albaran`),
  ADD KEY `n_cliente` (`n_cliente`),
  ADD KEY `n_factura` (`n_factura`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`n_cliente`),
  ADD KEY `id_persona` (`id_persona`);

--
-- Indices de la tabla `factura_e`
--
ALTER TABLE `factura_e`
  ADD PRIMARY KEY (`n_factura`);

--
-- Indices de la tabla `factura_s`
--
ALTER TABLE `factura_s`
  ADD PRIMARY KEY (`n_factura`);

--
-- Indices de la tabla `lineas_albaranes_e`
--
ALTER TABLE `lineas_albaranes_e`
  ADD PRIMARY KEY (`id_linea_e`),
  ADD KEY `n_albaran` (`n_albaran`),
  ADD KEY `tipo` (`tipo`);

--
-- Indices de la tabla `lineas_albaranes_s`
--
ALTER TABLE `lineas_albaranes_s`
  ADD PRIMARY KEY (`id_linea`),
  ADD KEY `n_albaran` (`n_albaran`),
  ADD KEY `tipo` (`tipo`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id_persona`),
  ADD UNIQUE KEY `cif_nif` (`cif_nif`);

--
-- Indices de la tabla `variedades`
--
ALTER TABLE `variedades`
  ADD PRIMARY KEY (`tipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agricultores`
--
ALTER TABLE `agricultores`
  MODIFY `n_socio` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `albaranes_entrada`
--
ALTER TABLE `albaranes_entrada`
  MODIFY `n_albaran` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `albaranes_salida`
--
ALTER TABLE `albaranes_salida`
  MODIFY `n_albaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `n_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `factura_e`
--
ALTER TABLE `factura_e`
  MODIFY `n_factura` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `factura_s`
--
ALTER TABLE `factura_s`
  MODIFY `n_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `lineas_albaranes_e`
--
ALTER TABLE `lineas_albaranes_e`
  MODIFY `id_linea_e` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `lineas_albaranes_s`
--
ALTER TABLE `lineas_albaranes_s`
  MODIFY `id_linea` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agricultores`
--
ALTER TABLE `agricultores`
  ADD CONSTRAINT `agricultores_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `albaranes_entrada`
--
ALTER TABLE `albaranes_entrada`
  ADD CONSTRAINT `fk_albaranes_entrada_agricultores` FOREIGN KEY (`n_socio`) REFERENCES `agricultores` (`n_socio`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_albaranes_entrada_factura` FOREIGN KEY (`n_factura`) REFERENCES `factura_e` (`n_factura`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `albaranes_salida`
--
ALTER TABLE `albaranes_salida`
  ADD CONSTRAINT `albaranes_salida_clientes` FOREIGN KEY (`n_cliente`) REFERENCES `clientes` (`n_cliente`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `albaranes_salida_facturas` FOREIGN KEY (`n_factura`) REFERENCES `factura_s` (`n_factura`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `lineas_albaranes_e`
--
ALTER TABLE `lineas_albaranes_e`
  ADD CONSTRAINT `lineas_albaranes_e_ibfk_1` FOREIGN KEY (`n_albaran`) REFERENCES `albaranes_entrada` (`n_albaran`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lineas_albaranes_e_variedades` FOREIGN KEY (`tipo`) REFERENCES `variedades` (`tipo`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `lineas_albaranes_s`
--
ALTER TABLE `lineas_albaranes_s`
  ADD CONSTRAINT `lineas_albaranes_s_ibfk_1` FOREIGN KEY (`n_albaran`) REFERENCES `albaranes_salida` (`n_albaran`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lineas_albaranes_s_variedades` FOREIGN KEY (`tipo`) REFERENCES `variedades` (`tipo`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
