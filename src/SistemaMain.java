package src;

import java.util.Scanner;

import src.dao.*;
import src.model.*;

import java.util.ArrayList;
import java.util.List;

public class SistemaMain {
    private static Scanner scanner = new Scanner(System.in);

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static RestauranteDAO restauranteDAO = new RestauranteDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static PedidoDAO pedidoDAO = new PedidoDAO();
    private static EntregadorDAO entregadorDAO = new EntregadorDAO();

    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
            // Restaurante
                case 1: cadastrarRestaurante(); break;
                case 2: listarRestaurantes(); break;
                case 15: atualizarRestaurante(); break;
                case 16: excluirRestaurante(); break;

                // Produto
                case 3: cadastrarProduto(); break;
                case 4: listarProdutos(); break;
                case 17: atualizarProduto(); break;
                case 18: excluirProduto(); break;

                // Cliente
                case 5: cadastrarCliente(); break;
                case 6: listarClientes(); break;
                case 11: atualizarCliente(); break;
                case 12: excluirCliente(); break;

                // Entregador
                case 7: cadastrarEntregador(); break;
                case 8: listarEntregadores(); break;
                case 13: atualizarEntregador(); break;
                case 14: excluirEntregador(); break;

                // Pedido
                case 9: cadastrarPedido(); break;
                case 10: listarPedidos(); break;
                case 19: atualizarStatusPedido(); break;
                case 20: excluirPedido(); break;

