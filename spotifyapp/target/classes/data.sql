CREATE TABLE IF NOT EXISTS ARTISTA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nacionalidad VARCHAR(255),
    fecha_nacimiento DATE,
    fecha_fallecimiento DATE,
    biografia TEXT
    );

CREATE TABLE IF NOT EXISTS ARTISTA_GENEROS (
    artista_id INT,
    generos VARCHAR(255),
    FOREIGN KEY (artista_id) REFERENCES ARTISTA(id)
    );

CREATE TABLE IF NOT EXISTS ARTISTA_INSTRUMENTOS (
    artista_id INT,
    instrumentos VARCHAR(255),
    FOREIGN KEY (artista_id) REFERENCES ARTISTA(id)
    );

CREATE TABLE IF NOT EXISTS DISCO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_lanzamiento DATE,
    artista_id INT,
    FOREIGN KEY (artista_id) REFERENCES ARTISTA(id)
    );

CREATE TABLE IF NOT EXISTS DISCO_GENEROS (
    disco_id INT,
    generos VARCHAR(255),
    FOREIGN KEY (disco_id) REFERENCES DISCO(id)
    );

CREATE TABLE IF NOT EXISTS CANCION (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    letra TEXT,
    genero VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS DISCO_CANCIONES (
    disco_id INT,
    canciones_id INT,
    FOREIGN KEY (disco_id) REFERENCES DISCO(id),
    FOREIGN KEY (canciones_id) REFERENCES CANCION(id)
    );