package src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.model.Restaurante;

public class RestauranteDAO {

    public boolean inserir(Restaurante restaurante) {
        String sql = "INSERT INTO restaurante (nome, endereco, cnpj, telefone, cat_culinaria) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getEndereco());
            stmt.setString(3, restaurante.getCnpj());
            stmt.setString(4, restaurante.getTelefone());
            stmt.setString(5, restaurante.getCategoriaCulinaria());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        restaurante.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir restaurante: " + e.getMessage());
            return false;
        }
    }

    public List<Restaurante> listarTodos() {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Restaurante r = new Restaurante(
                    rs.getInt("codigo"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("cnpj"),
                    rs.getString("telefone"),
                    rs.getString("cat_culinaria")
                    
                );
                r.setId(rs.getInt("codigo"));
                restaurantes.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar restaurantes: " + e.getMessage());
        }
        return restaurantes;
    }

    public Restaurante buscarPorId(int id) {
    Restaurante r = null;
    String sql = "SELECT * FROM restaurante WHERE codigo = ?";

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                r = new Restaurante(
                    rs.getInt("codigo"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("cnpj"),
                    rs.getString("telefone"),
                    rs.getString("cat_culinaria")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar restaurante: " + e.getMessage());
    }
    return r;
  }

    public boolean atualizar(Restaurante restaurante) {
    String sql = "UPDATE restaurante SET nome=?, endereco=?, cnpj=?, telefone=?, cat_culinaria=? WHERE codigo=?";

   try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
    stmt.setString(1, restaurante.getNome());
    stmt.setString(2, restaurante.getEndereco());
    stmt.setString(3, restaurante.getCnpj());
    stmt.setString(4, restaurante.getTelefone());
    stmt.setString(5, restaurante.getCategoriaCulinaria());
    stmt.setInt(6, restaurante.getCodigo());

    stmt.executeUpdate();
    return true;
    } catch (SQLException e) {
    System.err.println("Erro ao atualizar restaurante: " + e.getMessage());
    return false;
    }
  }

    public boolean excluir(int codigo) {
    String sql = "DELETE FROM restaurante WHERE codigo = ?";
    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao excluir restaurante: " + e.getMessage());
        return false;
    }
  }
}