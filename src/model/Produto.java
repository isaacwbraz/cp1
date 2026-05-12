package src.model;
public class Produto{
    
    private int id;

    private int codigo;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    private Restaurante restaurante;

    public Produto(int codigo, String nome, String descricao, double preco,
        String categoria, Restaurante restaurante){
            this.codigo = codigo;
            setNome(nome);
            setDescricao(descricao);
            setPreco(preco);
            setCategoria(categoria);
            this.restaurante = restaurante;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCodigo(){
        return codigo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        if(descricao == null || descricao.isEmpty()){
            throw new IllegalArgumentException("Descrição inválida!");
        }
        this.descricao = descricao;
    }

    public double getPreco(){
        return preco;
    }

    public void setPreco(double preco){
        if(preco <= 0){
            throw new IllegalArgumentException("O preço tem que ser positivo!");
        }
        this.preco = preco;
    }
    
    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        if(categoria == null || categoria.isEmpty()){
            throw new IllegalArgumentException("Categoria inválida!");
        }
        this.categoria = categoria;
    }

    public Restaurante getRestaurante(){
        return restaurante;
    }

     @Override
    public String toString() {
        return "Produto: " + nome + " | Preço: R$" + preco + " | Restaurante: " + restaurante.getNome();
    }


    // usado para buscar o id no produtoDAO
    public Produto(int codigo, String nome, double preco) {
    this.codigo = codigo;
    this.nome = nome;
    this.preco = preco;
    }
}
