-- database: ../DataBase/EcuFauna.sqlite
DROP TABLE IF EXISTS HORMIGA;

DROP TABLE IF EXISTS CATALOGOGEOGRAFIA;

DROP TABLE IF EXISTS CATALOGOALIMENTO;

DROP TABLE IF EXISTS CATALOGOTIPOGEOGRAFIA;

DROP TABLE IF EXISTS CATALOGOTIPOALIMENTO;

DROP TABLE IF EXISTS CATALOGOEVOLUCIONES;

CREATE TABLE CATALOGOTIPOALIMENTO (
    IDCATALOGOTIPOAL INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    NOMBRE VARCHAR(50) NOT NULL UNIQUE,
    DESCRIPCION VARCHAR(100) NOT NULL,
    ESTADO VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (ESTADO IN ('A', 'X')),
    FECHACREACION DATETIME DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE CATALOGOALIMENTO (
    IDCATALOGOAL INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    IDCATALOGOTIPOAL INTEGER NOT NULL REFERENCES CATALOGOTIPOALIMENTO (IDCATALOGOTIPOAL),
    NOMBRE VARCHAR(50) NOT NULL UNIQUE,
    DESCRIPCION VARCHAR(100) NOT NULL,
    ESTADO VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (ESTADO IN ('A', 'X')),
    FECHACREACION DATETIME DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE CATALOGOTIPOGEOGRAFIA (
    IDCATALOGOTIPOGEO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    NOMBRE VARCHAR(50) NOT NULL UNIQUE,
    DESCRIPCION VARCHAR(100) NOT NULL,
    ESTADO VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (ESTADO IN ('A', 'X')),
    FECHACREACION DATETIME DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE CATALOGOGEOGRAFIA (
    IDCATALOGOGEO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    IDCATALOGOTIPOGEO INTEGER NOT NULL REFERENCES CATALOGOTIPOGEOGRAFIA (IDCATALOGOTIPOGEO),
    IDREGION INTEGER REFERENCES CATALOGOGEOGRAFIA (IDCATALOGOGEO),
    NOMBRE VARCHAR(50) NOT NULL UNIQUE,
    DESCRIPCION VARCHAR(100) NOT NULL,
    ESTADO VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (ESTADO IN ('A', 'X')),
    FECHACREACION DATETIME DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE HORMIGA (
    IDHORMIGA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    IDSEXO INTEGER NOT NULL REFERENCES CATALOGOALIMENTO (IDCATALOGOAL),
    IDGENOALIMENTO INTEGER REFERENCES CATALOGOALIMENTO (IDCATALOGOAL),
    IDINGESTANATIVA INTEGER REFERENCES CATALOGOALIMENTO (IDCATALOGOAL),
    IDPROVINCIA INTEGER,
    TIPOHORMIGA VARCHAR(50) NOT NULL,
    NOMBREVIDA VARCHAR(50) NOT NULL DEFAULT 'VIVA' CHECK (NOMBREVIDA IN ('VIVA', 'MUERTA')),
    PORCENTAJEEVOLUCION INTEGER DEFAULT 0,
    ESTADO VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (ESTADO IN ('A', 'X')),
    FECHACREACION DATETIME DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE CATALOGOEVOLUCIONES (
    IDEVOLUCION INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    NOMBREEVOLUCION VARCHAR(50) NOT NULL UNIQUE,
    DESCRIPCION VARCHAR(100) NOT NULL
);