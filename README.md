Aldo Silvestre - aldosilvestre89@gmail.com
======

## Descripción

Este proyecto consta de el consumo de un api para el pronostico del tiempo, la misma da el pronostico actual.

## Decisiones de diseño

#### Dominio aplicación
Se limito el pronostico debido a que la cantidad de llamadas a api en version gratuita esta limitada a 26 por dia. Se realizan 2 llamadas a la misma, una para obtener la key de localizacion a partir de la latitud y longitud del dispositivo que consulta el pronostico, y la segunda para obtener el pronostico de 12 horas de ventana (es configurable la ventana<sup>1</sup>), lo almacena luego en un BD local en memoria a tipo de cache de datos y para limitar el numero de llamadas a la api externa<sup>2</sup>.

No se pretendio sobrediseñar la aplicación, es decir que no se forzaron a implementar patrones de diseño innecesarios, solo se utilizaron algunos adapter para mantener agnosticas las implementaciones con la BD y el servicio externo.

**Nota 1:** No se fue por la opcion de un servidor de ambiente. Se podria haber implementado el mismo, pero no se contemplaba en el alcance.

**Nota 2:** La localización no era factible grabarla en la BD debido se tomo con bastante precisión la ubicación, aparte de que la misma puede crecer demasiado. Puede ser factible aumentando el radio de pronostico por ubicación.

## Primeros pasos

#### Tecnologies
- Arcolinux
- Java con jdk 8
- Maven
- H2
- Springboot

#### Herramientas adicionales
- Podman o Docker en su defecto
- Just (Opcional)

## Levantar Servidor
- Mediante IDE
    * No se utilizaron interpretes o generadores como lombok, es decir que el proyecto no necesita una configuración especial con el ide salvo indicarle que la jdk que utilizara sera la 1.8.
    * Se debe clonar el proyecto y abrirlo como proyecto maven; el IDE puede hacerlo automaticamente (Eclipse, IntelliJ Community, Netbeans, VSCode).
- Mediante Docker
    * Se pueden utilizar las herramientas en la seccion de adicionales para levantar el contenedor, esta forma es la que mantendra su ambiente mas limpio ya que no require nada mas que tener git y podman o docker instalados. A continuación se detallan los comandos.

## Levantar Docker

1. Clonamos el proyecto. No hace falta que sea en una carpeta especifica, lo que si debe mantener su estructura.
2. Una vez dentro del proyecto a travez de la herramienta de docker que dispongamos sea el CLI o la aplicación de escritorio construir la imagen del docker.

        `podman build -t user/java:appWeather .` >> Ejecutara el podman

    ó

        `docker build -t user/java:appWeather .` >> Nativo de docker

***Aclaración:*** *Esto bajara la imagen de docker con alpine linux junto con las dependencias que necesita para correr el proyecto*

3. Ya terminado de construir el proyecto y ya ejecutado los test, procedemos a ponerlo en marcha junto con nuestra aplicación.

        `docker run --name appWeather -d -p 8080:8080 user/java:appWeather` >> Corre el contenedor con podman

    ó

        `docker run --name appWeather -d -p 8080:8080 user/java:appWeather` >> Corre el contenedor con docker

4. La aplicación ya estará corriendo en segundo plano, en el puerto 8080 de nuestro host local.

## Comandos utilies

Se generaron unos comando utiles con Just para poder manipular el contenedor como asi poder realizar algunas operaciones con el.

--------------------------------------------------------------------
| Comando            | Descripcion                                  |
|--------------------|----------------------------------------------|
| `just build`       |  Generá la imagen                            |
| `just run`         |  Corre la imagen generada en un contenedor   |
| `just kill`        |  Detiene el contenedor                       |
| `just logs`        |  Visualizan de forma tail los logs           |
| `just interactive` | Ingresa en modo interactivo con la terminal  |
---------------------------------------------------------------------

***Aclaración:*** *Just es solo un interprete de comandos, si necesita ver lo que ejecuta por atras, visualize el archivo **.justfile** ; Cabe resaltar que los comandos de Just ejecutan podman, no docker. En todo caso que quiera realizar el cambio sera solo modificar la palabra docker por podman*
