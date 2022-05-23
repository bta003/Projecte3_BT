drop database if exists esqui;
create database esqui;
use esqui;

CREATE TABLE `Usuaris` (
	`DNI` varchar(9) NOT NULL UNIQUE,
	`Nom` varchar(20) NOT NULL,
    `Cognoms` varchar(40) NOT NULL,
    `Telefon` int(9) NOT NULL,
	`Imatge` varchar(60) NOT NULL DEFAULT '/img/defaultuser',
	`email` varchar(60) NOT NULL,
	`nomusuari` varchar(20) NOT NULL,
    `contrasenya` varchar(32) NOT NULL,
	PRIMARY KEY (`DNI`)
);

CREATE TABLE `Federats` (
	`DNI` varchar(9) NOT NULL,
	`Num_federacio` varchar(6) NOT NULL,
	`Nivell` varchar(5) NOT NULL,
	PRIMARY KEY (`DNI`)
);

CREATE TABLE `Normals` (
	`DNI` varchar(9) NOT NULL,
	PRIMARY KEY (`DNI`)
);

CREATE TABLE `Familia_nombrosa` (
	`DNI` varchar(9) NOT NULL,
	`Num_carnet` varchar(14) NOT NULL,
	PRIMARY KEY (`DNI`)
);

CREATE TABLE `Cursos` (
	`ID_curs` INT NOT NULL AUTO_INCREMENT,
	`Nom` varchar(30) NOT NULL,
    `Preu_hora` int(3) NOT NULL,
	`Descripcio` varchar(255) NOT NULL,
    `Data_inici` date NOT NULL,
    `Hora_inici` time NOT NULL,
	PRIMARY KEY (`ID_curs`)
);

CREATE TABLE `Colectius` (
	`ID_curs` INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`ID_curs`)
);

CREATE TABLE `Individuals` (
	`ID_curs` INT NOT NULL,
	PRIMARY KEY (`ID_curs`)
);

CREATE TABLE `Competicio` (
	`ID_curs` INT NOT NULL,
	`nivell` varchar(4) NOT NULL,
	PRIMARY KEY (`ID_curs`)
);

CREATE TABLE `Monitors` (
	`DNI` varchar(9) NOT NULL,
	`NSS` int(9) NOT NULL,
	`Nom` varchar(20) NOT NULL,
	`Cognoms` varchar(40) NOT NULL,
	`Telefon` INT(9) NOT NULL,
	`Email` varchar(60) NOT NULL,
	PRIMARY KEY (`DNI`)
);

CREATE TABLE `Ensenya` (
	`DNI` varchar(9) NOT NULL,
	`ID_curs` INT NOT NULL,
	PRIMARY KEY (`DNI`)
);

CREATE TABLE `Lloga_curs` (
	`DNI` varchar(9) NOT NULL,
	`ID_curs` INT NOT NULL,
	`Num_hores` INT(2) NOT NULL,
    `Preu_final` DECIMAL(5) NOT NULL,
	PRIMARY KEY (`DNI`)
);

CREATE TABLE `Kit` (
	`ID_kit` INT NOT NULL AUTO_INCREMENT,
	`Preu` INT(6) NOT NULL,
	`Vegades_guardat` INT(6) NOT NULL DEFAULT 0,
	PRIMARY KEY (`ID_kit`)
);

CREATE TABLE `Conforma_kit` (
	`ID_kit` INT NOT NULL,
	`ID_material1` INT NOT NULL,
    `ID_material2` INT NOT NULL,
    `ID_material3` INT NOT NULL,
    PRIMARY KEY (`ID_kit`)
);

CREATE TABLE `Material` (
	`ID_material` INT NOT NULL AUTO_INCREMENT,
	`Marca` varchar(20) NOT NULL,
	`Model` varchar(30) NOT NULL,
	`Vegades_utilitzat` INT(2) NOT NULL,
	`Preu` INT(6) NOT NULL,
	`Imatge` varchar(60) NOT NULL DEFAULT '/img/defaultmaterial',
	`Estat` BOOLEAN NOT NULL DEFAULT true,
    `Estoc` INT(6) NOT NULL,
	PRIMARY KEY (`ID_material`)
);

