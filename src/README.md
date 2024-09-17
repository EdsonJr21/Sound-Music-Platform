# Sound Music Platform

Sound Music Platform é uma aplicação Java Spring Boot para gerenciar artistas e músicas. A aplicação permite cadastrar artistas, cadastrar músicas, listar músicas, buscar músicas por artistas e pesquisar dados sobre um artista utilizando a API da Wikipedia.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- JPA/Hibernate
- PostgreSQL

## Configuração do Ambiente

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- PostgreSQL

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL com o nome definido na variável de ambiente `${DB_NAME}`.
2. Configure as variáveis de ambiente `DB_HOST`, `DB_USER`, `DB_PASSWORD` e `DB_NAME` com as informações do seu banco de dados.

### Configuração da Aplicação

1. Clone o repositório:
    ```sh
    git clone https://github.com/EdsonJr21/Sound-Music-Platform.git
    cd Sound-Music-Platform
    ```

2. Configure o arquivo `application.properties` com as informações do seu banco de dados:
    ```ini
    spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    spring.datasource.driver-class-name=org.postgresql.Driver
    hibernate.dialect=org.hibernate.dialect.HSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Compile e execute a aplicação:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

## Funcionalidades

- **Cadastrar Artistas**: Permite cadastrar novos artistas.
- **Cadastrar Músicas**: Permite cadastrar novas músicas para artistas existentes.
- **Listar Músicas**: Lista todas as músicas cadastradas.
- **Buscar Músicas por Artistas**: Busca músicas de um artista específico.
- **Pesquisar Dados sobre um Artista**: Pesquisa informações sobre um artista utilizando a API da Wikipedia.

## Uso

Ao iniciar a aplicação, um menu será exibido no console com as seguintes opções:

1. Cadastrar artistas
2. Cadastrar músicas
3. Listar músicas
4. Buscar músicas por artistas
5. Pesquisar dados sobre um artista
9. Sair

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.
