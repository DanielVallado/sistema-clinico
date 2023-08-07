# Sistema clínico
Este proyecto emula el manejo de datos de una clínica para pacientes de rehabilitación física con la implementación de una arquitectura basada en microservicios, elaborado con Spring Boot.

## Control pacientes
Este microservicio es el principal, pues es donde gestionamos directamente los pacientes que forman parte de la clínica, aquí tenemos la disponibilidad de obtener, crear y eliminar registros de pacientes.
#### Entidades
- Paciente
- Antecedentes heredo familiares
- Antecedentes personales no patológicos
- Antecedentes personales patológicos
- Antecedentes gineco obstétricos
- Antecedentes perinatales
- Padecimiento actual

## Control historial clínico
Este microservicio es el que encargado de gestionar toda la información referente a los tratamientos, estudios, diagnósticos, etcétera que se le hagan a los pacientes.
#### Entidades
- Diagnóstico
- Exploración física
- Estudios
- Revaloración
- Pronóstico

## Control sistemas
En este microservicio encontramos la gestión de los sistemas y aparatos del cuerpo humano que se requieren para poder elaborar los historiales clínicos de manera precisa.
#### Entidades
- Sistema
- Aparato

## Control citas
Aquí es donde gestionamos las citas con los pacientes, las agendamos y confirmamos o cancelamos, según sea el caso.
#### Entidades
- Cita

## Control reportes
Este microservicio nos da un pequeño resumen de los registros que se guardan en los otros microservicios, para tener una vista generalizada de los datos.

## Email service
Este microservicio se encarga de hacer el envío de emails y se implementó en conjunto con la herramienta RabbitMQ.

## Otros microservicio implementados
- Config service
- Discover service (Eureka)
- Gateway

