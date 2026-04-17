# Comidex - Delivery

## 👥 Integrantes do Grupo
- Isaac William Braz - 44185227
- Christian Gisto Silva - 42033071
- Miguel Augusto da Costa Souza - 43998704
- Ramon Leite Pinto - 43732216

## 📋 Tema Escolhido
[Delivery]

# 🎯 Objetivo do Sistema

O sistema de delivery desenvolvido em Java tem como finalidade gerenciar de forma prática e organizada os principais elementos de um serviço de entregas. Ele permite cadastrar e listar restaurantes, produtos, clientes, entregadores e pedidos, criando um fluxo completo que simula o funcionamento de uma plataforma de delivery. Dessa forma, o usuário consegue visualizar e manipular todas as informações necessárias para o processo de compra e entrega.

Na parte operacional, o sistema possibilita que restaurantes registrem seus dados e produtos, enquanto clientes podem ser cadastrados com informações pessoais e de contato. Os pedidos são formados a partir da associação entre cliente, restaurante e produto, e recebem a atribuição de um entregador disponível. Esse ciclo garante que o processo de delivery seja representado de ponta a ponta, desde a escolha do prato até a entrega ao consumidor.

Além disso, o sistema foi estruturado para ser expansível e servir como base para integração com um banco de dados relacional. Isso permite que as informações sejam persistidas e consultadas de forma eficiente, refletindo a lógica de um sistema real de delivery. A aplicação, portanto, não apenas simula o funcionamento de um app comercial, mas também oferece uma oportunidade de aplicar conceitos de programação orientada a objetos e modelagem de dados.

## 📦 Funcionalidades Principais

1. Cadastro e listagem de restaurantes
2. Cadastro e listagem de produtos
3. Cadastro e listagem de clientes
4. Cadastro e listagem de entregadores
5. Cadastro e listagem de pedidos
6. Menu interativo

## 🏗️ Estrutura de Classes 

- **Classe 1:** Restaurante - Responsável por armazenar informações de um restaurante, como código, nome, endereço, CNPJ, telefone e categoria culinária. Serve como entidade principal para vincular produtos e pedidos.

- **Classe 2:** Produto - Representa os itens oferecidos pelos restaurantes. Contém dados como código, nome, descrição, preço e categoria, além da associação ao restaurante que o fornece.

- **Classe 3:** Cliente - Guarda os dados dos usuários que fazem pedidos, incluindo código, nome, CPF, telefone, e-mail e endereço. É a entidade que inicia o processo de compra.

- **Classe 4:** Entregador - Modela os entregadores do sistema, com atributos como código, nome, CPF, telefone, veículo e status de disponibilidade. É responsável por realizar a entrega dos pedidos.

- **Classe 5:** ItemPedido - Representa cada produto dentro de um pedido, incluindo quantidade e valor. É usado para detalhar os itens escolhidos pelo cliente em uma compra.

- **Classe 6:** Pedido - Centraliza o processo de compra, relacionando cliente, restaurante, produtos e entregador. Permite adicionar itens e atribuir entregadores disponíveis, simulando o fluxo completo de um delivery.

- Classe 7:** SistemaMain - Classe principal que contém o menu interativo e os métodos para cadastrar e listar todas as entidades (restaurantes, produtos, clientes, entregadores e pedidos). É responsável por controlar a execução do sistema.

## 🔄 Regra de Negócio Complexa

"Atribuição automática de entregador disponível ao pedido + aplicação de taxa de entrega variável"