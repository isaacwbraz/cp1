package src;
public class Restaurante {
     private static final double TAXA_ENTREGA = 8.0;

    private int codigo;
    private String nome;
    private String endereco;
    private String cnpj;
    private String telefone;
    private String categoriaCulinaria;

    public Restaurante(int codigo, String nome, String endereco, String cnpj,
        String telefone, String categoriaCulinaria){
            this.codigo = codigo;
            setNome(nome);
            setEndereco(endereco);
            setCnpj(cnpj);
            setTelefone(telefone);
            setCategoriaCulinaria(categoriaCulinaria);
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

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        if(endereco == null || endereco.isEmpty()){
            throw new IllegalArgumentException("Endereço inválido!");
        }
        this.endereco = endereco;
    }

    public String getCnpj(){
        return cnpj;
    }

    public void setCnpj(String cnpj){
        if(cnpj == null || cnpj.length() != 14){
            throw new IllegalArgumentException("CNPJ inválido!");
        }
        this.cnpj = cnpj;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        if(telefone == null || telefone.length() != 9){
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public String getCategoriaCulinaria(){
        return categoriaCulinaria;
    }

    public void setCategoriaCulinaria(String categoriaCulinaria){
        if(categoriaCulinaria == null || categoriaCulinaria.isEmpty()){
            throw new IllegalArgumentException("Categoria inválida!");
        }
        this.categoriaCulinaria = categoriaCulinaria;
    }

    public double getTaxaEntrega() {
        return TAXA_ENTREGA;
    }

    @Override
    public String toString() {
        return "Restaurante: " + nome + " | CNPJ: " + cnpj + 
        " | Telefone: " + telefone + " | Categoria: " + categoriaCulinaria;
    }
}