CREATE TABLE `Esquis` (
	`ID_material` INT NOT NULL,
	`Altura` DECIMAL NOT NULL,
	PRIMARY KEY (`ID_material`)
);

CREATE TABLE `Pals` (
	`ID_material` INT NOT NULL,
	`Altura` DECIMAL NOT NULL,
	PRIMARY KEY (`ID_material`)
);

CREATE TABLE `Botes` (
	`ID_material` INT NOT NULL,
	`Talla` DECIMAL NOT NULL,
	PRIMARY KEY (`ID_material`)
);

CREATE TABLE `Lloga_material` (
	`DNI` varchar(9) NOT NULL,
	`ID_material` INT(7) NOT NULL,
    `data` DATE NOT NULL
);

CREATE TABLE `Lloga_kit` (
	`DNI` varchar(9) NOT NULL,
	`ID_kit` INT NOT NULL,
	`Data_guardat` DATE NOT NULL
);

CREATE TABLE `Kits_dia` (
	`dia` DATE NOT NULL,
	`kitsguardatsdia` INT(5) NOT NULL,
	PRIMARY KEY (`dia`)
);





ALTER TABLE `Conforma_kit` ADD CONSTRAINT `Conforma_kit_fk0` FOREIGN KEY (`ID_kit`) REFERENCES `Kit`(`ID_kit`);

ALTER TABLE `Conforma_kit` ADD CONSTRAINT `Conforma_kit_fk1` FOREIGN KEY (`ID_material1`) REFERENCES `Material`(`ID_material`);

ALTER TABLE `Conforma_kit` ADD CONSTRAINT `Conforma_kit_fk2` FOREIGN KEY (`ID_material2`) REFERENCES `Material`(`ID_material`);

ALTER TABLE `Conforma_kit` ADD CONSTRAINT `Conforma_kit_fk3` FOREIGN KEY (`ID_material3`) REFERENCES `Material`(`ID_material`);

ALTER TABLE `Pals` ADD CONSTRAINT `Pals_fk0` FOREIGN KEY (`ID_material`) REFERENCES `Material`(`ID_material`);

ALTER TABLE `Esquis` ADD CONSTRAINT `Esquis_fk0` FOREIGN KEY (`ID_material`) REFERENCES `Material`(`ID_material`);

ALTER TABLE `Botes` ADD CONSTRAINT `Botes_fk0` FOREIGN KEY (`ID_material`) REFERENCES `Material`(`ID_material`);

ALTER TABLE `Lloga_material` ADD CONSTRAINT `Lloga_material_fk0` FOREIGN KEY (`DNI`) REFERENCES `Usuaris`(`DNI`);

ALTER TABLE `Lloga_material` ADD CONSTRAINT `Lloga_material_fk1` FOREIGN KEY (`ID_material`) REFERENCES `Material`(`ID_material`);



ALTER TABLE `Colectius` ADD CONSTRAINT `Colectius_fk0` FOREIGN KEY (`ID_curs`) REFERENCES `Cursos`(`ID_curs`);

ALTER TABLE `Individuals` ADD CONSTRAINT `Individuals_fk0` FOREIGN KEY (`ID_curs`) REFERENCES `Cursos`(`ID_curs`);

ALTER TABLE `Competicio` ADD CONSTRAINT `Competicio_fk0` FOREIGN KEY (`ID_curs`) REFERENCES `Cursos`(`ID_curs`);

ALTER TABLE `Ensenya` ADD CONSTRAINT `Ensenya_fk0` FOREIGN KEY (`DNI`) REFERENCES `Monitors`(`DNI`);

ALTER TABLE `Ensenya` ADD CONSTRAINT `Ensenya_fk1` FOREIGN KEY (`ID_curs`) REFERENCES `Cursos`(`ID_curs`);

ALTER TABLE `Lloga_curs` ADD CONSTRAINT `Lloga_curs_fk0` FOREIGN KEY (`DNI`) REFERENCES `Usuaris`(`DNI`);

