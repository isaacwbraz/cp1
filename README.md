# Comidex - Delivery

## 👥 Integrantes do Grupo
- Isaac William Braz - 44185227
- Christian Gisto Silva - 42033071
- Miguel Augusto da Costa Souza - 43998704
- Ramon Leite Pinto - 43732216

## 📋 Tema Escolhido
[Delivery]

# 🎯 Objetivo do Sistema

O sistema de delivery desenvolvido em Java tem como finalidade gerenciar de forma prática e organizada os principais elementos de um serviço de entregas. Ele permite cadastrar e listar restaurantes, produtos, clientes, entregadores e pedidos, criando um fluxo completo que simula o funcionamento de uma plataforma de delivery. Através do uso de Herança e Polimorfismo, o sistema otimiza o gerenciamento de diferentes perfis de usuários em uma estrutura unificada.

Na parte operacional, o sistema possibilita que restaurantes registrem seus dados e produtos, enquanto clientes podem ser cadastrados com informações pessoais e de contato. Os pedidos são formados a partir da associação entre cliente, restaurante e produto, e recebem a atribuição de um entregador disponível. A classe Pedido conta com Sobrecarga de Métodos, permitindo adicionar itens de diferentes maneiras, garantindo que o ciclo de delivery seja representado de ponta a ponta.

Além disso, o sistema foi estruturado para ser expansível e servir como base para integração com um banco de dados relacional. A aplicação utiliza um ArrayList Polimórfico para manipular Restaurantes, Clientes e Entregadores sob uma mesma base, aplicando conceitos avançados de programação orientada a objetos e modelagem de dados que refletem a lógica de um sistema real.

## 📦 Funcionalidades Principais

1. Cadastro e listagem de restaurantes (com Sobrescrita de métodos)
2. Cadastro e listagem de produtos
3. Cadastro e listagem de clientes
4. Cadastro e listagem de entregadores (com filtro de disponibilidade)
5. Cadastro e listagem de pedidos (com Sobrecarga de métodos)
6. Menu interativo com busca polimórfica

## 🏗️ Estrutura de Classes 

- **Classe Base:** Usuario - Superclasse que centraliza os dados comuns, permitindo a aplicação de Herança para evitar repetição de código entre as entidades do sistema.

- **Classe 1:** Restaurante - Responsável por armazenar informações de um restaurante, como código, nome, endereço, CNPJ, telefone e categoria culinária. Serve como entidade principal para vincular produtos e pedidos.

- **Classe 2:** Produto - Representa os itens oferecidos pelos restaurantes. Contém dados como código, nome, descrição, preço e categoria, além da associação ao restaurante que o fornece.

- **Classe 3:** Cliente - Estende a classe Usuario. Guarda os dados dos usuários que fazem pedidos, incluindo e-mail e endereço. É a entidade que inicia o processo de compra.

- **Classe 4:** Entregador - Estende a classe Usuario. Modela os entregadores do sistema, com atributos como veículo e status de disponibilidade.

- **Classe 5:** ItemPedido - Representa cada produto dentro de um pedido, incluindo quantidade e valor. É usado para detalhar os itens escolhidos pelo cliente em uma compra.

- **Classe 6:** Pedido - Centraliza o processo de compra, relacionando cliente, restaurante, produtos e entregador. Implementa a Sobrecarga de Métodos para facilitar a inserção de itens no carrinho.

- Classe 7:** SistemaMain - Classe principal que contém o menu interativo e utiliza um ArrayList polimórfico para gerenciar todas as instâncias de usuários. É responsável por controlar a execução do sistema.

## 🔄 Regra de Negócio Complexa

"Atribuição automática de entregador disponível através de busca polimórfica na lista de usuários + aplicação de taxa de entrega variável e descontos progressivos."