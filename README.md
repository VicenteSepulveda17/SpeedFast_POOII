# SpeedFast - Semana 7

## Descripción

Aplicación de escritorio desarrollada en Java para la gestión de pedidos, repartidores y entregas de SpeedFast.

En esta etapa se incorporó la conexión de la aplicación con una base de datos MySQL mediante JDBC, permitiendo almacenar y consultar información de manera persistente.

## Objetivo

Implementar la conexión entre una aplicación Java y una base de datos MySQL utilizando JDBC, permitiendo registrar, consultar y relacionar pedidos, repartidores y entregas.

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- MySQL
- MySQL Workbench
- JDBC
- Swing
- Maven

## Funcionalidades

La aplicación permite:

- Registrar pedidos.
- Consultar los pedidos almacenados en la base de datos.
- Registrar repartidores.
- Consultar repartidores desde la base de datos.
- Asignar un repartidor a un pedido.
- Registrar una entrega con fecha y hora.
- Mantener la información almacenada de forma persistente en MySQL.

## Estructura del proyecto

El proyecto se encuentra organizado en los siguientes paquetes:

- `modelo`: contiene las clases `Pedido`, `Repartidor` y `Entrega`.
- `dao`: contiene las clases encargadas de la comunicación con la base de datos mediante JDBC.
- `controlador`: contiene las clases utilizadas para la gestión de los datos.
- `vista`: contiene las ventanas y componentes de la interfaz gráfica.
- `main`: contiene la clase principal que inicia la aplicación.

## Acceso a la base de datos

La aplicación utiliza una base de datos MySQL llamada:

`speedfast_db`

Tablas utilizadas:

- `pedido`
- `repartidor`
- `entrega`

La conexión se realiza mediante JDBC y utiliza `PreparedStatement` para ejecutar las operaciones sobre la base de datos.

## Clases DAO

### PedidoDAO

Permite:

- Guardar pedidos.
- Obtener todos los pedidos almacenados en MySQL.

### RepartidorDAO

Permite:

- Guardar repartidores.
- Obtener todos los repartidores almacenados en MySQL.

### EntregaDAO

Permite registrar una entrega relacionando:

- Un pedido.
- Un repartidor.
- La fecha de entrega.
- La hora de entrega.

## Ejecución

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que MySQL esté disponible.
3. Verificar que exista la base de datos `speedfast_db`.
4. Verificar que el conector MySQL JDBC se encuentre disponible en la carpeta `lib`.
5. Ejecutar la clase:

`main.Main`

6. Se abrirá la ventana principal de SpeedFast.

## Interfaz principal

La aplicación cuenta con las siguientes opciones:

- Registrar pedido.
- Registrar repartidor.
- Listar pedidos.
- Asignar repartidor / Iniciar entrega.

## Conexión JDBC

La conexión a la base de datos se encuentra implementada en:

`dao.ConexionDB`

Las operaciones de persistencia y consulta utilizan los DAO correspondientes y manejo de recursos mediante `try-with-resources`.

## Autor

Proyecto desarrollado para la asignatura de programación - Duoc UC.
