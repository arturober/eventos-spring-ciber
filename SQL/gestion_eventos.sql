-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 02-05-2023 a las 14:00:37
-- Versión del servidor: 10.7.3-MariaDB-1:10.7.3+maria~focal
-- Versión de PHP: 8.0.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `gestion_eventos`
--
CREATE DATABASE IF NOT EXISTS `gestion_eventos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestion_eventos`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `id` int(10) UNSIGNED NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `descripcion` varchar(2000) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `fecha` date NOT NULL,
  `imagen` varchar(200) NOT NULL,
  `id_creador` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`id`, `titulo`, `descripcion`, `precio`, `fecha`, `imagen`, `id_creador`) VALUES
(6, 'Buscando el Mad Monkey', 'Únete a la búsqueda del Mad Monkey en el barco de la capitán Kate Capsize\nAsí compartimos gastos, que sale caro...', '100.00', '2022-07-07', 'images/eventos/1650442723783.png', 4),
(7, 'Rebelión de las máquinas', 'Únete a la rebelión de las máquinas\nSe ha producido un pequeño retraso de 50 años, pero no vamos a conquistar nada en fase beta...', '1200.00', '2047-08-29', 'images/eventos/1650444522282.png', 5),
(9, 'Fiesta en el barco fantasma', 'Situado en las profundidades de Monkey Island, este barco con orquesta fantasma amenizará tus veladas en el infierno', '60.00', '2022-10-24', 'images/eventos/1650445303291.png', 8),
(10, 'Reunión urgente Batcueva', 'Debemos reunirnos inmediatamente\nMejor no vengáis, prefiero estar solo', '15.00', '2022-05-04', 'images/eventos/1650445742177.png', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `correo` varchar(180) NOT NULL,
  `avatar` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `correo`, `avatar`, `password`) VALUES
(3, 'Lumpy', 'lumpy@htf.com', 'images/usuarios/1650441767122.png', 'hOqcAO7AJzBZWjQ0XQO28XWC83NNZtRw4ve7IoQAvhg='),
(4, 'Guybrush Threepwood', 'guy@monkey.com', 'images/usuarios/1650442333802.png', 'CkK8l8lyf9QnJlg9eqgHsnnl1fT1IqqIkeGeIl088VI='),
(5, 'T-800', 't800@sky.net', 'images/usuarios/1650444269231.png', 'IEoKgY9ZWOA9+B1J2fTQRmTT01iQ24/K+MLiu6vZiJ0='),
(6, 'Bender', 'bender@planetexpress.com', 'images/usuarios/1650444599168.png', '1sC4QXaco/KiZuIF9Ex0MmEJnowg0hi/NEOZlQ5B65k='),
(7, 'Agente Smith', 'smith@matrix.com', 'images/usuarios/1650444700227.png', 'Y9Fe4tDcf3SpNWbhi+e2ht2Ow/hfK44zUde/8ZrXW+U='),
(8, 'Lechuck', 'lechuck@monkey.com', 'images/usuarios/1650444995543.png', 'BryO2A1rkvWLhfINtxRFFxoXvODqFf/rLg4LesqBDbI='),
(9, 'Batman', 'batman@gotham.com', 'images/usuarios/1650445601421.png', 'W5d3odM3sh9rRKo1Iog31wEgmz+p4iC8R23DEgw3xu0='),
(10, 'The Joker', 'joker@gotham.com', 'images/usuarios/1650445826842.png', 'mNISD5OAvpa+tata3CHHYUYbOlBR69a0W9wZnOEzvAQ='),
(44, 'Test', 'test@test.com', 'images/usuarios/1682866777078.png', 'A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_asiste_evento`
--

CREATE TABLE `usuario_asiste_evento` (
  `usuario` int(10) UNSIGNED NOT NULL,
  `evento` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_asiste_evento`
--

INSERT INTO `usuario_asiste_evento` (`usuario`, `evento`) VALUES
(4, 6),
(4, 9),
(6, 7),
(7, 7),
(8, 9),
(10, 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_creador` (`id_creador`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `usuario_asiste_evento`
--
ALTER TABLE `usuario_asiste_evento`
  ADD PRIMARY KEY (`usuario`,`evento`),
  ADD KEY `evento` (`evento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=193;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`id_creador`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario_asiste_evento`
--
ALTER TABLE `usuario_asiste_evento`
  ADD CONSTRAINT `usuario_asiste_evento_ibfk_1` FOREIGN KEY (`evento`) REFERENCES `evento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_asiste_evento_ibfk_2` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
