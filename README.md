# movrent
Movie Rental App
Aplicação Spring Boot

Informações baseadas no servidor local e porta default do Spring Boot(localhost:8080), então caso utilize outro, levar isso em conta.  

1 - Executar o script sql da pasta resources para criar o banco de dados, tabelas e usuários

2 - Executar a aplicação spring boot pela IDE, Maven(comando mvn spring-boot:run), Gradle, etc.

3 - Visualizar se o serviços estão rodando pelo Actuator http://localhost:8080/actuator

4 - Visualizar a documentação dos serviços pelo Swagger http://localhost:8080/swagger-ui.html (é possível também testar pela ferramenta)

5 - Ainda não foram criados teste unitários e nem de integração

Basicamente, contém três serviços:

1 - Auth - Para registro de clientes e login/logout

2 - Movie - Para cadastro dos filmes

3 - Rent - para cadastro das locações

O campo username no cadastro de clientes deve ser obrigatoriamente um e-mail.
