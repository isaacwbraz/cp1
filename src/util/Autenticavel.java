package src.util;

public interface Autenticavel {
    void registrarLog(String acao);
    boolean validarAcesso(String credencial);
}
