# Mimi autorizador

Realiza a validação de regras para autorização de transação e atualiza o saldo do cartão.

## Funcionalidades da API

- Cadastro de cartão
- Consulta de saldo
- Autorização e retirada de saldo do cartão


> Na criação de cartões o saldo tem valor padrão de 500.00.
> A autorização de transações realizadas usando os cartões previamente criados como meio de pagamento são autorizadas e retiradas o valor do saldo caso o cartão existir, a senha do cartão for a correta e o cartão possuir saldo disponível.


## Tecnologias

O Projeto é um microserviço REST API e está desenvolvido no padrão MVC utilizando as seguintes tecnologias:

- [Java] - versão 17
- [Maven] - Gerenciador de dependências do java
- [Spring Boot] - Framework Java para auxiliar no desenvolvimento da aplicação
- [Mysql] - Banco de dados relacional
- [Swagger] - Documentação das API's

> Para garantir que 2 transações disparadas ao mesmo tempo não causem problemas relacionados à concorrência em instâncias diferentes da aplicação é verificado se possui alguma transação em execução para o cartão(Transação iniciada mas não fechada) antes de validar dados e realizar transação, caso não tenha nenhuma transação pendente o sistema inicia uma transação, tenta executar a transação e registra o fim desta transação mesmo se tiver erro.
> No caso da concorrencia a segunda instancia irá retorna erro de concorrencia pois já possui transação em andamento

## Subindo aplicação

Para instalar as bibliotecas utilizadas é necessário ter instalado o Maven 3.

```sh
cd mini-autorizador
mvn clean package
mvn spring-boot:run
```

> A estrutura da base de dados está configurada para ser criada todas as vezes que a aplicação for executada.
## Plugins

Mini autorizador está atualmente estendido com os seguintes plugins.

| Plugin | README |
| ------ | ------ |
| Lombok | https://projectlombok.org/ |



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
