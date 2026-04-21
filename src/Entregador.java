package src;
public class Entregador extends Usuario {

    private String veiculo;
    private String status;

    public Entregador(int codigo, String nome, String cpf, String telefone,
        String veiculo, String status){
            super(codigo, nome, cpf, telefone, null);
            setVeiculo(veiculo);
            setStatus(status);
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
        return super.toString() + " | Veículo: " + veiculo + " | Status: " + status;
    }
}
