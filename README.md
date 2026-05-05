# Comidex - Delivery

## 👥 Integrantes do Grupo
- Isaac William Braz - 44185227
- Christian Gisto Silva - 42033071
- Miguel Augusto da Costa Souza - 43998704
- Ramon Leite Pinto - 43732216

## 📋 Tema Escolhido
[Delivery]

# 🎯 Objetivo do Sistema

O sistema de delivery desenvolvido em Java gerencia de forma prática e organizada os principais elementos de um serviço de entregas. Originalmente focado em lógica de memória, a versão atual integra persistência completa em um banco de dados relacional PostgreSQL, permitindo que todas as informações de restaurantes, produtos, clientes, entregadores e pedidos sejam mantidas de forma permanente.

O projeto aplica conceitos avançados de Programação Orientada a Objetos (POO), como Herança, Polimorfismo e Encapsulamento, agora reforçados pelo padrão de arquitetura DAO (Data Access Object). A estrutura foi reorganizada em pacotes profissionais para atender aos critérios de modularidade e clareza exigidos em ambientes de desenvolvimento real.
## 📦 Funcionalidades Principais

1. CRUD Completo: Cadastro, listagem, atualização e exclusão para todas as entidades (Restaurantes, Produtos, Clientes e Entregadores).
2. Persistência de Dados: Integração total com PostgreSQL via JDBC.
3. Gerenciamento de Pedidos: Cadastro e listagem de pedidos com controle de status e atribuição de entregadores.
4. Integridade Referencial: Tratamento de restrições de chaves estrangeiras para impedir exclusões acidentais de dados vinculados.
5. Organização em Pacotes: Separação clara entre modelos (domínio), persistência (DAO) e ponto de entrada da aplicação.
6. Menu Interativo: Console otimizado com tratamento de limpeza de buffer para entrada de dados.

## 🏗️ Estrutura de Classes 

📂 Pacote model
- **Classe Base**: Usuario - Superclasse que centraliza dados comuns entre Clientes e Entregadores.

- **Classe 1**: Restaurante - Entidade com atributos de identificação e categoria culinária.

- **Classe 2**: Produto - Itens vinculados obrigatoriamente a um restaurante.

- **Classe 3**: Cliente - Extensão de Usuario para gestão de consumidores.

- **Classe 4**: Entregador - Extensão de Usuario com controle de veículo e status.

- **Classe 5**: ItemPedido - Detalhamento de produtos dentro de uma transação.

- **Classe 6**: Pedido - Entidade central que relaciona todas as partes do sistema.

📂 Pacote dao
- **ConexaoBD**: Gerencia a conexão JDBC com o banco de dados PostgreSQL.

- **DAOs Específicos**: (ClienteDAO, ProdutoDAO, etc.) - Classes que contêm toda a lógica de SQL para inserção, busca, edição e deleção no banco de dados.

📂 Raiz
- **SistemaMain**: Classe principal responsável pelo menu e orquestração do fluxo de execução do sistema.

## 🔄 Regra de Negócio Complexa

"Atribuição automática de entregador disponível através de busca polimórfica com persistência em banco de dados + controle de integridade referencial para proteção de dados vinculados entre Restaurantes e Produtos."