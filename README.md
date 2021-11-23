#tqi-dio-kafka

Utilizar Java 11

###Inicializar projeto
- Entrar na pasta `/docker` dentro do projeto e subir a base local: `$ docker-compose docker-compose.yml up -d`

- Gerar artefato `/gradlew clean build`

- Executar aplicação `java -jar build/libs/tqi-dio-kafka-0.0.1.jar`

- Cadastrar evento `curl -X POST http://localhost:8099/employees/1/Klerisson/Paixao`