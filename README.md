# Sistema de Envio de E-mails Assíncrono com RabbitMQ

Este projeto foi desenvolvido com o objetivo de estudar mensageria assíncrona utilizando **Spring Boot**, **RabbitMQ** e **Spring Mail**, simulando um fluxo utilizado em aplicações reais.

## Objetivo

Ao realizar o cadastro de um usuário, a aplicação não envia o e-mail diretamente. Em vez disso, ela publica uma mensagem em uma fila do RabbitMQ.

Um consumidor (`Consumer`) fica responsável por processar essa mensagem e realizar o envio do e-mail de boas-vindas através do SMTP do Gmail.

Dessa forma, o cadastro do usuário é concluído imediatamente, enquanto o envio do e-mail acontece em segundo plano, tornando a aplicação mais desacoplada e escalável.

## Fluxo da aplicação

```text
Cadastro de usuário
        │
        ▼
Salva no banco de dados
        │
        ▼
Publica mensagem no RabbitMQ
        │
        ▼
Exchange
        │
        ▼
Queue
        │
        ▼
EmailConsumer
        │
        ▼
JavaMailSender
        │
        ▼
SMTP Gmail
        │
        ▼
E-mail enviado
```

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring AMQP (RabbitMQ)
- Spring Mail
- Maven
- RabbitMQ
- SMTP Gmail

## Conceitos praticados

- Comunicação assíncrona
- Mensageria com RabbitMQ
- Producer e Consumer
- Exchanges e Queues
- Routing Keys
- Injeção de dependências
- Configuração de Beans
- Envio de e-mails com JavaMailSender
- Separação de responsabilidades
- Arquitetura orientada a eventos (Event-Driven)
