# transportorders
API REST para la gestión de órdenes de transporte de una empresa de  movilidad

# pasos para desplegar el componente

Dirigirse al código fuente del repositorio GitHub en la siguiente ruta:

	https://github.com/eistenroman/transportorders/
	
Clonar el repositorio mediante el comando

	git clone https://github.com/eistenroman/transportorders.git
	
Entrar a la carpeta

	transportorders
	
Cambiar a la rama develop

	git checkout develop
	
Compilar el código fuente de la siguiente forma

	mvn clean package
	
Ejecurar el despliegue en docker mediante el siguiente comando

	docker-compose up --build

La documenación del aplicativo se encuentra en

	http://localhost:8080/swagger-ui/index.html
	
En la carpeta transportorders se encuentra un postman_collection para realizar las pruebas mediante postman

	TransportOrders.postman_collection.json
	
Para hacer las pruebas es necesario registrar primero un usuario en el postman para obtener el token jwt

	Register User 
	
Si el token jwt llega a expirar, obtener nuevamente mediante el enpoint de postman

	Login User
	