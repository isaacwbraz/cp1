package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.Entregador;
import src.dao.ConexaoBD;

public class EntregadorDAO {

    public boolean salvar(Entregador e) {
        String sql = "INSERT INTO entregador (codigo, nome, cpf, telefone, veiculo, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, e.getCodigo());
            stmt.setString(2, e.getNome());
            stmt.setString(3, e.getCpf());
            stmt.setString(4, e.getTelefone());
            stmt.setString(5, e.getVeiculo());
            stmt.setString(6, e.getStatus());
            
            stmt.executeUpdate();
            System.out.println("Entregador salvo com sucesso!");
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar entregador: " + ex.getMessage());
            return false;
        }
    }

    public Entregador buscarPorId(int id) {
        Entregador ent = null;
        String sql = "SELECT * FROM entregador WHERE codigo = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ent = new Entregador(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("veiculo"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar entregador: " + ex.getMessage());
        }
        return ent;
    }

    public List<Entregador> listarTodos() {
        List<Entregador> entregadores = new ArrayList<>();
        String sql = "SELECT * FROM entregador";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entregador ent = new Entregador(
                    rs.getInt("codigo"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("veiculo"),
                    rs.getString("status")
                );
                entregadores.add(ent);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar entregadores: " + ex.getMessage());
        }
        return entregadores;
    }

    public void atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE entregador SET status = ? WHERE codigo = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar status do entregador: " + ex.getMessage());
        }
    }

    public boolean atualizar(Entregador entregador) {
    String sql = "UPDATE entregador SET nome = ?, veiculo = ? WHERE codigo = ?";
    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, entregador.getNome());
        stmt.setString(2, entregador.getVeiculo());
        stmt.setInt(3, entregador.getCodigo());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar entregador: " + e.getMessage());
        return false;
    }
  }

public boolean excluir(int codigo) {
    String sql = "DELETE FROM entregador WHERE codigo = ?";
    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao excluir entregador: " + e.getMessage());
        return false;
    }
  }
}