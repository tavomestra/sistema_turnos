# Generacion de turnos

## Herramientas usadas


<table>
  <tr>
    <td><b>Herramienta</b></td>
    <td><b>Versión</b></td>
  </tr>
  <tr>
    <td colspan="2"><b>Backend (turnos)</b></td>
  </tr>
  <tr>
    <td>Maven</td>
    <td>3.6.0</td>
  </tr>
  <tr>
    <td>Java</td>
    <td>11</td>
  </tr>
  <tr>
    <td>Spring boot</td>
    <td>2.4.1</td>
  </tr>
 <tr>
    <td colspan="2"><b>Frontend (clienteturnos)</b></td>
  </tr>
  <tr>
    <td>Angular</td>
    <td>9</td>
  </tr>
  
</table>

## Ejecución
- Crear la base de datos y ejecutar el script ubicado en la ruta script_database/database.sql.

- Configurar el archivo properties ubicado en turnos\src\main\resources\application.properties con los datos para la conexión a la base de datos.

- Abrir el proyecto turnos y compilarlo con el comando <code>mvn clean install</code> y luego ejecutar <code>mvn spring-boot:run</code> para levantar los servicios del api rest.

- Abrir el proyecto clienteturnos y ejecutar el comando <code>npm install</code> para instalar los modulos que requiere el proyecto, al finalizar ejecurar el comando <code>ng serve -o</code> y abrira la pagina de login, el usuario es admin y la contraseña es 123456.

Para ver la documentación de los servicios ingrese en `http://localhost:8082/api/v1/swagger-ui/#`