ALTER TABLE `Lloga_curs` ADD CONSTRAINT `Lloga_curs_fk1` FOREIGN KEY (`ID_curs`) REFERENCES `Cursos`(`ID_curs`);

ALTER TABLE `Federats` ADD CONSTRAINT `Federats_fk0` FOREIGN KEY (`DNI`) REFERENCES `Usuaris`(`DNI`);

ALTER TABLE `Normals` ADD CONSTRAINT `Normals_fk0` FOREIGN KEY (`DNI`) REFERENCES `Usuaris`(`DNI`);

ALTER TABLE `Familia_nombrosa` ADD CONSTRAINT `Familia_nombrosa_fk0` FOREIGN KEY (`DNI`) REFERENCES `Usuaris`(`DNI`);

ALTER TABLE `Lloga_kit` ADD CONSTRAINT `Lloga_kit_fk0` FOREIGN KEY (`DNI`) REFERENCES `Usuaris`(`DNI`);

ALTER TABLE `Lloga_kit` ADD CONSTRAINT `Lloga_kit_fk1` FOREIGN KEY (`ID_kit`) REFERENCES `Kit`(`ID_kit`);


INSERT INTO Usuaris (DNI, Nom, Cognoms, email, Telefon, nomusuari, contrasenya) VALUES
('47137446G', 'Bruno', 'Tomé Arias', 'brunota.dam1@alumnescostafreda.cat', 785883790, 'BrunoTA', md5('1234')),
('67900534A', 'Daniel', 'Alcobé Orellana', 'danielao.dam1@alumnescostafreda.cat',  622914709, 'DanielAO', md5('1234')),
('03595023P', 'Pau', 'Rubio Silva', 'paurs.dam1@alumnescostafreda.cat',  634133599, 'PauRS', md5('1234')),
('94334295W', 'Sergi', 'Florensa Rojo', 'sergifr.dam1@alumnescostafreda.cat',  688676547, 'SergiFR', md5('1234')),
('23706082M', 'Matías', 'Jurado Rodríguez', 'matiasjr.aif1@alumnescostafreda.cat',  634599574, 'MatiasJR', md5('1234'));



INSERT INTO Federats (DNI, Num_federacio, Nivell) VALUES
('67900534A', '463728', 'Alt'),
('03595023P', '463728', 'Mig');




INSERT INTO Familia_nombrosa (DNI, Num_carnet) VALUES
('47137446G', '22/666666/4444');





INSERT INTO Cursos (Nom, Preu_hora, Descripcio, Data_inici, Hora_inici) VALUES
('Curs de competició',   20, 'Curs federat de nivell baix', '2022/05/05', '12:00:00'),
('Curs de competició', 25, 'Curs federat de nivell mig', '2022/05/06', '12:00:00'),
('Curs de competició', 22, 'Curs federat de nivell alt', '2022/05/07', '12:00:00'),
('Curs individual', 15, 'Curs individual per a tothom', '2022/05/08', '12:00:00'),
('Curs col·lectiu', 12 , 'Curs col·lectiu per a grups i famílies', '2022/05/07', '12:00:00');






INSERT INTO Competicio (ID_curs, nivell) VALUES
(1, 'baix'),
(2, 'mig'),
(3, 'alt');

INSERT INTO Individuals (ID_curs) VALUES
(4);

INSERT INTO Colectius(ID_curs) VALUES
(5);

INSERT INTO Monitors (DNI, NSS, Nom, Cognoms, Telefon, email) VALUES
('84701532F', '487263677','María', 'Jesús Pla', 631588821, 'mariajp@eskydam.com'),
('21374897P', '238946483','Ignacio', 'Segovia Ayala', 635851777, 'ignaciosa@eskydam.com'),
('81240504L', '001058638','Joan', 'Antoni Fernández', 658183599, 'joanaf@eskydam.com'),
('99027727P', '519454651','Yolanda', 'Solsona Fernandez', 634621463, 'yolandasf@eskydam.com');


INSERT INTO  Ensenya (DNI, ID_curs) VALUES
('84701532F', 1),
('21374897P', 2),
('81240504L', 3);


