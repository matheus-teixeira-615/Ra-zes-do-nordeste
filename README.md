# Raízes do Nordeste - Backend API

API RESTful desenvolvida em **Spring Boot** para gerenciar o sistema de pedidos, controle de estoque, cadastro de usuários e o programa de resgate de pontos da aplicação **Raízes do Nordeste**.

---

## Requisitos

* **Java Development:** Versão 17 ou superior
* **Gerenciador de Dependências:** Maven
* **Banco de Dados Relacional:** MySQL (Versão 8.0)
* **Ferramenta de Testes:** Postman

---

## Configuração do Banco de Dados (MySQL Local)

Como o projeto se conecta a um banco MySQL local, siga os passos abaixo para configurar o ambiente:

1. Certifique-se de que o seu serviço do MySQL está rodando na sua máquina.
2. Abra o seu gerenciador de banco de dados e crie o banco de dados com o comando:
   sql:
   CREATE DATABASE raizes_nordeste;

   O projeto já está configurado por padrão para se conectar ao MySQL local através do arquivo src/main/resources/application.properties.
   Se o seu usuário ou senha do MySQL forem diferentes de root(usuario) 123(senha), ajuste as propriedades abaixo no arquivo:
   
   spring.datasource.url=jdbc:mysql://localhost:3306/raizes_nordeste?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=123

   Tudo isso esta em um arquivo chamado "application.properties"

---

Como rodar a API:

- Ligar o Banco de dados com as instruções acima
- Criar o banco de dados
- Abrir o CMD na pasta do projeto \dowload\raizes do nordeste backEnd"
- Executar a aplicação no Windows .\mvnw spring-boot:run
- ou Linux ./mvnw spring-boot:run

Link do postman com os nomes dos testes dos endPoints: 
https://matheus-teixeiradiogenes-1001898.postman.co/workspace/Matheus-Teixeira's-Workspace~33cb7ee7-953e-4c3b-8dc9-470b2b9a745d/collection/47617050-403f0dbe-24a5-4e89-9241-1f1c2c617918?action=share&creator=47617050

---

## Documentação da API (Swagger)
O projeto conta com documentação interativa gerada automaticamente via Springdoc OpenAPI. 

Após iniciar a aplicação, acesse no navegador:
* **URL do Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
