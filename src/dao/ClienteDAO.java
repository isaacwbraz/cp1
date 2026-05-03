package src.dao;

import src.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}
