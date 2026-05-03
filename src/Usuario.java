package src;
public abstract class Usuario {
    
    protected int id;

    protected int codigo;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String endereco;
    public Usuario(int codigo, String nome, String cpf, String telefone, String endereco){
        this.codigo = codigo;
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String exibirDados();

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

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        if(cpf == null || cpf.length() != 11){
            throw new IllegalArgumentException("CPF inválido!");
        }
        this.cpf = cpf;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        if(telefone == null || telefone.length() != 10){
            throw new IllegalArgumentException("Telefone inválido!");
        }
        this.telefone = telefone;
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

    public String toString() {
    return "Nome: " + nome; 
    }
}
