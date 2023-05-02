# eventos-spring-ciber
Servicios de eventos para el curso de Ciberseguridad 

## Instrucciones de despliegue en local

Importa la base de datos **SQL/gestion_eventos.sql** en local (MariaDB/MySQL), usando por ejemplo con un sistema XAMPP.

Abre el proyecto con VSCode (con las extensiones de Java y Spring Boot instaladas) por ejemplo. Edita el archivo **resources/application.yaml** cambiando el usuario y la contraseña de acceso a la base de datos (en XAMPP es root con contraseña vacía). Ejecuta el proyecto.

En la aplicación cliente de Angular (src/environments/environments.ts y environments.prod.ts) cambia la dirección del servidor para que sea **localhot:8080**. Ejecútala y accede a http://localhost:4200.
