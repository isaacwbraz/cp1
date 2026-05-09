package src.model;

interface Autenticavel {
    void registrarLog(String acao);
    boolean validarAcesso(String credencial);
}
