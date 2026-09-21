# SpeedFast - Semana 6

## Descripción

Proyecto desarrollado en Java para la implementación de una interfaz gráfica de usuario (GUI) para el sistema de gestión de pedidos de SpeedFast.

En esta semana se implementó una aplicación de escritorio utilizando Java Swing, permitiendo registrar pedidos, visualizar los pedidos registrados y asignar repartidores para iniciar una entrega.

## Tecnologías utilizadas

- Java
- Java Swing
- IntelliJ IDEA
- Maven
- Git y GitHub

## Funcionalidades

### Registrar pedido

La aplicación permite registrar nuevos pedidos ingresando:

- ID del pedido
- Dirección de entrega
- Tipo de pedido:
  - Comida
  - Encomienda
  - Express

Los datos son validados antes de registrar el pedido y se muestra un mensaje de confirmación al usuario.

### Listar pedidos

Los pedidos registrados se muestran mediante una tabla (`JTable`) con las siguientes columnas:

- ID
- Dirección
- Tipo

Los pedidos son almacenados temporalmente en memoria mediante un `ArrayList`.

### Asignar repartidor e iniciar entrega

La aplicación permite seleccionar:

- Un repartidor disponible.
- Un pedido registrado.

Al presionar **Iniciar entrega**, se muestra una confirmación con la información del pedido y del repartidor seleccionado.

## Estructura del proyecto

```text
src/
└── main/
    └── java/
        ├── controlador/
        │   ├── GestorPedidos.java
        │   └── GestorRepartidores.java
        │
        ├── main/
        │   └── Main.java
        │
        ├── modelo/
        │   ├── Pedido.java
        │   └── Repartidor.java
        │
        └── vista/
            ├── VentanaPrincipal.java
            ├── VentanaRegistroPedido.java
            ├── VentanaListaPedidos.java
            └── VentanaAsignarRepartidor.java

Autor
Vicente Sepúlveda
