package src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.model.Cliente;

public class ClienteDAO {
 
    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, telefone, cpf, endereco) VALUES (?, ?, ?, ?, ?)";
 
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
 
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
 
            int linhasAfetadas = stmt.executeUpdate();
 
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cliente.setId(rs.getInt(1));
                    }
                }
                System.out.println("✅ Cliente salvo no banco! ID: " + cliente.getId());
                return true;
            }
            return false;
 
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ou inserir: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listarTodos() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM cliente";

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Cliente c = new Cliente(
                rs.getInt("codigo"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("endereco")
            );
            clientes.add(c);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar clientes: " + e.getMessage());
    }
    return clientes;
    }

    public Cliente buscarPorId(int id) {
    Cliente c = null;
    String sql = "SELECT * FROM cliente WHERE codigo = ?";

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                c = new Cliente(
                    rs.getInt("codigo"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("endereco")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar cliente: " + e.getMessage());
    }
    return c;
  }

  public boolean atualizar(Cliente cliente) {
    String sql = "UPDATE cliente SET nome = ?, email = ?, cpf = ? WHERE codigo = ?";
    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getCpf());
        stmt.setInt(4, cliente.getCodigo());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        return false;
    }
  }

  public boolean excluir(int codigo) {
    String sql = "DELETE FROM cliente WHERE codigo = ?";
    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao excluir cliente: " + e.getMessage());
        return false;
    }
 }
}
