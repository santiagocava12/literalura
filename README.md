📚 Proyecto Literalura
Literalura es un proyecto diseñado para buscar información de libros en la API de Gutendex, guardar datos en una base de datos local y realizar consultas básicas. Este sistema ofrece un flujo interactivo para gestionar libros y sus autores.

📋 Funcionalidades
Buscar libros por título: Consulta información sobre libros desde la API de Gutendex y guárdalos en la base de datos.
Guardar libros: Al buscar libros, la aplicación almacena los datos en una base de datos local si no existen.
Gestión de autores: Los libros pueden estar asociados a autores registrados en la base de datos.
🛠️ Tecnologías utilizadas
Java 17
Spring Boot 3.4.1
Spring Data JPA: Para la persistencia de datos.
PostgreSQL: Base de datos relacional.
Gutendex API: Fuente de datos externos sobre libros.
HTTP Client 5: Para realizar solicitudes HTTP.
Jackson: Procesamiento de datos JSON.
🚀 Instalación y configuración
Requisitos previos
Tener instalado:

Java 17
Maven
PostgreSQL
Configurar una base de datos PostgreSQL:

Crear una base de datos llamada literalura.
Configurar el usuario y contraseña en el archivo application.properties.
