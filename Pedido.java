import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<itemPedido> itens = new ArrayList<>();
    private Restaurante restaurante;

    public Pedido(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void adicionarItem(itemPedido item) {
        itens.add(item);
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
}
