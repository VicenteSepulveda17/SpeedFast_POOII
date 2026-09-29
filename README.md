# SpeedFast - Semana 7

## Descripción

Proyecto desarrollado en Java para la gestión de pedidos, repartidores y entregas del sistema SpeedFast.

En esta semana se implementó la conexión de la aplicación con una base de datos MySQL mediante JDBC, permitiendo almacenar y consultar información de forma persistente.

## Objetivo

Implementar la conexión entre una aplicación Java y una base de datos MySQL utilizando JDBC, permitiendo registrar, consultar y relacionar pedidos, repartidores y entregas.

## Tecnologías utilizadas

- Java
- Java Swing
- IntelliJ IDEA
- MySQL
- MySQL Workbench
- JDBC
- Maven
- Git y GitHub

## Funcionalidades

La aplicación permite:

- Registrar pedidos.
- Listar los pedidos almacenados en la base de datos.
- Registrar repartidores.
- Consultar repartidores desde la base de datos.
- Asignar un repartidor a un pedido.
- Registrar una entrega con fecha y hora.
- Mantener la información almacenada de forma persistente en MySQL.

## Estructura del proyecto

El proyecto está organizado en los siguientes paquetes:

### modelo

Contiene las clases que representan los datos del sistema:

- `Pedido`
- `Repartidor`
- `Entrega`

### dao

Contiene las clases encargadas de la comunicación con la base de datos mediante JDBC:

- `ConexionDB`
- `PedidoDAO`
- `RepartidorDAO`
- `EntregaDAO`

### controlador

Contiene las clases utilizadas para la gestión de los datos de la aplicación.

### vista

Contiene las ventanas y componentes de la interfaz gráfica:

- `VentanaPrincipal`
- `VentanaRegistroPedido`
- `VentanaRegistroRepartidor`
- `VentanaListaPedidos`
- `VentanaAsignarRepartidor`

### main

Contiene la clase `Main`, encargada de iniciar la aplicación.

## Base de datos

La aplicación utiliza una base de datos MySQL llamada:

`speedfast_db`

Las tablas utilizadas son:

- `pedido`
- `repartidor`
- `entrega`

La tabla `entrega` relaciona los pedidos con los repartidores y almacena la fecha y hora de cada entrega.

## Clases DAO

### PedidoDAO

Permite registrar pedidos y consultar los pedidos almacenados en MySQL.

### RepartidorDAO

Permite registrar repartidores y consultar los repartidores almacenados en MySQL.

### EntregaDAO

Permite registrar una entrega relacionando un pedido con un repartidor y almacenando la fecha y hora correspondiente.

## Conexión JDBC

La conexión con MySQL se encuentra implementada en la clase:

`dao.ConexionDB`

Las operaciones de acceso a datos utilizan `PreparedStatement`, `ResultSet` y manejo de recursos mediante `try-with-resources`.

## Ejecución

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que MySQL esté disponible.
3. Verificar que exista la base de datos `speedfast_db`.
4. Verificar que el conector MySQL JDBC se encuentre disponible en la carpeta `lib`.
5. Ejecutar la clase `main.Main`.
6. Se abrirá la ventana principal de SpeedFast.

## Interfaz principal

La aplicación cuenta con las siguientes opciones:

- Registrar pedido.
- Registrar repartidor.
- Listar pedidos.
- Asignar repartidor / Iniciar entrega.

## Autor

Proyecto desarrollado para la asignatura de programación - Duoc UC.
