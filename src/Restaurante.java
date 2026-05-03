package src;
public class Restaurante extends Usuario {
    private static final double TAXA_ENTREGA = 8.0;

    private String cnpj;
    private String categoriaCulinaria;

    public Restaurante(int codigo, String nome, String endereco, String cnpj,
        String telefone, String categoriaCulinaria){
            super(codigo, nome, null, telefone, endereco); 
            setCnpj(cnpj);
            setCategoriaCulinaria(categoriaCulinaria);
        }

    
    @Override
    public String exibirDados() {
        return "TIPO: Restaurante | ID: " + codigo + 
               " | Nome: " + nome + 
               " | CNPJ: " + cnpj + 
               " | Categoria: " + categoriaCulinaria + 
               " | Taxa de Entrega: R$" + TAXA_ENTREGA;
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
}