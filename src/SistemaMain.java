package src;
import java.util.Scanner;

import src.dao.ClienteDAO;
import src.dao.EntregadorDAO;
import src.dao.PedidoDAO;
import src.dao.ProdutoDAO;
import src.dao.RestauranteDAO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SistemaMain {
    private static Scanner scanner = new Scanner(System.in);

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static RestauranteDAO restauranteDAO = new RestauranteDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static PedidoDAO pedidoDAO = new PedidoDAO();

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
                case 1: cadastrarRestaurante(); break;
                case 2: listarRestaurantes(); break;
                case 3: cadastrarProduto(); break;
                case 4: listarProdutos(); break;
                case 5: cadastrarCliente(); break;
                case 6: listarClientes(); break;
                case 7: cadastrarEntregador(); break;
                case 8: listarEntregadores(); break;
                case 9: cadastrarPedido(); break;
                case 10: listarPedidos(); break;
                case 0: System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE DELIVERY ===");
        System.out.println("1. Cadastrar Restaurante");
        System.out.println("2. Listar Restaurantes");
        System.out.println("3. Cadastrar Produto");
        System.out.println("4. Listar Produtos");
        System.out.println("5. Cadastrar Cliente");
        System.out.println("6. Listar Clientes");
        System.out.println("7. Cadastrar Entregador");
        System.out.println("8. Listar Entregadores");
        System.out.println("9. Cadastrar Pedido");
        System.out.println("10. Listar Pedidos");
        System.out.println("0. Sair");
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

    // ===== PRODUTO =====
    private static void cadastrarProduto() {
        System.out.println("\n--- CADASTRAR PRODUTO ---");
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

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado. Cadastre um restaurante primeiro!");
            return;
        }
        Restaurante restaurante = null;
        for (Usuario u : usuarios) {
            if (u instanceof Restaurante) {
                restaurante = (Restaurante) u;
                break;
            }
        }

        Produto p = new Produto(codigo, nome, descricao, preco, categoria, restaurante);
        if (produtoDAO.inserir(p, restaurante.getId())) { 
    System.out.println("Produto vinculado ao restaurante " + restaurante.getNome() + " e salvo no banco!");
    } else {
        System.out.println("Erro ao salvar produto.");
    }
        System.out.println("Produto cadastrado com sucesso!");
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
    System.out.print("Status: ");
    String status = scanner.nextLine();
  
    Entregador e = new Entregador(codigo, nome, cpf, telefone, veiculo, status);

    EntregadorDAO entregadorDAO = new EntregadorDAO();
    entregadorDAO.salvar(e); 
    System.out.println("Entregador cadastrado e salvo no banco de dados com sucesso!");
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

    // ===== PEDIDO =====
   private static void cadastrarPedido() {
    System.out.println("\n--- CADASTRAR PEDIDO ---");

    Restaurante rest = null;
    Cliente cli = null;
    Entregador ent = null;

    for (Usuario u : usuarios) {
        if (u instanceof Restaurante && rest == null) rest = (Restaurante) u;
        if (u instanceof Cliente && cli == null) cli = (Cliente) u;
        if (u instanceof Entregador && ent == null) {
            Entregador e = (Entregador) u;
            if (e.getStatus().toLowerCase().contains("dispon")) {
                ent = e;
            }
        }
    }

    if (rest == null || cli == null || produtos.isEmpty()) {
        System.out.println("Erro: Verifique se há restaurantes, clientes e produtos cadastrados!");
        return;
    }

    Pedido pedido = new Pedido(rest, cli);

    Produto produto = produtos.get(0);
    System.out.print("Quantidade do produto " + produto.getNome() + ": ");
    int qtd = scanner.nextInt();
    scanner.nextLine();

    pedido.adicionarItem(produto, qtd);

    if (ent != null) {
        if (pedido.atribuirEntregador(ent)) {
            System.out.println("Entregador " + ent.getNome() + " atribuído ao pedido!");
        }
    } else {
        System.out.println("Nenhum entregador disponível no momento!");
    }

    if (pedidoDAO.inserir(pedido)) {
    System.out.println("Pedido salvo com sucesso no banco! ID: " + pedido.getId());
    } else {
        System.out.println("Erro ao persistir pedido.");
    }
    System.out.println("Pedido cadastrado com sucesso!");
}

private static void listarPedidos() {
    System.out.println("\n--- LISTA DE PEDIDOS (BANCO DE DATOS) ---");
    
    PedidoDAO pedidoDAO = new PedidoDAO();
    List<Pedido> listaDoBanco = pedidoDAO.listarTodos();

    if (listaDoBanco.isEmpty()) {
        System.out.println("Nenhum pedido encontrado no banco de dados.");
    } else {
        for (Pedido p : listaDoBanco) {
            System.out.println(p);
        }
    }
}
}
