package src;
public class Cliente {
    
    private int codigo;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;

    public Cliente(int codigo, String nome,
         String cpf, String telefone, String email, String endereco){
            this.codigo = codigo;
            setNome(nome);
            setCpf(cpf);
            setTelefone(telefone);
            setEmail(email);
            setEndereco(endereco);
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
        if(telefone == null || telefone.length() != 9){
            throw new IllegalArgumentException("Telefone inválido!");
        }
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        if(email == null || !email.contains("@")){
            throw new IllegalArgumentException("Email inválido!");
        }
        this.email = email;
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


    @Override
    public String toString() {
        return "Cliente: " + nome + " | CPF: " + cpf +
         " | Telefone: " + telefone + " | Email: " + email;
    }
}
