package src;
public class itemPedido {
    
    private int codigo;
    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public itemPedido(int codigo, Produto produto, int quantidade, double precoUnitario){
        this.codigo = codigo;
        setProduto(produto);
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
    }

    public itemPedido() {
    }   

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public Produto getProduto(){
        return produto;
    }

    public void setProduto(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("Produto inválido!");
        }
        this.produto = produto;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int quantidade){
        if(quantidade <=0){
            throw new IllegalArgumentException("Quantidade deve ser positiva!");
        }
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario(){
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario){
        if(precoUnitario <=0){
            throw new IllegalArgumentException("Preço unitário inválido!");
        }
        this.precoUnitario = precoUnitario;
    }

    public double getSubtotal(){
        return precoUnitario * quantidade;
    }
    
    @Override
    public String toString() {
    return quantidade + "x " + produto.getNome() + " | Subtotal: R$"; }
    
}
