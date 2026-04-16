package src;
import java.util.Scanner;
import java.util.ArrayList;

public class SistemaMain {
    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Restaurante> restaurantes = new ArrayList<>();
    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Entregador> entregadores = new ArrayList<>();
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
        restaurantes.add(r);
        System.out.println("Restaurante cadastrado com sucesso!");
    }

    private static void listarRestaurantes() {
        System.out.println("\n--- LISTA DE RESTAURANTES ---");
        if (restaurantes.isEmpty()) System.out.println("Nenhum restaurante cadastrado.");
        else for (Restaurante r : restaurantes) System.out.println(r);
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

        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado. Cadastre um restaurante primeiro!");
            return;
        }
        Restaurante restaurante = restaurantes.get(0);

        Produto p = new Produto(codigo, nome, descricao, preco, categoria, restaurante);
        produtos.add(p);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        if (produtos.isEmpty()) System.out.println("Nenhum produto cadastrado.");
        else for (Produto p : produtos) System.out.println(p);
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
        clientes.add(c);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        if (clientes.isEmpty()) System.out.println("Nenhum cliente cadastrado.");
        else for (Cliente c : clientes) System.out.println(c);
    }

    // ===== ENTREGADOR =====
    private static void cadastrarEntregador() {
        System.out.println("\n--- CADASTRAR ENTREGADOR ---");
        System.out.print("Código: ");
        int codigo = scanner.nextInt(); scanner.nextLine();
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
        entregadores.add(e);
        System.out.println("Entregador cadastrado com sucesso!");
    }

    private static void listarEntregadores() {
        System.out.println("\n--- LISTA DE ENTREGADORES ---");
        if (entregadores.isEmpty()) System.out.println("Nenhum entregador cadastrado.");
        else for (Entregador e : entregadores) System.out.println(e);
    }

    // ===== PEDIDO =====
    private static void cadastrarPedido() {
    System.out.println("\n--- CADASTRAR PEDIDO ---");

    if (clientes.isEmpty() || produtos.isEmpty() || restaurantes.isEmpty() || entregadores.isEmpty()) {
        System.out.println("É necessário ter cliente, produto, restaurante e entregador cadastrados!");
        return;
    }

    Restaurante restaurante = restaurantes.get(0);
    Pedido pedido = new Pedido(restaurante);

    Produto produto = produtos.get(0);
    System.out.print("Quantidade do produto " + produto.getNome() + ": ");
    int qtd = scanner.nextInt();
    scanner.nextLine();

    itemPedido item = new itemPedido(1, produto, qtd, produto.getPreco());
    pedido.adicionarItem(item);

    Entregador entregadorDisponivel = null;
    for (Entregador e : entregadores) {
        if (e.getStatus().toLowerCase().contains("dispon")) {
            entregadorDisponivel = e;
            break;
        }
    }

    if (entregadorDisponivel != null) {
        if (pedido.atribuirEntregador(entregadorDisponivel)) {
            System.out.println("Entregador " + entregadorDisponivel.getNome() + " atribuído ao pedido!");
        }
    } else {
        System.out.println("Nenhum entregador disponível no momento!");
    }

    pedidos.add(pedido);
    System.out.println("Pedido cadastrado com sucesso!");
}

private static void listarPedidos() {
    System.out.println("\n--- LISTA DE PEDIDOS ---");
    if (pedidos.isEmpty()) {
        System.out.println("Nenhum pedido cadastrado.");
    } else {
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }
 }
}