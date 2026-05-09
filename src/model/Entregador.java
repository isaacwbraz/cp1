package src.model;
public class Entregador extends Usuario implements Autenticavel {

    private String veiculo;
    private String status;

    public Entregador(int codigo, String nome, String cpf, String telefone,
        String veiculo, String status){
            super(codigo, nome, cpf, telefone, "");
            setVeiculo(veiculo);
            setStatus(status);
        }


    @Override
    public String exibirDados() {
        return "TIPO: Entregador | ID: " + codigo + 
               " | Nome: " + nome + 
               " | Veículo: " + veiculo + 
               " | Status Atual: " + status;
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
    public void registrarLog(String acao) {
        System.out.println("Log do Entregador: " + acao);
    }

    @Override
    public boolean validarAcesso(String credencial) {
        return this.getCpf().equals(credencial);
    }
}
