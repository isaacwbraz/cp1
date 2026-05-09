# Comidex - Delivery

## 👥 Integrantes do Grupo
- Isaac William Braz - 44185227
- Christian Gisto Silva - 42033071
- Miguel Augusto da Costa Souza - 43998704
- Ramon Leite - 43732216

## 📋 Tema Escolhido
[Delivery]

# 🎯 Objetivo do Sistema

O sistema de delivery desenvolvido em Java gerencia de forma prática e organizada os principais elementos de um serviço de entregas. Esta versão integra persistência completa em **PostgreSQL** e aplica o uso de **Interfaces** para padronizar comportamentos de segurança e cálculos financeiros.

O projeto utiliza conceitos de **Programação Orientada a Objetos (POO)**, como Herança, Polimorfismo e Encapsulamento, agora reforçados pelo padrão **DAO** e pelo uso de contratos via interfaces para garantir a integridade das regras de negócio em um ambiente modular.

## 📦 Funcionalidades Principais

1. **CRUD com Validação**: Cadastro, listagem, atualização e exclusão protegidos por contratos de autenticação.
2. **Persistência de Dados**: Integração total com PostgreSQL via JDBC.
3. **Gestão de Acesso**: Uso de interfaces para validar credenciais (CPF/CNPJ) antes de operações críticas.
4. **Cálculos Automatizados**: Sistema de descontos progressivos e taxas de entrega processados via interface financeira.
5. **Controle de Status**: Atribuição de entregadores com validação de disponibilidade em tempo real.
6. **Menu Interativo**: Console orquestrado para consumir as regras de negócio definidas nas interfaces.

## 🏗️ Estrutura de Classes e Interfaces

📂 **Pacote model**
- **Classe Base**: Usuario - Superclasse que centraliza dados comuns entre Clientes e Entregadores.
- **Classes de Domínio**: Restaurante, Produto, Cliente, Entregador, ItemPedido e Pedido.

📂 **Pacote util (Interfaces)**
- **Autenticavel**: Contrato que obriga a implementação de validação de acesso para Cliente, Restaurante e Entregador.
- **Calculavel**: Contrato que padroniza o cálculo de preços finais, descontos e taxas no Pedido e Produtos.

📂 **Pacote dao**
- **ConexaoBD**: Gerencia a conexão JDBC com o banco de dados PostgreSQL.
- **DAOs Específicos**: (ClienteDAO, ProdutoDAO, etc.) - Classes que isolam a lógica SQL, agora com suporte a buscas por ID para validação de segurança.

📂 **Raiz**
- **SistemaMain**: Classe principal responsável pelo menu e aplicação do polimorfismo através das interfaces.

## 🔄 Regra de Negócio Complexa

"Cálculo automatizado de descontos progressivos (5%, 10% e 15%) e taxas fixas via interface **Calculavel**, somado à validação de identidade via interface **Autenticavel** para proteção contra exclusões indevidas de contas e registros no banco de dados."