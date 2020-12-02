DROP DATABASE IF EXISTS eurekabank;
CREATE DATABASE eurekabank;
USE eurekabank;
SET NAMES 'utf8';

CREATE TABLE TipoMovimiento (
	chr_tipocodigo       CHAR(3) NOT NULL,
	vch_tipodescripcion  VARCHAR(40) NOT NULL,
	vch_tipoaccion       VARCHAR(10) NOT NULL,
	vch_tipoestado       VARCHAR(15) DEFAULT 'ACTIVO' NOT NULL,
	CONSTRAINT PK_TipoMovimiento 
		PRIMARY KEY (chr_tipocodigo),
	CONSTRAINT chk_tipomovimiento_vch_tipoaccion
		CHECK (vch_tipoaccion IN ('INGRESO', 'SALIDA')),
	CONSTRAINT chk_tipomovimiento_vch_tipoestado
		CHECK (vch_tipoestado IN ('ACTIVO', 'ANULADO', 'CANCELADO'))						
) ENGINE = INNODB ;

CREATE TABLE Sucursal (
	chr_sucucodigo       CHAR(3) NOT NULL,
	vch_sucunombre       VARCHAR(50) NOT NULL,
	vch_sucuciudad       VARCHAR(30) NOT NULL,
	vch_sucudireccion    VARCHAR(50) NULL,
		int_sucucontcuenta   INTEGER NOT NULL,
	CONSTRAINT PK_Sucursal 
		PRIMARY KEY (chr_sucucodigo)
) ENGINE = INNODB ;

CREATE TABLE Empleado (
	chr_emplcodigo       CHAR(4) NOT NULL,
	vch_emplpaterno      VARCHAR(25) NOT NULL,
	vch_emplmaterno      VARCHAR(25) NOT NULL,
	vch_emplnombre       VARCHAR(30) NOT NULL,
	vch_emplciudad       VARCHAR(30) NOT NULL,
	vch_empldireccion    VARCHAR(50) NULL,
	CONSTRAINT PK_Empleado PRIMARY KEY (chr_emplcodigo)
) ENGINE = INNODB ;

CREATE TABLE Modulo (
	int_moducodigo       INTEGER NOT NULL,
	vch_modunombre       VARCHAR(50) NULL,
	vch_moduestado       VARCHAR(15) NOT NULL  DEFAULT 'ACTIVO' CHECK ( vch_moduestado IN ('ACTIVO', 'ANULADO', 'CANCELADO') ),
	CONSTRAINT PK_Modulo PRIMARY KEY (int_moducodigo)
) ENGINE = INNODB ;

CREATE TABLE Usuario (
	chr_emplcodigo       CHAR(4) NOT NULL,
	vch_emplusuario      VARCHAR(20) NOT NULL,
	vch_emplclave        VARCHAR(50) NOT NULL,
	vch_emplestado       VARCHAR(15) NULL DEFAULT 'ACTIVO' CHECK ( vch_emplestado IN ('ACTIVO', 'ANULADO', 'CANCELADO') ),
	CONSTRAINT PK_Usuario PRIMARY KEY (chr_emplcodigo),
	CONSTRAINT U_Usuario_vch_emplusuario UNIQUE (vch_emplusuario),
	FOREIGN KEY FK_Usuario_Empleado (chr_emplcodigo) REFERENCES Empleado (chr_emplcodigo)
) ENGINE = INNODB ;

CREATE TABLE Permiso (
	chr_emplcodigo       CHAR(4) NOT NULL,
	int_moducodigo       INTEGER NOT NULL,
	vch_permestado       VARCHAR(15) NOT NULL DEFAULT 'ACTIVO' CHECK ( vch_permestado IN ('ACTIVO', 'ANULADO', 'CANCELADO') ),
	CONSTRAINT PK_Permiso PRIMARY KEY (chr_emplcodigo,int_moducodigo),
	FOREIGN KEY FK_Permiso_Modulo (int_moducodigo) REFERENCES Modulo (int_moducodigo),
	FOREIGN KEY FK_Permiso_Usuario (chr_emplcodigo) REFERENCES Usuario (chr_emplcodigo)
) ENGINE = INNODB ;

CREATE TABLE LOGSESSION ( 
	ID                 INT NOT NULL AUTO_INCREMENT, 
	chr_emplcodigo     CHAR(4) NOT NULL, 
	fec_ingreso        DATETIME NOT NULL, 
	fec_salida         DATETIME NULL, 
	ip                 VARCHAR(20) NOT NULL DEFAULT 'NONE', 
	hostname           VARCHAR(100) NOT NULL DEFAULT 'NONE', 
	CONSTRAINT PK_LOG_SESSION PRIMARY KEY (ID), 
	CONSTRAINT fk_log_session_empleado 
		FOREIGN KEY (chr_emplcodigo) 
		REFERENCES Empleado (chr_emplcodigo) 
		ON DELETE RESTRICT 
		ON UPDATE RESTRICT 
) ENGINE = INNODB ;

