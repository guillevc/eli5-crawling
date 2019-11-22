## Manual de usuario

### Instalación y desarrollo

Requisitos:


* Elasticsearch 6.8.4
* Java 1.8 y Maven
* Node 12.3.0 y NPM para ejecutar webpack-dev-server

Para compilar el proyecto, desde la raíz se ejecuta el comando:
```
    $ mvn clean install -DskipTests
```

Antes de lanzar la app, se iniciará Elasticsearch mediante el comando:
    
```
    $ elasticsearch
```
Para ejecutar la aplicación se utilizará el comando:
```
    $ mvn --projects backend spring-boot:run
```

Además, se puede lanzar el servidor de desarrollo de Vue
```
    cd frontend
    npm run serve
```

Una vez lanzada la aplicación Spring, podremos acceder a ella desde el puerto \texttt{8088}
```
    http://localhost:8088/
```

El servidor de desarrollo de Vue es accedido desde el puerto \texttt{8080}
```
    http://localhost:8080/
```

### Uso

El crawling no es realizado automáticamente, existe un endpoint en la aplicación que inicia una secuencia de crawling, populando las respuestas sobre las que se harán las búsquedas.

Para iniciar el crawling, basta con visitar la URL
```
    http://localhost:8088/api/index
```

Una vez realizado, se puede comenzar a realizar búsquedas a través de la aplicación web.


