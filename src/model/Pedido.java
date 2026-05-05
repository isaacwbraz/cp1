package src.model;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int codigo; 
    private String status;
    private int id; 
    private List<itemPedido> itens = new ArrayList<>();
    private Restaurante restaurante;
    private Entregador entregador;
    private Cliente cliente;

    public Pedido(Restaurante restaurante, Cliente cliente) {
        this.restaurante = restaurante;
        this.cliente = cliente;
        this.status = "Pendende";
        this.itens = new ArrayList<>();
    }

    public int getCodigo() {
    return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<itemPedido> getItens() {
        return itens;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setItens(List<itemPedido> itens) {
    this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void adicionarItem(itemPedido item) {
        itens.add(item);
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itemPedido novoItem = new itemPedido(itens.size() + 1, produto, quantidade, produto.getPreco());
        this.adicionarItem(novoItem); 
    }

    public double calcularTotal() {
    double soma = 0;
    for(itemPedido item : itens) {
        soma += item.getSubtotal();
    }

    if(soma > 300) soma *= 0.85;
    else if(soma > 200) soma *= 0.90;
    else if(soma > 100) soma *= 0.95;

    return soma + restaurante.getTaxaEntrega();
}

    public boolean atribuirEntregador(Entregador e) {
        if(e.getStatus().toLowerCase().contains("dispon")) {
            this.entregador = e;
            e.setStatus("em entrega");
            return true;
        } else {
            System.out.println("Entregador não está disponível!");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Pedido do restaurante " + restaurante.getNome() +
               " | Total: R$" + String.format("%.2f", calcularTotal()) +
               (entregador != null ? " | Entregador: " + entregador.getNome() : " | Sem entregador");
    }
}