INSERT INTO Material(Marca, Model, Vegades_utilitzat, Preu, Imatge, Estat, Estoc) VALUES
('Salomon', 'MAX 8 S', 0, 349.99, 'imatge1.jpg', true, 20),
('Dynastar', 'Speed Team Pro Open', 0, 239.91, 'imatge2.jpg', true, 20),
('Wedze', 'FR 500 SLASH 100 H', 0, 259.99, 'imatge3.jpg', true, 20),
('Wedze', 'MT90', 0, 649.99, 'imatge4.jpg', true, 20),
('Dynastar', 'Legend W84', 0, 390.72, 'imatge5.jpg', true, 20),
('Atomic', 'REDSTER G7 X12', 0, 759.99, 'imatge6.jpg', true, 20),
('Rossignol', 'Blackops Sender Freeride', 0, 569.99, 'imatge7.jpg', true, 20),
('Wedze', 'FR 500 SLASH 100', 0, 359.99, 'imatge8.jpg', true, 20),
('Dynastar', 'Legend W 84 Xp', 0, 403.13, 'imatge9.jpg', true, 20),
('Leki', 'AirFoil 3D', 0, 69.95, 'imatgea1.jpg', true, 20),
('Scott', 'SRS Scott', 0, 75, 'imatgea2.jpg', true, 20),
('Wedze', 'FR950 LIGHT', 0, 39.99, 'imatgea3.jpg', true, 20),
('Oneway', 'DIAMOND 14', 0, 69.99, 'imatgea4.jpg', true, 20),
('Kerma', 'Vector Hybrid 6', 0, 51.99, 'imatgea5.jpg', true, 20),
('Inovik', 'XC S POLE 150', 0, 29.99, 'imatgea6.jpg', true, 20),
('Scott', '540 P-lite Black Ultra Lime', 0, 28.13, 'imatgea7.jpg', true, 20),
('Rossignol', 'Tactic Jr Grey - 75', 0, 16.13, 'imatgea8.jpg', true, 20),
('K2', 'Boys Sprout', 0, 24.10, 'imatgea9.jpg', true, 20),
('Head', 'Advant Edge 85', 0, 181.30, 'imatgeb1.jpg', true, 20),
('Atomic', 'Hawx Magna 100', 0, 179.95, 'imatgeb2.jpg', true, 20),
('Salomon', 'QST Access 90 CH', 0, 364.65, 'imatgeb3.jpg', true, 20),
('Atomic', 'Hawx Ultra 110 S GW', 0, 364.65, 'imatgeb4.jpg', true, 20),
('Nordica', 'Sportmachine 85', 0, 239.99, 'imatgeb5.jpg', true, 20),
('Atomic', 'Hawx Prime W', 0, 155.40, 'imatgeb6.jpg', true, 20),
('Dalbello', 'Moro MX 90', 0, 280, 'imatgeb7.jpg', true, 20),
('Rossignol', 'Alltrack 90', 0, 269, 'imatgeb8.jpg', true, 20),
('Lange', 'XT Free 110', 0, 341.40, 'imatgeb9.jpg', true, 20);


INSERT INTO Esquis(ID_material, Altura) VALUES
(1, 170),
(2, 127),
(3, 174),
(4, 178),
(5, 168),
(6, 175),
(7, 172),
(8, 183),
(9, 177);


INSERT INTO Pals(ID_material, Altura) VALUES
(10, 120),
(11, 115),
(12, 125),
(13, 145),
(14, 130),
(15, 125),
(16, 120),
(17, 130),
(18, 125);


INSERT INTO Botes(ID_material, Talla) VALUES
(19, 24),
(20, 27),
(21, 26),
(22, 27),
(23, 25),
(24, 26),
(25, 25),
(26, 25),
(27, 27);


INSERT INTO Lloga_material (DNI, ID_material, data) VALUES
('47137446G', 1, (SELECT CURDATE())),
('47137446G', 3, (SELECT CURDATE()));



SET GLOBAL log_bin_trust_function_creators = 1;

#---------------PROCEDIMENT PER CREAR KITS----------------------

