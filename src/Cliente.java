package src;
public class Cliente extends Usuario {

    private String email;

    public Cliente(int codigo, String nome,
         String cpf, String telefone, String email, String endereco){
            super(codigo, nome, cpf, telefone, endereco);
            setEmail(email);

         }

    @Override
    public String exibirDados() {
        return "TIPO: Cliente | ID: " + codigo + 
               " | Nome: " + nome + 
               " | CPF: " + cpf + 
               " | Email: " + email + 
               " | Endereço: " + endereco;
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

    @Override
    public String toString() {
        return "Cliente: " + nome + " (" + email + ")";
    }
}
