CREATE TABLE IF NOT EXISTS Genero (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE
    );

INSERT INTO Genero (nombre) VALUES ('JAZZ'), ('BLUES'), ('ROCK'), ('ELECTRONICA'), ('CLASICA'), ('TANGO');

CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Cancion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    letra TEXT,
    genero_id INT,
    FOREIGN KEY (genero_id) REFERENCES Genero(id)
    );

CREATE TABLE IF NOT EXISTS PuntajeCancion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cancion_id INT,
    usuario_id INT,
    puntaje DECIMAL(2, 1) CHECK (puntaje >= 0 AND puntaje <= 5),
    FOREIGN KEY (cancion_id) REFERENCES Cancion(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
    );

CREATE TABLE IF NOT EXISTS Artista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nacionalidad VARCHAR(255),
    fecha_nacimiento DATE,
    fecha_fallecimiento DATE,
    biografia TEXT
    );

CREATE TABLE IF NOT EXISTS ArtistaGenero (
    artista_id INT,
    genero_id INT,
    PRIMARY KEY (artista_id, genero_id),
    FOREIGN KEY (artista_id) REFERENCES Artista(id),
    FOREIGN KEY (genero_id) REFERENCES Genero(id)
    );

CREATE TABLE IF NOT EXISTS ArtistaInstrumentos (
    artista_id INT,
    instrumentos VARCHAR(255),
    FOREIGN KEY (artista_id) REFERENCES Artista(id)
    );

CREATE TABLE IF NOT EXISTS Disco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_lanzamiento DATE,
    artista_id INT,
    FOREIGN KEY (artista_id) REFERENCES Artista(id)
    );

CREATE TABLE IF NOT EXISTS PuntajeDisco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    disco_id INT,
    usuario_id INT,
    puntaje DECIMAL(2, 1) CHECK (puntaje >= 0 AND puntaje <= 5),
    FOREIGN KEY (disco_id) REFERENCES Disco(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
    );

CREATE TABLE IF NOT EXISTS DiscoGenero (
    disco_id INT,
    genero_id INT,
    PRIMARY KEY (disco_id, genero_id),
    FOREIGN KEY (disco_id) REFERENCES Disco(id),
    FOREIGN KEY (genero_id) REFERENCES Genero(id)
    );

CREATE TABLE IF NOT EXISTS DiscoCancion (
    disco_id INT,
    cancion_id INT,
    PRIMARY KEY (disco_id, cancion_id),
    FOREIGN KEY (disco_id) REFERENCES Disco(id),
    FOREIGN KEY (cancion_id) REFERENCES Cancion(id)
    );