                case 0: System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opção inválida!"); break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
    System.out.println("\n===============================================");
    System.out.println("          SISTEMA DE DELIVERY COMIDEX          ");
    System.out.println("===============================================");
    System.out.println(" [RESTAURANTES]          [PRODUTOS]");
    System.out.println(" 1. Cadastrar            3. Cadastrar");
    System.out.println(" 2. Listar               4. Listar");
    System.out.println(" 15. Atualizar           17. Atualizar");
    System.out.println(" 16. Excluir             18. Excluir");
    System.out.println("-----------------------------------------------");
    System.out.println(" [CLIENTES]              [ENTREGADORES]");
    System.out.println(" 5. Cadastrar            7. Cadastrar");
    System.out.println(" 6. Listar               8. Listar");
    System.out.println(" 11. Atualizar           13. Atualizar");
    System.out.println(" 12. Excluir             14. Excluir");
    System.out.println("-----------------------------------------------");
    System.out.println(" [PEDIDOS]");
    System.out.println(" 9. Fazer Pedido         10. Listar Pedidos");
    System.out.println(" 19. Mudar Status        20. Cancelar/Excluir");
    System.out.println("-----------------------------------------------");
    System.out.println(" 0. Sair");
    System.out.println("===============================================");
    System.out.print("Escolha uma opção: ");
    }

    // ===== RESTAURANTE =====
    private static void cadastrarRestaurante() {
        System.out.println("\n--- CADASTRAR RESTAURANTE ---");
        System.out.print("Código: ");
        int codigo = scanner.nextInt(); scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Categoria culinária: ");
        String categoria = scanner.nextLine();

        Restaurante r = new Restaurante(codigo, nome, endereco, cnpj, telefone, categoria);
        restauranteDAO.inserir(r);
        System.out.println("Restaurante cadastrado com sucesso!");
    }

    private static void listarRestaurantes() {
        System.out.println("\n--- LISTA DE RESTAURANTES (DO BANCO)---");
        List<Restaurante> lista = restauranteDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado.");
        } else {
            for (Restaurante r : lista) {
                System.out.println(r.exibirDados());
            }
        }
    }

    private static void atualizarRestaurante() {
    System.out.println("\n--- ATUALIZAR RESTAURANTE ---");
    System.out.print("Digite o código do restaurante que deseja editar: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine();

    System.out.print("Novo Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Novo Endereço: ");
    String endereco = scanner.nextLine();
    System.out.print("Novo CNPJ: ");
    String cnpj = scanner.nextLine();
    System.out.print("Novo Telefone: ");
    String telefone = scanner.nextLine();
    System.out.print("Nova Categoria Culinária: ");
    String categoria = scanner.nextLine();

    Restaurante r = new Restaurante(codigo, nome, endereco, cnpj, telefone, categoria);

    if (restauranteDAO.atualizar(r)) {
        System.out.println("✅ Restaurante atualizado no banco com sucesso!");
    } else {
        System.out.println("❌ Erro ao atualizar. Verifique se o código existe.");
    }
}

    private static void excluirRestaurante() {
    System.out.print("\nDigite o código do restaurante para excluir: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine();

    Restaurante r = restauranteDAO.buscarPorId(codigo); 

    if (r != null) {
        System.out.print("Digite o CNPJ do restaurante para confirmar a exclusão: ");
        String cnpjConfirma = scanner.nextLine();

        if (r.validarAcesso(cnpjConfirma)) {
            if (restauranteDAO.excluir(codigo)) {
                System.out.println("✅ Restaurante removido com sucesso!");
            }
        } else {
            System.out.println("❌ Erro: CNPJ não confere. Acesso Negado!");
        }
    }
}

    // ===== PRODUTO =====
    private static void cadastrarProduto() {
    System.out.println("\n--- CADASTRAR PRODUTO ---");
    
    RestauranteDAO restauranteDAO = new RestauranteDAO();
    List<Restaurante> restaurantesNoBanco = restauranteDAO.listarTodos();

    if (restaurantesNoBanco.isEmpty()) {
        System.out.println("Nenhum restaurante encontrado no banco. Cadastre um restaurante primeiro!");
        return;
    }

    System.out.print("Código: ");
    int codigo = scanner.nextInt(); scanner.nextLine();
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Descrição: ");
    String descricao = scanner.nextLine();
    System.out.print("Preço: ");
    double preco = scanner.nextDouble(); scanner.nextLine();
    System.out.print("Categoria: ");
    String categoria = scanner.nextLine();

    System.out.println("Selecione o restaurante (pelo índice):");
    for (int i = 0; i < restaurantesNoBanco.size(); i++) {
        System.out.println(i + " - " + restaurantesNoBanco.get(i).getNome());
    }
    int escolha = scanner.nextInt(); scanner.nextLine();
    Restaurante restaurante = restaurantesNoBanco.get(escolha);

    Produto p = new Produto(codigo, nome, descricao, preco, categoria, restaurante);
    
    if (produtoDAO.salvar(p)) { 
    System.out.println("Produto '" + p.getNome() + "' cadastrado com sucesso no banco!");
    } else {
        System.out.println("Falha ao salvar produto no PostgreSQL.");
    }
}

    private static void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS (NO BANCO) ---");
        List<Produto> lista = produtoDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : lista) {
                System.out.println(p);
            }
        }
    }

    private static void atualizarProduto() {
    System.out.println("\n--- ATUALIZAR PRODUTO ---");
    System.out.print("Digite o código do produto que deseja editar: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine();

    System.out.print("Novo Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Nova Descrição: ");
    String descricao = scanner.nextLine();
    System.out.print("Novo Preço: ");
    double preco = scanner.nextDouble(); 
    scanner.nextLine();
    System.out.print("Nova Categoria: ");
    String categoria = scanner.nextLine();

    List<Restaurante> restaurantes = restauranteDAO.listarTodos();
    System.out.println("Selecione o restaurante dono deste produto:");
    for (int i = 0; i < restaurantes.size(); i++) {
        System.out.println(i + " - " + restaurantes.get(i).getNome());
    }
    int escolha = scanner.nextInt(); scanner.nextLine();
    Restaurante rest = restaurantes.get(escolha);

    Produto p = new Produto(codigo, nome, descricao, preco, categoria, rest);

    if (produtoDAO.atualizar(p)) {
        System.out.println("✅ Produto atualizado com sucesso!");
    } else {
        System.out.println("❌ Erro ao atualizar produto.");
    }
  }

    private static void excluirProduto() {
    System.out.print("\nDigite o código do produto para excluir: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine(); 

    System.out.print("⚠ Tem certeza que deseja remover este produto? (S/N): ");
    if (scanner.nextLine().equalsIgnoreCase("S")) {
        if (produtoDAO.excluir(codigo)) {
            System.out.println("✅ Produto removido do banco!");
        } else {
            System.out.println("❌ Erro ao excluir. Ele pode estar em um pedido ativo.");
        }
     }
    }

    // ===== CLIENTE =====
    private static void cadastrarCliente() {
        System.out.println("\n--- CADASTRAR CLIENTE ---");
        System.out.print("Código: ");
        int codigo = scanner.nextInt(); scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Cliente c = new Cliente(codigo, nome, cpf, telefone, email, endereco);
        if (clienteDAO.inserir(c)) {
    System.out.println("Cliente salvo no banco com sucesso!");
    } else {
        System.out.println("Erro ao salvar cliente no banco.");
    }
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES (NO BANCO)---");
        List<Cliente> lista = clienteDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : lista) {
                System.out.println(c.exibirDados());
            }
        }
    }

    private static void atualizarCliente() {
    System.out.println("\n--- ATUALIZAR CLIENTE ---");
    System.out.print("Código do cliente que deseja editar: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine(); 

    System.out.print("Novo Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Novo CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("Novo Telefone: ");
    String telefone = scanner.nextLine();
    System.out.print("Novo Email: ");
    String email = scanner.nextLine();
    System.out.print("Novo Endereço: ");
    String endereco = scanner.nextLine();

    Cliente c = new Cliente(codigo, nome, cpf, telefone, email, endereco);

    if (clienteDAO.atualizar(c)) {
        System.out.println("✅ Cliente atualizado no banco com sucesso!");
    } else {
        System.out.println("❌ Erro ao atualizar cliente. Verifique o código.");
    }
}

   private static void excluirCliente() {
    System.out.print("\nCódigo do cliente para excluir: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine();

    Cliente c = clienteDAO.buscarPorId(codigo);

    if (c != null) {
        System.out.print("⚠ Para confirmar a exclusão da sua conta, digite seu CPF: ");
        String cpfConfirma = scanner.nextLine();

        if (c.validarAcesso(cpfConfirma)) { 
            boolean sucesso = clienteDAO.excluir(codigo);

            if (sucesso) {
                System.out.println("✅ Sua conta foi removida com sucesso.");
            } else {
                System.out.println("❌ Não foi possível excluir: existem pedidos vinculados a esta conta.");
            }
        } else {
            System.out.println("❌ Acesso negado! Você só pode deletar sua própria conta.");
        }
    } else {
        System.out.println("❌ Cliente não encontrado!");
    }
  }
    

    // ===== ENTREGADOR =====
    private static void cadastrarEntregador() {
    System.out.println("\n--- CADASTRAR ENTREGADOR ---");
    System.out.print("Código: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine();
    
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Telefone: ");
    String telefone = scanner.nextLine();
    System.out.print("CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("Veículo: ");
    String veiculo = scanner.nextLine();
    System.out.print("Status (Disponível/Ocupado): ");
    String status = scanner.nextLine();
  
    Entregador e = new Entregador(codigo, nome, cpf, telefone, veiculo, status);
    EntregadorDAO entregadorDAO = new EntregadorDAO();
    
    if (entregadorDAO.salvar(e)) {
    System.out.println("Entregador cadastrado e salvo no banco de dados com sucesso!");
    } else {
        System.out.println("ERRO: O entregador NÃO foi salvo. Verifique se o status está correto conforme as regras do banco.");
    }
}

    private static void listarEntregadores() {
        System.out.println("\n--- LISTA DE ENTREGADORES ---");
        EntregadorDAO entregadorDAO = new EntregadorDAO();
        List<Entregador> lista = entregadorDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum entregador cadastrado.");
        } else {
            for (Entregador e : lista) {
                System.out.println(e.exibirDados());
            }
        }
    }

    private static void atualizarEntregador() {
    System.out.println("\n--- ATUALIZAR ENTREGADOR ---");
    System.out.print("Digite o código do entregador que deseja editar: ");
    int codigo = scanner.nextInt(); scanner.nextLine();


    System.out.print("Novo Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Novo Telefone: ");
    String telefone = scanner.nextLine();
    System.out.print("Novo CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("Novo Veículo: ");
    String veiculo = scanner.nextLine();
    System.out.print("Novo Status (Disponível/Ocupado): ");
    String status = scanner.nextLine();

    Entregador e = new Entregador(codigo, nome, cpf, telefone, veiculo, status);

    if (entregadorDAO.atualizar(e)) {
        System.out.println("✅ Entregador atualizado no banco!");
    } else {
        System.out.println("❌ Erro ao atualizar entregador.");
    }
}

private static void excluirEntregador() {
    System.out.print("\nCódigo do entregador para excluir: ");
    int codigo = scanner.nextInt(); 
    scanner.nextLine();

    Entregador e = entregadorDAO.buscarPorId(codigo);

    if (e != null) {
        System.out.print("⚠ Confirme o CPF do entregador para autorizar a remoção: ");
        String cpfConfirma = scanner.nextLine();

        if (e.validarAcesso(cpfConfirma)) { //
            if (entregadorDAO.excluir(codigo)) {
                System.out.println("✅ Entregador removido.");
            }
        } else {
            System.out.println("❌ Erro: Credencial inválida. Ação bloqueada.");
        }
    }
}

    // ===== PEDIDO =====
   private static void cadastrarPedido() {
    System.out.println("\n--- CADASTRAR PEDIDO ---");

    RestauranteDAO restDAO = new RestauranteDAO();
    ClienteDAO cliDAO = new ClienteDAO();
    EntregadorDAO entDAO = new EntregadorDAO();
    ProdutoDAO prodDAO = new ProdutoDAO();

    List<Restaurante> restaurantes = restDAO.listarTodos();
    List<Cliente> clientes = cliDAO.listarTodos();
    List<Produto> produtosBanco = prodDAO.listarTodos();
    List<Entregador> entregadores = entDAO.listarTodos();

    if (restaurantes.isEmpty() || clientes.isEmpty() || produtosBanco.isEmpty()) {
        System.out.println("Erro: Verifique se há restaurantes, clientes e produtos no BANCO DE DADOS!");
        return;
    }

    Restaurante rest = restaurantes.get(0);
    Cliente cli = clientes.get(0);
    
    Entregador ent = null;
    for (Entregador e : entregadores) {
        if (e.getStatus().toLowerCase().contains("dispon")) {
            ent = e;
            break;
        }
    }

    Pedido pedido = new Pedido(rest, cli);

    Produto produto = produtosBanco.get(0);
    System.out.print("Quantidade do produto " + produto.getNome() + ": ");
    int qtd = scanner.nextInt();
    scanner.nextLine();

    pedido.adicionarItem(produto, qtd);

    if (ent != null) {
        if (pedido.atribuirEntregador(ent)) {
        System.out.println("✅ Entregador " + ent.getNome() + " atribuído com sucesso!");
        } else {
        System.out.println("⚠ O entregador selecionado não estava disponível.");
        }
    }

    if (pedidoDAO.inserir(pedido)) {
        System.out.println("Pedido salvo com sucesso no banco!");
    }
}

private static void listarPedidos() {
    System.out.println("\n--- LISTA DE PEDIDOS (RESUMO DETALHADO) ---");
    List<Pedido> listaDoBanco = pedidoDAO.listarTodos();

    if (listaDoBanco.isEmpty()) {
        System.out.println("Nenhum pedido encontrado.");
    } else {
        for (Pedido p : listaDoBanco) {
            double totalComRegras = p.calcularPrecoFinal(); 

            System.out.println("Pedido ID: " + p.getId());
            System.out.println("Cliente: " + p.getCliente().getNome());
            System.out.printf("Valor Final (com Descontos e Taxa R$ 8): R$ %.2f\n", totalComRegras);
            System.out.println("-----------------------------------------------");
        }
    }
}

 private static void atualizarStatusPedido() {
        System.out.print("Código do Pedido: ");
        int cod = scanner.nextInt(); scanner.nextLine();
        System.out.print("Novo Status (Caminho/Entregue): ");
        String status = scanner.nextLine();
        
        if (pedidoDAO.atualizarStatus(cod, status)) {
            System.out.println("✅ Status atualizado!");
        }
    }

    private static void excluirPedido() {
        System.out.print("Código do Pedido para DELETAR: ");
        int cod = scanner.nextInt(); scanner.nextLine();
        System.out.print("⚠ Isso apagará os itens do pedido. Confirma? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            pedidoDAO.excluir(cod);
            System.out.println("✅ Pedido excluído.");
        }
    }
}