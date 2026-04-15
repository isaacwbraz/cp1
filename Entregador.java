public class Entregador {
    
    private int codigo;
    private String nome;
    private String cpf;
    private String telefone;
    private String veiculo;
    private String status;

    public Entregador(int codigo, String nome, String cpf, String telefone,
        String veivulo, String status){
            this.codigo = codigo;
            setNome(nome);
            setCpf(cpf);
            setTelefone(telefone);
            setVeiculo(veiculo);
            setStatus(status);
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

    public String getVeiculo(){
        return veiculo;
    }

    public void setVeiculo(String veiculo){
        if(veiculo == null || veiculo.isEmpty()){
            throw new IllegalArgumentException("Veiculo inválido!");
        }
        this.veiculo = veiculo;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        if(status == null || status.isEmpty()){
            throw new IllegalArgumentException("Status inválido!");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Entregador: " + nome + " | Veículo: " + veiculo + " | Status: " + status;
    }
}
