package src.dao;

import src.Restaurante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                        // Atribui o código gerado pelo PostgreSQL ao objeto Java
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

    public List<Restaurante> listarRestaurantes() {
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
}