DELIMITER //
CREATE PROCEDURE crear_Kit(IN ID_mat1 INT(3), IN ID_mat2 INT(3), IN ID_mat3 INT(3))
BEGIN
    
	DECLARE Preu1 INT;
    DECLARE Preu2 INT;
    DECLARE Preu3 INT;
    DECLARE PreuTotal INT;
    
	SET Preu1 = (SELECT Preu
				 FROM Material
                 WHERE ID_material = ID_mat1);
                 
	SET Preu2 = (SELECT Preu
				 FROM Material
                 WHERE ID_material = ID_mat2);
                 
	SET Preu3 = (SELECT Preu
				 FROM Material
                 WHERE ID_material = ID_mat3);
                 
	SET PreuTotal := Preu1 + Preu2 + Preu3;
    
    
    if ((SELECT Vegades_utilitzat FROM Material WHERE ID_material = ID_mat1) < 10 AND (SELECT Vegades_utilitzat FROM Material WHERE ID_material = ID_mat2) < 10 AND (SELECT Vegades_utilitzat FROM Material WHERE ID_material = ID_mat3) < 10) THEN 
	if not exists (select * 
				   from Conforma_kit 
                   where ID_material1 = ID_mat1
                   AND ID_material2 = ID_mat2
                   AND ID_material3 = ID_mat3) then
                   
		INSERT INTO Kit (Preu, Vegades_guardat) VALUES
		((SELECT PreuTotal), 1);
    
		INSERT INTO Conforma_kit (ID_kit, ID_material1, ID_material2, ID_material3) VALUES
		((SELECT ID_kit FROM Kit ORDER BY ID_kit DESC limit 1), ID_mat1, ID_mat2, ID_mat3);
        
	else
		update Kit set Vegades_guardat = Vegades_guardat + 1 where ID_kit = (SELECT ID_kit FROM Conforma_kit WHERE ID_material1 = ID_mat1 AND ID_material2 = ID_mat2 AND ID_material3 = ID_mat3);
    end if;
		UPDATE Material SET Vegades_utilitzat = Vegades_utilitzat+1 WHERE ID_material = ID_mat1;
		UPDATE Material SET Vegades_utilitzat = Vegades_utilitzat+1 WHERE ID_material = ID_mat2;
		UPDATE Material SET Vegades_utilitzat = Vegades_utilitzat+1 WHERE ID_material = ID_mat3;
        
    end if;
    
    
END
//


#------------PROCEDIMENT PER APLICAR DESCOMPTES---------------


DELIMITER //
CREATE PROCEDURE descompte_marca(IN descompte INT(2), IN _marca VARCHAR(20))
BEGIN

	DECLARE cdesc DOUBLE;
    DECLARE cdescfinal DOUBLE;
    
    SET cdesc = descompte/100;
    SET cdescfinal = 1 - cdesc;

	UPDATE Material SET Preu = Preu * cdescfinal WHERE Marca = _marca;

END
//


#------------PROCEDIMENT PER CONTROLAR QUE NOMES ELS USUARIS FEDERATS PUGUIN LLOGAR UN CURS DE COMPETICIO---------------


DELIMITER //
CREATE PROCEDURE llogarCurs(IN _idcurs INT, IN _dni VARCHAR(9), IN _numhores INT(1))
BEGIN
    
  if exists (SELECT * FROM Competicio WHERE ID_curs=_idcurs) then
	if exists (SELECT * FROM Federats WHERE DNI=_dni) then
		INSERT INTO Lloga_curs (DNI, ID_curs, Num_hores, Preu_final) VALUES 
		(_dni, _idcurs, _numhores, (SELECT calcularPreu(_dni, _idcurs, _numhores)));
	end if;
  
  elseif exists (SELECT * FROM Individuals WHERE ID_curs=_idcurs) then
	INSERT INTO Lloga_curs (DNI, ID_curs, Num_hores, Preu_final) VALUES 
	(_dni, _idcurs, _numhores, (SELECT calcularPreu(_dni, _idcurs, _numhores)));
    
  elseif exists (SELECT * FROM Colectius WHERE ID_curs=_idcurs) then
	INSERT INTO Lloga_curs (DNI, ID_curs, Num_hores, Preu_final) VALUES 
	(_dni, _idcurs, _numhores, (SELECT calcularPreu(_dni, _idcurs, _numhores)));
  end if;
