# SpeedFast

Aplicación de escritorio desarrollada en Java para la gestión de pedidos y entregas de la empresa SpeedFast.

El proyecto utiliza Java Swing para la interfaz gráfica y JDBC para la conexión y persistencia de información en una base de datos MySQL.

## Descripción del proyecto

SpeedFast permite gestionar las principales operaciones relacionadas con los pedidos y las entregas, utilizando una arquitectura separada en modelos, DAO, controladores y vistas.

La aplicación permite registrar, consultar, modificar y eliminar información de:

- Clientes
- Repartidores
- Pedidos
- Entregas

Además, permite asignar repartidores a pedidos y registrar la fecha y hora de las entregas.

## Funcionalidades

### Clientes

- Registrar clientes.
- Listar clientes.
- Editar clientes.
- Eliminar clientes.
- Validación de campos obligatorios.

### Repartidores

- Registrar repartidores.
- Listar repartidores.
- Editar repartidores.
- Eliminar repartidores.
- Validación de datos.
- Confirmación antes de eliminar registros.

### Pedidos

- Registrar pedidos.
- Listar pedidos.
- Editar pedidos.
- Eliminar pedidos.
- Seleccionar tipo de pedido:
  - COMIDA
  - ENCOMIENDA
  - EXPRESS
- Seleccionar estado:
  - PENDIENTE
  - EN_REPARTO
  - ENTREGADO

### Entregas

- Asignar un repartidor a un pedido.
- Registrar fecha y hora de la entrega.
- Listar entregas.
- Editar entregas.
- Eliminar entregas.
- Seleccionar pedidos y repartidores mediante listas desplegables.

## Tecnologías utilizadas

- Java
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- Git y GitHub

## Estructura del proyecto

```text
src/
└── main/
    └── java/
        ├── controlador/
        │   └── GestorPedidos.java
        │
        ├── dao/
        │   ├── ClienteDAO.java
        │   ├── ConexionDB.java
        │   ├── EntregaDAO.java
        │   ├── PedidoDAO.java
        │   └── RepartidorDAO.java
        │
        ├── main/
        │   └── Main.java
        │
        ├── modelo/
        │   ├── Cliente.java
        │   ├── Entrega.java
        │   ├── EstadoPedido.java
        │   ├── Pedido.java
        │   └── Repartidor.java
        │
        └── vista/
            ├── VentanaAsignarRepartidor.java
            ├── VentanaEditarCliente.java
            ├── VentanaEditarEntrega.java
            ├── VentanaEditarPedido.java
            ├── VentanaEditarRepartidor.java
            ├── VentanaListaClientes.java
            ├── VentanaListaEntregas.java
            ├── VentanaListaPedidos.java
            ├── VentanaListaRepartidores.java
            ├── VentanaPrincipal.java
            ├── VentanaRegistroCliente.java
            ├── VentanaRegistroPedido.java
            └── VentanaRegistroRepartidor.java
