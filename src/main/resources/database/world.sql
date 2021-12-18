
DROP TABLE IF EXISTS `persona`;
CREATE TABLE `persona` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `empleado` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO persona VALUES (1,'solier ramos','45564369','si','rogers'),
(2,'string','12345677','SI','string'),
(3,'string','12345677','SI','string'),
(4,'string','12345677','SI','string'),
(5,'string','12345677','SI','string'),
(6,'string','12345677','SI','string'),
(7,'suarez mendoza','99999999','NO','RAUL');
