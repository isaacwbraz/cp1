package src;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<itemPedido> itens = new ArrayList<>();
    private Restaurante restaurante;
    private Entregador entregador;

    public Pedido(Restaurante restaurante) {
        this.restaurante = restaurante;
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


        soma += restaurante.getTaxaEntrega();

        return soma;
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
               " | Total: R$" + calcularTotal() +
               (entregador != null ? " | Entregador: " + entregador.getNome() : " | Sem entregador");
    }
}
