CREATE DATABASE speedfast_db;

USE speedfast_db;

CREATE TABLE repartidor (
                            id_repartidor INT PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL
);

CREATE TABLE pedido (
                        id_pedido INT PRIMARY KEY,
                        direccion VARCHAR(200) NOT NULL,
                        tipo VARCHAR(50) NOT NULL,
                        estado VARCHAR(20) NOT NULL
);

CREATE TABLE entrega (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         id_pedido INT NOT NULL,
                         id_repartidor INT NOT NULL,
                         fecha DATE NOT NULL,
                         hora TIME NOT NULL,
                         FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
                         FOREIGN KEY (id_repartidor) REFERENCES repartidor(id_repartidor)
);

CREATE TABLE cliente (
                         id_cliente INT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL
);