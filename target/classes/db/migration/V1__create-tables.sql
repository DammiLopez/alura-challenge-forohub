-- Tabla Perfil
CREATE TABLE perfiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Tabla Usuario
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    perfiles INT,
    FOREIGN KEY (perfiles) REFERENCES perfiles(id)
);

-- Tabla Curso
CREATE TABLE cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Tabla Tópicos
CREATE TABLE topicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(50),
    autor INT,
    curso INT,
    respuestas INT,
    FOREIGN KEY (autor) REFERENCES usuarios(id),
    FOREIGN KEY (curso) REFERENCES cursos(id)
);

-- Tabla Respuesta
CREATE TABLE respuestas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico INT,
    fecha_creacion DATETIME NOT NULL,
    autor INT,
    solucion BOOLEAN,
    FOREIGN KEY (topico) REFERENCES topicos(id),
    FOREIGN KEY (autor) REFERENCES usuarios(id)
);