END
//


#------------FUNCIO PER CALCULAR EL PREU FINAL DEL CURS DEPENENT DEL TIPUS DE CURS I USUARI---------------

DELIMITER //
CREATE FUNCTION calcularPreu(_dni VARCHAR(9), _idcurs INT, _numhores INT(1)) RETURNS decimal(20)
BEGIN
  DECLARE preuhoraf decimal;
  DECLARE preutotal decimal;
  
  #-----------CURSOS INDIVIDUALS-----------
  
  if exists (SELECT * FROM Individuals WHERE ID_curs=_idcurs) then
	#SI LLOGA UN CURS 1 O 2 HORES APLICA UN DESCOMPTE DEL 20%
	IF _numhores IN (1, 2) THEN
		SET preuhoraf = (SELECT Preu_hora FROM Cursos WHERE ID_curs=_idcurs)*0.8;
	#SI LLOGA UN CURS 3 HORES APLICA UN DESCOMPTE DEL 30%
	ELSEIF _numhores = 3 THEN
		SET preuhoraf = (SELECT Preu_hora FROM Cursos WHERE ID_curs=_idcurs)*0.7;
	#SI LLOGA UN CURS 6 HORES APLICA UN DESCOMPTE DEL 50%
	ELSEIF _numhores = 6 THEN
		SET preuhoraf = (SELECT Preu_hora FROM Cursos WHERE ID_curs=_idcurs)*0.5;

   END IF;
	 SET preutotal= preuhoraf * _numhores;
     
  #-----------CURSOS COLECTIUS-----------
  
  ELSEif exists (SELECT * FROM Colectius WHERE ID_curs=_idcurs) then
	if exists (SELECT * FROM Familia_nombrosa WHERE DNI=_dni) then
		#SI TE CARNET DE FAMILIA NOMBROSA SE LI APLICA UN DESCOMPTE DEL 40%
		SET preuhoraf = (SELECT Preu_hora FROM Cursos WHERE ID_curs=_idcurs)*0.6; 
        SET preutotal= preuhoraf * _numhores;
        #SI NO EL TE NO SE LI APLICA CAP DESCOMPTE
	else
		SET preutotal=_numhores*(SELECT Preu_hora FROM Cursos WHERE ID_curs=_idcurs);
    end if;
    
#-----------CURSOS DE COMPETICIO-----------
    
  ELSEif exists (SELECT * FROM Competicio WHERE ID_curs=_idcurs) then
	#el preu dels equiadors federats
	if exists (SELECT * FROM Federats WHERE DNI=_dni) then
		SET preutotal=_numhores*(SELECT Preu_hora FROM Cursos WHERE ID_curs=_idcurs);	
	end if;
  end if;

  RETURN preutotal;
END
//



#------------TRIGGER PER GUARDAR EL TOTAL DE KITS QUE S'HA LLOGAT AL LLARG DEL DIA---------------

DELIMITER //
CREATE TRIGGER kitsDia
	AFTER INSERT
    ON Lloga_kit FOR EACH ROW
BEGIN
	DECLARE kitsguardats INT(5);
    SET kitsguardats = 	(select count(*) 
						 FROM Lloga_kit
						 WHERE Data_guardat= (SELECT CURDATE()));

	if exists (SELECT * FROM Kits_dia WHERE dia = (SELECT CURDATE())) then

	UPDATE Kits_dia SET kitsguardatsdia = kitsguardatsdia+1;
    
    else
		INSERT INTO Kits_dia (dia, kitsguardatsdia) VALUES
		((SELECT CURDATE()), kitsguardats);
	end if;
END
//



call crear_Kit(1, 11, 23);

INSERT INTO Lloga_kit(DNI, ID_kit, Data_guardat) VALUES
('47137446G', 1, (SELECT CURDATE()));


call llogarCurs(4, '47137446G', 6);