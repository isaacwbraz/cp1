package src;
public class Cliente extends Usuario {

    private String email;

    public Cliente(int codigo, String nome,
         String cpf, String telefone, String email, String endereco){
            super(codigo, nome, cpf, telefone, endereco);
            setEmail(email);

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
        return "Cliente: " + nome + " | CPF: " + cpf +
         " | Telefone: " + telefone + " | Email: " + email;
    }
}