CREATE TABLE Asignado (
	chr_asigcodigo       CHAR(6) NOT NULL,
	chr_sucucodigo       CHAR(3) NOT NULL,
	chr_emplcodigo       CHAR(4) NOT NULL,
	dtt_asigfechaalta    DATE NOT NULL,
	dtt_asigfechabaja    DATE NULL,
	CONSTRAINT PK_Asignado 
		PRIMARY KEY (chr_asigcodigo), 
	KEY idx_asignado01 (chr_emplcodigo),
	CONSTRAINT fk_asignado_empleado
		FOREIGN KEY (chr_emplcodigo)
		REFERENCES Empleado (chr_emplcodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_asignado02 (chr_sucucodigo),
	CONSTRAINT fk_asignado_sucursal
		FOREIGN KEY (chr_sucucodigo)
		REFERENCES Sucursal (chr_sucucodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB;

CREATE TABLE Cliente (
	chr_cliecodigo       CHAR(5) NOT NULL,
	vch_cliepaterno      VARCHAR(25) NOT NULL,
	vch_cliematerno      VARCHAR(25) NOT NULL,
	vch_clienombre       VARCHAR(30) NOT NULL,
	chr_cliedni          CHAR(8) NOT NULL,
	vch_clieciudad       VARCHAR(30) NOT NULL,
	vch_cliedireccion    VARCHAR(50) NOT NULL,
	vch_clietelefono     VARCHAR(20) NULL,
	vch_clieemail        VARCHAR(50) NULL,
	CONSTRAINT PK_Cliente 
		PRIMARY KEY (chr_cliecodigo)
) ENGINE = INNODB ;

CREATE TABLE Moneda (
	chr_monecodigo       CHAR(2) NOT NULL,
	vch_monedescripcion  VARCHAR(20) NOT NULL,
	CONSTRAINT PK_Moneda 
		PRIMARY KEY (chr_monecodigo)
) ENGINE = INNODB ;

CREATE TABLE Cuenta (
	chr_cuencodigo       CHAR(8) NOT NULL,
	chr_monecodigo       CHAR(2) NOT NULL,
	chr_sucucodigo       CHAR(3) NOT NULL,
	chr_emplcreacuenta   CHAR(4) NOT NULL,
	chr_cliecodigo       CHAR(5) NOT NULL,
	dec_cuensaldo        DECIMAL(12,2) NOT NULL,
	dtt_cuenfechacreacion DATE NOT NULL,
	vch_cuenestado       VARCHAR(15) DEFAULT 'ACTIVO' NOT NULL,
	int_cuencontmov      INTEGER NOT NULL,
	chr_cuenclave        CHAR(6) NOT NULL,
	CONSTRAINT chk_cuenta_chr_cuenestado
		CHECK (vch_cuenestado IN ('ACTIVO', 'ANULADO', 'CANCELADO')),
	CONSTRAINT PK_Cuenta 
		PRIMARY KEY (chr_cuencodigo), 
	KEY idx_cuenta01 (chr_cliecodigo),
	CONSTRAINT fk_cuenta_cliente
		FOREIGN KEY (chr_cliecodigo)
		REFERENCES Cliente (chr_cliecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_cuenta02 (chr_emplcreacuenta),
	CONSTRAINT fk_cuente_empleado
		FOREIGN KEY (chr_emplcreacuenta)
		REFERENCES Empleado (chr_emplcodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_cuenta03 (chr_sucucodigo),
	CONSTRAINT fk_cuenta_sucursal
		FOREIGN KEY (chr_sucucodigo)
		REFERENCES Sucursal (chr_sucucodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_cuenta04 (chr_monecodigo),
	CONSTRAINT fk_cuenta_moneda
		FOREIGN KEY (chr_monecodigo)
		REFERENCES Moneda (chr_monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE Movimiento (
	chr_cuencodigo       CHAR(8) NOT NULL,
	int_movinumero       INTEGER NOT NULL,
	dtt_movifecha        DATE NOT NULL,
	chr_emplcodigo       CHAR(4) NOT NULL,
	chr_tipocodigo       CHAR(3) NOT NULL,
	dec_moviimporte      DECIMAL(12,2) NOT NULL,
	chr_cuenreferencia   CHAR(8) NULL,
	CONSTRAINT chk_Movimiento_importe4
		CHECK (dec_moviimporte >= 0.0),		 
	CONSTRAINT PK_Movimiento 
		PRIMARY KEY (chr_cuencodigo, int_movinumero), 
	KEY idx_movimiento01 (chr_tipocodigo),
	CONSTRAINT fk_movimiento_tipomovimiento
		FOREIGN KEY (chr_tipocodigo)
		REFERENCES TipoMovimiento (chr_tipocodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	KEY idx_movimiento02 (chr_emplcodigo),
	CONSTRAINT fk_movimiento_empleado
		FOREIGN KEY (chr_emplcodigo)
		REFERENCES Empleado (chr_emplcodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_movimiento03 (chr_cuencodigo),
	CONSTRAINT fk_movimiento_cuenta
		FOREIGN KEY (chr_cuencodigo)
		REFERENCES Cuenta (chr_cuencodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE Parametro (
	chr_paracodigo       CHAR(3) NOT NULL,
	vch_paradescripcion  VARCHAR(50) NOT NULL,
	vch_paravalor        VARCHAR(70) NOT NULL,
	vch_paraestado       VARCHAR(15) DEFAULT 'ACTIVO' NOT NULL,
	CONSTRAINT chk_parametro_vch_paraestado
		CHECK (vch_paraestado IN ('ACTIVO', 'ANULADO', 'CANCELADO')),
	CONSTRAINT PK_Parametro 
		PRIMARY KEY (chr_paracodigo)
) ENGINE = INNODB ;

CREATE TABLE InteresMensual (
	chr_monecodigo       CHAR(2) NOT NULL,
	dec_inteimporte      DECIMAL(12,2) NOT NULL,
	CONSTRAINT PK_InteresMensual 
		PRIMARY KEY (chr_monecodigo), 
	KEY idx_interesmensual01 (chr_monecodigo),
	CONSTRAINT fk_interesmensual_moneda
		FOREIGN KEY (chr_monecodigo)
		REFERENCES Moneda (chr_monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE CostoMovimiento (
	chr_monecodigo       CHAR(2) NOT NULL,
	dec_costimporte      DECIMAL(12,2) NOT NULL,
	CONSTRAINT PK_CostoMovimiento 
		PRIMARY KEY (chr_monecodigo), 
	KEY idx_costomovimiento (chr_monecodigo),
	CONSTRAINT fk_costomovimiento_moneda
		FOREIGN KEY (chr_monecodigo)
		REFERENCES Moneda (chr_monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE CargoMantenimiento (
	chr_monecodigo       CHAR(2) NOT NULL,
	dec_cargMontoMaximo  DECIMAL(12,2) NOT NULL,
	dec_cargImporte      DECIMAL(12,2) NOT NULL,
	CONSTRAINT PK_CargoMantenimiento 
		PRIMARY KEY (chr_monecodigo), 
	KEY idx_cargomantenimiento01 (chr_monecodigo),
	CONSTRAINT fk_cargomantenimiento_moneda
		FOREIGN KEY (chr_monecodigo)
		REFERENCES Moneda (chr_monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE Contador (
	vch_conttabla        VARCHAR(30) NOT NULL,
	int_contitem         INTEGER NOT NULL,
	int_contlongitud     INTEGER NOT NULL,
	CONSTRAINT PK_Contador 
		PRIMARY KEY (vch_conttabla)
) ENGINE = INNODB ;

USE MYSQL;
FLUSH PRIVILEGES;
USE eurekabank;
SET NAMES utf8;

select @@tx_isolation;

SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualiza_cliente`( 
	out p_estado varchar(200), -- Parámetro de salida
	p_cliecodigo char(5), 
	p_cliepaterno varchar(25), 
	p_cliematerno varchar(25),  
	p_clienombre varchar(30), 
	p_cliedni char(8), 
	p_clieciudad varchar(30),
	p_cliedireccion varchar(50), 
	p_clietelefono varchar(20),
	p_clieemail varchar(50) 
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING, NOT FOUND
	BEGIN
		rollback;
		set p_estado = 'Error en el proceso de actualización.';
	END;
	start transaction;
	set p_estado = null;
	update cliente
	set
		vch_cliepaterno   = p_cliepaterno,
		vch_cliematerno   = p_cliematerno,
		vch_clienombre    = p_clienombre,
		chr_cliedni       = p_cliedni,
		vch_clieciudad    = p_clieciudad,
		vch_cliedireccion = p_cliedireccion,
		vch_clietelefono  = p_clietelefono,
		vch_clieemail     = p_clieemail
	where
		chr_cliecodigo = p_cliecodigo;
	commit;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `consultar_saldo`(IN p_cuenta char(8), OUT p_saldo decimal(12,2))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING, NOT FOUND
	BEGIN
		rollback;
		set p_saldo = '-1';
	END;
	select dec_cuensaldo into p_saldo from cuenta where chr_cuencodigo = p_cuenta;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `depositar`( 
	p_cuenta char(8), 
	p_importe decimal(12,2),  
	p_empleado char(4)
)
BEGIN
	DECLARE moneda char(2);
	DECLARE costoMov decimal(12,2);
	DECLARE cont int;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING, NOT FOUND
	BEGIN
		-- Cancela la transacción
		rollback; 
		-- Retorna el estado
		select -1 as state, 'Error en el proceso de actualización.' as message;
	END;
	-- Iniciar Transacción
	start transaction;
	-- Tabla Cuenta
	select int_cuencontmov, chr_monecodigo into cont, moneda from cuenta where chr_cuencodigo = p_cuenta;
	select dec_costimporte into costoMov from costomovimiento where chr_monecodigo = moneda;
	-- Registrar el deposito
	update cuenta set dec_cuensaldo = dec_cuensaldo + p_importe - costoMov, int_cuencontmov = int_cuencontmov + 2 where chr_cuencodigo = p_cuenta;
	-- Registrar el movimiento
	set cont := cont + 1;	
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha, chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia) values(p_cuenta,cont,current_date,p_empleado,'003',p_importe,null);
	-- Registrar el costo del movimiento
	set cont := cont + 1;
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha, chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia) values(p_cuenta,cont,current_date,p_empleado,'010',costoMov,null);
	-- Confirma Transacción
	commit;
	-- Retorna el estado
	select 1 as state, 'Proceso ok' as message;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `empleados_por_ciudad`()
BEGIN
	select vch_emplciudad as ciudad,  count(*) AS empleados
	from empleado
	group by vch_emplciudad
	order by vch_emplciudad ASC;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `moviminetos_cuenta`(IN p_cuenta char(8))
BEGIN
	select m.chr_cuencodigo cuenta, m.int_movinumero nromov, m.dtt_movifecha fecha, m.chr_tipocodigo tipo, t.vch_tipodescripcion descripcion, t.vch_tipoaccion accion, m.dec_moviimporte importe
    from tipomovimiento t join movimiento m on t.chr_tipocodigo = m.chr_tipocodigo where m.chr_cuencodigo = p_cuenta order by 2;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `realizar_transferencia`( 
	p_cuenta1 char(8),  -- Cuenta origen
	p_cuenta2 char(8),  -- Cuenta destino
	p_clave1  varchar(15), -- Clave de cuenta origen
	p_importe decimal(12,2), -- Importe a transferir
	p_empleado char(4)       -- Empleado que realiza la transacción
)
BEGIN
	DECLARE moneda1 char(2);
	DECLARE moneda2 char(2);
	DECLARE saldo1  decimal(12,2);
	DECLARE cargo decimal(12,2);
	DECLARE cont1 int;
	DECLARE cont2 int;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING, NOT FOUND
	BEGIN
	  -- Cancela la transacción
		rollback;
	  -- Propaga el error    
		RESIGNAL;
	END;
  -- Iniciar Transacción
	start transaction;
  -- Datos de las cuentas
	select chr_monecodigo, dec_cuensaldo, int_cuencontmov into moneda1, saldo1, cont1 from cuenta where chr_cuencodigo = p_cuenta1 and chr_cuenclave = p_clave1;
	select chr_monecodigo, int_cuencontmov into moneda2, cont2 from cuenta where chr_cuencodigo = p_cuenta2;
  -- Verifica moneda
	if ( moneda1 != moneda2 ) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error, las cuentas deben ser de la misma moneda.';
	end if;
  -- Verifica saldo
	select dec_costimporte into cargo from costomovimiento where chr_monecodigo = moneda1;
	if(  (p_importe + cargo) > saldo1 ) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error, no hay saldo suficinte.';
	end if;
  -- Registrar el retiro en cuenta origen
	update cuenta set dec_cuensaldo = dec_cuensaldo - (p_importe + cargo), int_cuencontmov = int_cuencontmov + 2 where chr_cuencodigo = p_cuenta1;
	set cont1 = cont1 + 1;
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha, chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia) values(p_cuenta1,cont1,current_date,p_empleado,'009',p_importe,p_cuenta2);
	set cont1 = cont1 + 1;
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha, chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia) values(p_cuenta1,cont1,current_date,p_empleado,'010',cargo,null);
  -- Registrar el deposito en cuenta destino
	update cuenta set dec_cuensaldo = dec_cuensaldo + p_importe - cargo, int_cuencontmov = int_cuencontmov + 2 where chr_cuencodigo = p_cuenta2;
	set cont2 = cont2 + 1;
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha, chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia) values(p_cuenta2,cont2,current_date,p_empleado,'008',p_importe,p_cuenta1);
	set cont2 = cont2 + 1;
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha, chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia) values(p_cuenta2,cont2,current_date,p_empleado,'010',cargo,null);
	commit;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `retiro`(p_cuenta char(8), p_importe decimal(12,2), p_clave char(6), p_empleado char(4))
BEGIN
  -- Variables
	DECLARE moneda char(2);
	DECLARE costoMov decimal(12,2);
	DECLARE cont int;
	DECLARE saldo decimal(12,2);
  -- Control de errores
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING, NOT FOUND
	BEGIN
		-- Cancela la transacción
		rollback; 
		-- Propaga el error
		RESIGNAL;
	END;
	-- Iniciar transacción
	start transaction;
	-- Leer datos de la cuenta
	select int_cuencontmov, chr_monecodigo, dec_cuensaldo into cont, moneda, saldo from cuenta where chr_cuencodigo = p_cuenta and chr_cuenclave = p_clave for update;
	-- Costo de Transacción
	select dec_costimporte into costoMov from costomovimiento where chr_monecodigo = moneda;
	-- Verifica saldo
	if saldo < (p_importe + costoMov) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Saldo insuficiente';
	end if;
	-- Registrar el deposito
	update cuenta set dec_cuensaldo = dec_cuensaldo - p_importe - costoMov, int_cuencontmov = int_cuencontmov + 2 where chr_cuencodigo = p_cuenta;
	-- Registrar el movimiento
	set cont := cont + 1;
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha,
    chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia)
    values(p_cuenta,cont,current_date,p_empleado,'004',p_importe,null);
	-- Registrar el costo del movimiento
	set cont := cont + 1;
	insert into movimiento(chr_cuencodigo,int_movinumero,dtt_movifecha,
    chr_emplcodigo,chr_tipocodigo,dec_moviimporte,chr_cuenreferencia)
    values(p_cuenta,cont,current_date,p_empleado,'010',costoMov,null);
	-- Confirma Transacción
	commit;
END$$
DELIMITER ;

-- triggers
create trigger contador_asignado_ai after insert on asignado for each row
	update contador
		set int_contitem = int_contitem + 1
	where vch_conttabla = 'Asignado';

create trigger contador_cliente_ai after insert on cliente for each row
	update contador
		set int_contitem = int_contitem + 1
	where vch_conttabla = 'Cliente';

create trigger contador_empleado_ai after insert on empleado for each row
	update contador
		set int_contitem = int_contitem + 1
	where vch_conttabla = 'Empleado';

create trigger contador_Moneda_ai after insert on moneda for each row
	update contador
		set int_contitem = int_contitem + 1
	where vch_conttabla = 'Moneda';

create trigger contador_parametro_ai after insert on parametro for each row
	update contador
		set int_contitem = int_contitem + 1
	where vch_conttabla = 'Parametro';

create trigger contador_Sucursal_ai after insert on sucursal for each row
	update contador
		set int_contitem = int_contitem + 1
	where vch_conttabla = 'Sucursal';

create trigger contador_TipoMovimiento_ai after insert on tipomovimiento for each row
	update contador
		set int_contitem = int_contitem + 1
	where vch_conttabla = 'TipoMovimiento';
       
-- -----------------------------------------------------------------

create trigger contador_asignado_ad after delete on asignado for each row
	update contador
		set int_contitem = int_contitem - 1
	where vch_conttabla = 'Asignado';

create trigger contador_cliente_ad after delete on cliente for each row
	update contador
		set int_contitem = int_contitem - 1
	where vch_conttabla = 'Cliente';

create trigger contador_empleado_ad after delete on empleado for each row
	update contador
		set int_contitem = int_contitem - 1
	where vch_conttabla = 'Empleado';

create trigger contador_Moneda_ad after delete on moneda for each row
	update contador
		set int_contitem = int_contitem - 1
	where vch_conttabla = 'Moneda';

create trigger contador_Parametro_ad after delete on parametro for each row
	update contador
		set int_contitem = int_contitem - 1
	where vch_conttabla = 'Parametro';

create trigger contador_Sucursal_ad after delete on sucursal for each row
	update contador
		set int_contitem = int_contitem - 1
	where vch_conttabla = 'Sucursal';

create trigger contador_TipoMoviemiento_ad after delete on tipomovimiento for each row
	update contador
		set int_contitem = int_contitem - 1
	where vch_conttabla = 'TipoMovimiento';
        
-- atrbuto redundate int-sucucontcuenta
-- Se crea el trigger que actualiza el numero de cuentas por sucursal
create trigger sucursal_cuenta_ai after insert on cuenta for each row
	update sucursal
		set int_sucucontcuenta = int_sucucontcuenta+ 1
	where chr_sucucodigo = new.chr_sucucodigo;

-- Cargar Datos

-- Tabla: Moneda
insert into moneda values ( '01', 'Soles' );
insert into moneda values ( '02', 'Dolares' );

-- Tabla: CargoMantenimiento
insert into cargomantenimiento values ( '01', 3500.00, 7.00 );
insert into cargomantenimiento values ( '02', 1200.00, 2.50 );

-- Tabla: CargoMovimiento
insert into CostoMovimiento values ( '01', 2.00 );
insert into CostoMovimiento values ( '02', 0.60 );

-- Tabla: InteresMensual
insert into InteresMensual values ( '01', 0.70 );
insert into InteresMensual values ( '02', 0.60 );

-- Tabla: TipoMovimiento
insert into TipoMovimiento values( '001', 'Apertura de Cuenta', 'INGRESO', 'ACTIVO' );
insert into TipoMovimiento values( '002', 'Cancelar Cuenta', 'SALIDA', 'ACTIVO' );
insert into TipoMovimiento values( '003', 'Deposito', 'INGRESO', 'ACTIVO' );
insert into TipoMovimiento values( '004', 'Retiro', 'SALIDA', 'ACTIVO' );
insert into TipoMovimiento values( '005', 'Interes', 'INGRESO', 'ACTIVO' );
insert into TipoMovimiento values( '006', 'Mantenimiento', 'SALIDA', 'ACTIVO' );
insert into TipoMovimiento values( '007', 'ITF', 'SALIDA', 'ACTIVO' );
insert into TipoMovimiento values( '008', 'Transferencia', 'INGRESO', 'ACTIVO' );
insert into TipoMovimiento values( '009', 'Transferencia', 'SALIDA', 'ACTIVO' );
insert into TipoMovimiento values( '010', 'Cargo por Movimiento', 'SALIDA', 'ACTIVO' );

-- Tabla: Sucursal
insert into sucursal values( '001', 'Sipan', 'Chiclayo', 'Av. Balta 1456', 2 );
insert into sucursal values( '002', 'Chan Chan', 'Trujillo', 'Jr. Independencia 456', 3 );
insert into sucursal values( '003', 'Los Olivos', 'Lima', 'Av. Central 1234', 0 );
insert into sucursal values( '004', 'Pardo', 'Lima', 'Av. Pardo 345 - Miraflores', 0 );
insert into sucursal values( '005', 'Misti', 'Arequipa', 'Bolivar 546', 0 );
insert into sucursal values( '006', 'Machupicchu', 'Cusco', 'Calle El Sol 534', 0 );
insert into sucursal values( '007', 'Grau', 'Piura', 'Av. Grau 1528', 0 );

-- Tabla: Empleado
INSERT INTO empleado VALUES( '9999', 'Internet', 'Internet', 'internet', 'Internet', 'internet' );
INSERT INTO empleado VALUES( '0001', 'Oroche', 'Quispe', 'Frank Anthony', 'Lima', 'Jr.Guillermo Zuñiga 971 - SMP' );
INSERT INTO empleado VALUES( '0002', 'Castro', 'Vargas', 'Lidia', 'Lima', 'Federico Villarreal 456 - SMP' );
INSERT INTO empleado VALUES( '0003', 'Reyes', 'Ortiz', 'Claudia', 'Lima', 'Av. Aviación 3456 - San Borja' );
INSERT INTO empleado VALUES( '0004', 'Ramos', 'Garibay', 'Angelica', 'Chiclayo', 'Calle Barcelona 345' );
INSERT INTO empleado VALUES( '0005', 'Ruiz', 'Zabaleta', 'Claudia', 'Cusco', 'Calle Cruz Verde 364' );
INSERT INTO empleado VALUES( '0006', 'Cruz', 'Tarazona', 'Ricardo', 'Areguipa', 'Calle La Gruta 304' );
INSERT INTO empleado VALUES( '0007', 'Diaz', 'Flores', 'Edith', 'Lima', 'Av. Pardo 546' );
INSERT INTO empleado VALUES( '0008', 'Sarmiento', 'Bellido', 'Claudia Rocio', 'Areguipa', 'Calle Alfonso Ugarte 1567' );
INSERT INTO empleado VALUES( '0009', 'Pachas', 'Sifuentes', 'Luis Alberto', 'Trujillo', 'Francisco Pizarro 1263' );
INSERT INTO empleado VALUES( '0010', 'Tello', 'Alarcon', 'Hugo Valentin', 'Cusco', 'Los Angeles 865' );
INSERT INTO empleado VALUES( '0011', 'Carrasco', 'Vargas', 'Pedro Hugo', 'Chiclayo', 'Av. Balta 1265' );
INSERT INTO empleado VALUES( '0012', 'Mendoza', 'Jara', 'Monica Valeria', 'Lima', 'Calle Las Toronjas 450' );
INSERT INTO empleado VALUES( '0013', 'Espinoza', 'Melgar', 'Victor Eduardo', 'Huancayo', 'Av. San Martin 6734 Dpto. 508 ' );
INSERT INTO empleado VALUES( '0014', 'Hidalgo', 'Sandoval', 'Milagros Leonor', 'Chiclayo', 'Av. Luis Gonzales 1230' );

-- Tabla: Usuario
INSERT INTO usuario VALUES( '9999',  'internet',     SHA('internet'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0001',  'admin',      SHA('admin'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0002',  'lcastro',      SHA('flaca'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0003',  'creyes',       SHA('linda'), 'ANULADO' );
INSERT INTO usuario VALUES( '0004',  'aramos',       SHA('china'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0005',  'cvalencia',    SHA('angel'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0006',  'rcruz',        SHA('cerebro'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0007',  'ediaz',        SHA('princesa'), 'ANULADO' );
INSERT INTO usuario VALUES( '0008',  'csarmiento',   SHA('chinita'), 'ANULADO' );
INSERT INTO usuario VALUES( '0009',  'lpachas',      SHA('gato'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0010',  'htello',       SHA('machupichu'), 'ACTIVO' );
INSERT INTO usuario VALUES( '0011',  'pcarrasco',    SHA('tinajones'), 'ACTIVO' );

-- Tabla: Modulo
INSERT INTO Modulo VALUES( 1, 'Procesos', 'ACTIVO');
INSERT INTO Modulo VALUES( 2, 'Tablas', 'ACTIVO');
INSERT INTO Modulo VALUES( 3, 'Consultas', 'ACTIVO');
INSERT INTO Modulo VALUES( 4, 'Reportes', 'ACTIVO');
INSERT INTO Modulo VALUES( 5, 'Util', 'ACTIVO');
INSERT INTO Modulo VALUES( 6, 'Seguridad', 'ACTIVO');

-- Tabla: Permiso
-- Usuario: 0001
INSERT INTO Permiso VALUES( '0001', 1, 'ACTIVO');
INSERT INTO Permiso VALUES( '0001', 2, 'ACTIVO');
INSERT INTO Permiso VALUES( '0001', 3, 'ACTIVO');
INSERT INTO Permiso VALUES( '0001', 4, 'ACTIVO');
INSERT INTO Permiso VALUES( '0001', 5, 'ACTIVO');
INSERT INTO Permiso VALUES( '0001', 6, 'ACTIVO');

-- Usuario: 0002
INSERT INTO Permiso VALUES( '0002', 1, 'ACTIVO');
INSERT INTO Permiso VALUES( '0002', 2, 'ACTIVO');
INSERT INTO Permiso VALUES( '0002', 3, 'ACTIVO');
INSERT INTO Permiso VALUES( '0002', 4, 'ACTIVO');
INSERT INTO Permiso VALUES( '0002', 5, 'CANCELADO');
INSERT INTO Permiso VALUES( '0002', 6, 'CANCELADO');

-- Usuario: 0003
INSERT INTO Permiso VALUES( '0003', 1, 'ACTIVO');
INSERT INTO Permiso VALUES( '0003', 2, 'CANCELADO');
INSERT INTO Permiso VALUES( '0003', 3, 'ACTIVO');
INSERT INTO Permiso VALUES( '0003', 4, 'ACTIVO');
INSERT INTO Permiso VALUES( '0003', 5, 'ACTIVO');
INSERT INTO Permiso VALUES( '0003', 6, 'CANCELADO');

-- Usuario: 0004
INSERT INTO Permiso VALUES( '0004', 1, 'CANCELADO');
INSERT INTO Permiso VALUES( '0004', 2, 'ACTIVO');
INSERT INTO Permiso VALUES( '0004', 3, 'ACTIVO');
INSERT INTO Permiso VALUES( '0004', 4, 'CANCELADO');
INSERT INTO Permiso VALUES( '0004', 5, 'ACTIVO');
INSERT INTO Permiso VALUES( '0004', 6, 'CANCELADO');

-- Usuario: 0005
INSERT INTO Permiso VALUES( '0005', 1, 'ACTIVO');
INSERT INTO Permiso VALUES( '0005', 2, 'CANCELADO');
INSERT INTO Permiso VALUES( '0005', 3, 'ACTIVO');
INSERT INTO Permiso VALUES( '0005', 4, 'ACTIVO');
INSERT INTO Permiso VALUES( '0005', 5, 'ACTIVO');
INSERT INTO Permiso VALUES( '0005', 6, 'CANCELADO');

-- Usuario: 0006
INSERT INTO Permiso VALUES( '0006', 1, 'ACTIVO');
INSERT INTO Permiso VALUES( '0006', 2, 'ACTIVO');
INSERT INTO Permiso VALUES( '0006', 3, 'ACTIVO');
INSERT INTO Permiso VALUES( '0006', 4, 'ACTIVO');
INSERT INTO Permiso VALUES( '0006', 5, 'ACTIVO');
INSERT INTO Permiso VALUES( '0006', 6, 'ACTIVO');

-- Usuario: 0007
INSERT INTO Permiso VALUES( '0007', 1, 'CANCELADO');
INSERT INTO Permiso VALUES( '0007', 2, 'ACTIVO');
INSERT INTO Permiso VALUES( '0007', 3, 'ACTIVO');
INSERT INTO Permiso VALUES( '0007', 4, 'CANCELADO');
INSERT INTO Permiso VALUES( '0007', 5, 'ACTIVO');
INSERT INTO Permiso VALUES( '0007', 6, 'CANCELADO');

-- Asignado
insert into Asignado values( '000001', '001', '0004', '20071115', null );
insert into Asignado values( '000002', '002', '0001', '20071120', null );
insert into Asignado values( '000003', '003', '0002', '20071128', null );
insert into Asignado values( '000004', '004', '0003', '20071212', '20080325' );
insert into Asignado values( '000005', '005', '0006', '20071220', null );
insert into Asignado values( '000006', '006', '0005', '20080105', null );
insert into Asignado values( '000007', '004', '0007', '20080107', null );
insert into Asignado values( '000008', '005', '0008', '20080107', null );
insert into Asignado values( '000009', '001', '0011', '20080108', null );
insert into Asignado values( '000010', '002', '0009', '20080108', null );
insert into Asignado values( '000011', '006', '0010', '20080108', null );

-- Tabla: Parametro
insert into Parametro values( '001', 'ITF - Impuesto a la Transacciones Financieras', '0.08', 'ACTIVO' );
insert into Parametro values( '002', 'Número de Operaciones Sin Costo', '15', 'ACTIVO' );

-- Tabla: Cliente
insert into cliente values( '00001', 'CORONEL', 'CASTILLO', 'ERIC GUSTAVO', '06914897', 'LIMA', 'LOS OLIVOS', '996-664-457', 'gcoronelc@gmail.com' );
insert into cliente values( '00002', 'VALENCIA', 'MORALES', 'PEDRO HUGO', '01576173', 'LIMA', 'MAGDALENA', '924-7834', 'pvalencia@terra.com.pe' );
insert into cliente values( '00003', 'MARCELO', 'VILLALOBOS', 'RICARDO', '10762367', 'LIMA', 'LINCE', '993-62966', 'ricardomarcelo@hotmail.com' );
insert into cliente values( '00004', 'ROMERO', 'CASTILLO', 'CARLOS ALBERTO', '06531983', 'LIMA', 'LOS OLIVOS', '865-84762', 'c.romero@hotmail.com' );
insert into cliente values( '00005', 'ARANDA', 'LUNA', 'ALAN ALBERTO', '10875611', 'LIMA', 'SAN ISIDRO', '834-67125', 'a.aranda@hotmail.com' );
insert into cliente values( '00006', 'AYALA', 'PAZ', 'JORGE LUIS', '10679245', 'LIMA', 'SAN BORJA', '963-34769', 'j.ayala@yahoo.com' );
insert into cliente values( '00007', 'CHAVEZ', 'CANALES', 'EDGAR RAFAEL', '10145693', 'LIMA', 'MIRAFLORES', '999-96673', 'e.chavez@gmail.com' );
insert into cliente values( '00008', 'FLORES', 'CHAFLOQUE', 'ROSA LIZET', '10773456', 'LIMA', 'LA MOLINA', '966-87567', 'r.florez@hotmail.com' );
insert into cliente values( '00009', 'FLORES', 'CASTILLO', 'CRISTIAN RAFAEL', '10346723', 'LIMA', 'LOS OLIVOS', '978-43768', 'c.flores@hotmail.com' );
insert into cliente values( '00010', 'GONZALES', 'GARCIA', 'GABRIEL ALEJANDRO', '10192376', 'LIMA', 'SAN MIGUEL', '945-56782', 'g.gonzales@yahoo.es' );
insert into cliente values( '00011', 'LAY', 'VALLEJOS', 'JUAN CARLOS', '10942287', 'LIMA', 'LINCE', '956-12657', 'j.lay@peru.com' );
insert into cliente values( '00012', 'MONTALVO', 'SOTO', 'DEYSI LIDIA', '10612376', 'LIMA', 'SURCO', '965-67235', 'd.montalvo@hotmail.com' );
insert into cliente values( '00013', 'RICALDE', 'RAMIREZ', 'ROSARIO ESMERALDA', '10761324', 'LIMA', 'MIRAFLORES', '991-23546', 'r.ricalde@gmail.com' );
insert into cliente values( '00014', 'RODRIGUEZ', 'FLORES', 'ENRIQUE MANUEL', '10773345', 'LIMA', 'LINCE', '976-82838', 'e.rodriguez@gmail.com' );
insert into cliente values( '00015', 'ROJAS', 'OSCANOA', 'FELIX NINO', '10238943', 'LIMA', 'LIMA', '962-32158', 'f.rojas@yahoo.com' );
insert into cliente values( '00016', 'TEJADA', 'DEL AGUILA', 'TANIA LORENA', '10446791', 'LIMA', 'PUEBLO LIBRE', '966-23854', 't.tejada@hotmail.com' );
insert into cliente values( '00017', 'VALDEVIESO', 'LEYVA', 'LIDIA ROXANA', '10452682', 'LIMA', 'SURCO', '956-78951', 'r.valdivieso@terra.com.pe' );
insert into cliente values( '00018', 'VALENTIN', 'COTRINA', 'JUAN DIEGO', '10398247', 'LIMA', 'LA MOLINA', '921-12456', 'j.valentin@terra.com.pe' );
insert into cliente values( '00019', 'YAURICASA', 'BAUTISTA', 'YESABETH', '10934584', 'LIMA', 'MAGDALENA', '977-75777', 'y.yauricasa@terra.com.pe' );
insert into cliente values( '00020', 'ZEGARRA', 'GARCIA', 'FERNANDO MOISES', '10772365', 'LIMA', 'SAN ISIDRO', '936-45876', 'f.zegarra@hotmail.com' );

-- Tabla: Cuenta
insert into cuenta values('00200001','01','002','0001','00008',7000,'20080105','ACTIVO',15,'123456');
insert into cuenta values('00200002','01','002','0001','00001',6800,'20080109','ACTIVO',3,'123456');
insert into cuenta values('00200003','02','002','0001','00007',6000,'20080111','ACTIVO',6,'123456');
insert into cuenta values('00100001','01','001','0004','00005',6900,'20080106','ACTIVO',7,'123456');
insert into cuenta values('00100002','02','001','0004','00005',4500,'20080108','ACTIVO',4,'123456');
insert into cuenta values('00300001','01','003','0002','00010',0000,'20080107','CANCELADO',3,'123456');

-- Tabla: Movimiento
insert into movimiento values('00100002',01,'20080108','0004','001',1800,null);
insert into movimiento values('00100002',02,'20080125','0004','004',1000,null);
insert into movimiento values('00100002',03,'20080213','0004','003',2200,null);
insert into movimiento values('00100002',04,'20080308','0004','003',1500,null);
insert into movimiento values('00100001',01,'20080106','0004','001',2800,null);
insert into movimiento values('00100001',02,'20080115','0004','003',3200,null);
insert into movimiento values('00100001',03,'20080120','0004','004',0800,null);
insert into movimiento values('00100001',04,'20080214','0004','003',2000,null);
insert into movimiento values('00100001',05,'20080225','0004','004',0500,null);
insert into movimiento values('00100001',06,'20080303','0004','004',0800,null);
insert into movimiento values('00100001',07,'20080315','0004','003',1000,null);
insert into movimiento values('00200003',01,'20080111','0001','001',2500,null);
insert into movimiento values('00200003',02,'20080117','0001','003',1500,null);
insert into movimiento values('00200003',03,'20080120','0001','004',0500,null);
insert into movimiento values('00200003',04,'20080209','0001','004',0500,null);
insert into movimiento values('00200003',05,'20080225','0001','003',3500,null);
insert into movimiento values('00200003',06,'20080311','0001','004',0500,null);
insert into movimiento values('00200002',01,'20080109','0001','001',3800,null);
insert into movimiento values('00200002',02,'20080120','0001','003',4200,null);
insert into movimiento values('00200002',03,'20080306','0001','004',1200,null);
insert into movimiento values('00200001',01,'20080105','0001','001',5000,null);
insert into movimiento values('00200001',02,'20080107','0001','003',4000,null);
insert into movimiento values('00200001',03,'20080109','0001','004',2000,null);
insert into movimiento values('00200001',04,'20080111','0001','003',1000,null);
insert into movimiento values('00200001',05,'20080113','0001','003',2000,null);
insert into movimiento values('00200001',06,'20080115','0001','004',4000,null);
insert into movimiento values('00200001',07,'20080119','0001','003',2000,null);
insert into movimiento values('00200001',08,'20080121','0001','004',3000,null);
insert into movimiento values('00200001',09,'20080123','0001','003',7000,null);
insert into movimiento values('00200001',10,'20080127','0001','004',1000,null);
insert into movimiento values('00200001',11,'20080130','0001','004',3000,null);
insert into movimiento values('00200001',12,'20080204','0001','003',2000,null);
insert into movimiento values('00200001',13,'20080208','0001','004',4000,null);
insert into movimiento values('00200001',14,'20080213','0001','003',2000,null);
insert into movimiento values('00200001',15,'20080219','0001','004',1000,null);
insert into movimiento values('00300001',01,'20080107','0002','001',5600,null);
insert into movimiento values('00300001',02,'20080118','0002','003',1400,null);
insert into movimiento values('00300001',03,'20080125','0002','002',7000,null);

--  Tabla: Contador
insert into Contador Values( 'Moneda', 2, 2 );
insert into Contador Values( 'TipoMovimiento', 10, 3 );
insert into Contador Values( 'Sucursal', 7, 3 );
insert into Contador Values( 'Empleado', 14, 4 );
insert into Contador Values( 'Asignado', 11, 6 );
insert into Contador Values( 'Parametro', 2, 3 );
insert into Contador Values( 'Cliente', 20, 5 );

SELECT chr_emplcodigo AS Codigo, vch_emplpaterno AS Apellido FROM empleado WHERE vch_emplciudad = "Lima" ORDER BY vch_emplpaterno ASC LIMIT 3;
SELECT vch_cliepaterno AS Apellidos, chr_cliedni AS DNI, vch_cliedireccion AS Distrito, vch_clietelefono AS Telefono FROM cliente ORDER BY vch_cliepaterno ASC;
SELECT count(chr_cuencodigo) AS Cantidad_Cuentas, chr_monecodigo AS Tipo_Moneda FROM cuenta GROUP BY chr_monecodigo;
SELECT min(dec_moviimporte) AS Minimo_Descuento, max(dec_moviimporte) AS Maximo_Descuento FROM movimiento;
SELECT count(chr_emplcodigo) AS Cantidad_Empleados, chr_sucucodigo AS Sucursal FROM asignado GROUP BY chr_sucucodigo;

SELECT chr_cuencodigo, dtt_movifecha, dec_moviimporte FROM movimiento WHERE chr_tipocodigo IN ( SELECT chr_tipocodigo FROM tipomovimiento where vch_tipoaccion = "INGRESO");
SELECT chr_cuencodigo, int_movinumero, dec_moviimporte FROM movimiento WHERE dec_moviimporte > (SELECT AVG(dec_moviimporte) FROM movimiento);
SELECT chr_cuencodigo, dtt_cuenfechacreacion, dec_cuensaldo FROM cuenta WHERE chr_cuencodigo > ALL (SELECT chr_cuencodigo FROM movimiento WHERE chr_cuencodigo = 00100001);
SELECT vch_emplpaterno, vch_emplnombre, vch_emplciudad, vch_empldireccion FROM empleado WHERE chr_emplcodigo IN (SELECT chr_emplcodigo FROM usuario WHERE vch_emplestado = "ANULADO");
SELECT chr_emplcodigo AS Codigo_Empleado, chr_asigcodigo AS Codigo_Asignado FROM asignado WHERE chr_sucucodigo IN ( SELECT chr_sucucodigo FROM sucursal WHERE vch_sucuciudad = "LIMA");
SELECT vch_emplpaterno, vch_emplnombre, vch_emplciudad, vch_empldireccion FROM empleado WHERE chr_emplcodigo IN (SELECT chr_emplcodigo FROM permiso WHERE vch_permestado = "ACTIVO");
SELECT chr_cuencodigo, dtt_cuenfechacreacion FROM cuenta WHERE chr_sucucodigo IN ( SELECT chr_sucucodigo FROM sucursal WHERE vch_sucuciudad = "LIMA");

SELECT e.vch_emplpaterno, e.vch_emplnombre, a.dtt_asigfechaalta FROM asignado AS a INNER JOIN empleado AS e ON a.chr_emplcodigo = e.chr_emplcodigo;
SELECT a.chr_asigcodigo, s.vch_sucunombre,  a.dtt_asigfechaalta FROM asignado AS a INNER JOIN sucursal AS s ON a.chr_sucucodigo = s.chr_sucucodigo;
SELECT e.vch_emplpaterno AS Apellidos, s.vch_sucunombre AS Sucursal, a.dtt_asigfechaalta AS Fecha_alta FROM sucursal AS s INNER JOIN asignado AS a ON s.chr_sucucodigo = a.chr_sucucodigo INNER JOIN empleado AS e ON a.chr_emplcodigo = e.chr_emplcodigo;
SELECT cl.vch_cliepaterno, cl.vch_clienombre, cu.chr_cuencodigo, cu.dec_cuensaldo FROM cliente AS cl LEFT JOIN cuenta AS cu ON cl.chr_cliecodigo = cu.chr_cliecodigo;
SELECT cl.vch_cliepaterno, cl.vch_clienombre, cu.chr_cuencodigo, cu.dec_cuensaldo FROM cliente AS cl RIGHT JOIN cuenta AS cu ON cl.chr_cliecodigo = cu.chr_cliecodigo;
SELECT cl.vch_cliepaterno, cl.vch_clienombre, cu.chr_cuencodigo, m.vch_monedescripcion AS Tipo_moneda FROM cliente AS cl INNER JOIN cuenta AS cu ON cl.chr_cliecodigo = cu.chr_cliecodigo INNER JOIN moneda AS m ON cu.chr_monecodigo = m.chr_monecodigo;
SELECT e.vch_emplpaterno, e.vch_emplnombre, m.vch_modunombre, p.vch_permestado FROM empleado AS e INNER JOIN permiso AS p ON e.chr_emplcodigo = p.chr_emplcodigo INNER JOIN modulo AS m ON p.int_moducodigo = m.int_moducodigo;
SELECT cl.vch_cliepaterno, cl.vch_clienombre, t.vch_tipodescripcion, m.dec_moviimporte, cu.chr_cuencodigo, cu.chr_cuenclave FROM cliente AS cl INNER JOIN cuenta AS cu ON cl.chr_cliecodigo = cu.chr_cliecodigo INNER JOIN movimiento AS m ON cu.chr_cuencodigo = m.chr_cuencodigo INNER JOIN tipomovimiento AS t ON m.chr_tipocodigo = t.chr_tipocodigo;

-- Informacion de los Usuarios que tienen y no permisos - Left Join 
SELECT * FROM usuario LEFT JOIN permiso 
ON usuario.chr_emplcodigo = permiso.chr_emplcodigo;

-- Usuarios que no tienen permisos - Left Join Minus
SELECT * FROM usuario LEFT JOIN permiso 
ON usuario.chr_emplcodigo = permiso.chr_emplcodigo
WHERE permiso.chr_emplcodigo IS NULL;

-- Informacion de los Clientes que tienen y no cuenta - Right Join 
SELECT * FROM cuenta RIGHT JOIN cliente 
ON cuenta.chr_cliecodigo = cliente.chr_cliecodigo;

-- Los clientes que no tienen una cuenta - Right Join Minus
SELECT * FROM cuenta RIGHT JOIN cliente 
ON cuenta.chr_cliecodigo = cliente.chr_cliecodigo 
WHERE cuenta.chr_cliecodigo IS NULL;

--  Mostrar el producto cartesiano la informacion de consulta y moneda
-- Primero mostramos las tablas
SELECT * from cliente;
SELECT * from moneda;

-- Ahora mostramos el producto cartesiano con el CROSS JOIN
SELECT * from cliente CROSS JOIN moneda;

-- Usuarios que no tienen permisos - Left Join Minus
SELECT usuario.chr_emplcodigo, usuario.vch_emplusuario 
FROM usuario LEFT JOIN permiso 
ON usuario.chr_emplcodigo = permiso.chr_emplcodigo
WHERE permiso.chr_emplcodigo IS NULL;

-- Consulta basica
select m.chr_cuencodigo cuenta, m.int_movinumero nromov, 
m.dtt_movifecha fecha, m.chr_tipocodigo tipo, t.vch_tipodescripcion descripcion, 
t.vch_tipoaccion accion, m.dec_moviimporte importe
from tipomovimiento t, movimiento m 
where m.chr_cuencodigo = 00100001 and t.chr_tipocodigo = m.chr_tipocodigo;

-- Consulta optimizada
SELECT m.chr_cuencodigo cuenta, m.int_movinumero nromov, 
m.dtt_movifecha fecha, m.chr_tipocodigo tipo, 
t.vch_tipodescripcion descripcion, t.vch_tipoaccion accion, m.dec_moviimporte importe
FROM tipomovimiento t INNER JOIN movimiento m 
ON t.chr_tipocodigo = m.chr_tipocodigo
WHERE m.chr_cuencodigo = 00100001 ORDER BY 2;

select vch_emplciudad as ciudad,  count AS empleados from empleado group by vch_emplciudad ASC;