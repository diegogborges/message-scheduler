<h1 align="center">Message Scheduler - Luizalabs</h1>
<p align="center">API for message schedulling</p>

Aplicação para criar agendamentos de comunicação por tipos de mensagens, podendo ser SMS, e-mail, push e SMS.

<div align="center">

[![CircleCI](https://circleci.com/gh/diegogborges/message-scheduler/tree/main.svg?style=svg)](https://circleci.com/gh/diegogborges/message-scheduler/tree/main) [![Coverage Status](https://coveralls.io/repos/github/diegogborges/message-scheduler/badge.svg?branch=main)](https://coveralls.io/github/diegogborges/message-scheduler?branch=main)

</div>

## Pré-requisitos

* Java 11
* Docker

## Instalação

Antes de inicializar o projeto, são necessários os seguintes recursos:

* __MySQL__ - Banco de dados utilizado para armazenar as informações dos agendamentos de comunicação;

Caso estes recursos não existam, eles podem ser adquiridos e instalados na máquina local através do `Docker`:

```sh
docker-compose up -d
```

Os bancos de dados serão criados, porém, eles só serão populados pelo `Flyway` após a primeira execução.

Por fim, com o `Java 11` instalado, basta baixar as dependências do projeto e realizar a compilação:

```sh
./mvnw clean install -DskipTests
```

## Inicialização

Para inicializar a API é só executar o seguinte comando:

```sh
./mvnw spring-boot:run
```

## Testes

Os testes foram implementados de forma à validar os fluxos como um todo.

Para rodar os testes basta executar o seguinte comando:

```sh
./mvnw test
```

O resultado da cobertura dos testes se encontra [neste arquivo](./target/jacoco/index.html) (gerado através do plugin `Jacoco`).

Este projeto possui o CI integrado com o `CircleCI/Coveralls`.

## Documentação

Esta API possui os seguintes endpoints:

* __/v1/message/scheduler/__ - Obter, cadastrar e remover clientes;
* __/actuator/health__ - Obter o status da aplicação (gerado automaticamente pelo `Actuator`);

O `Swagger` é uma ferramenta responsável por gerar documentações de APIs de maneira automática:

* http://localhost:8080/swagger-ui/

## Autor

Aplicação desenvolvida por Diego Gomes Borges.
