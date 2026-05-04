package src.dao;

import src.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean inserir(Produto produto, int restauranteId) {
        String sql = "INSERT INTO produto (nome, descricao, preco, categoria, restaurante_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getCategoria()); 
            stmt.setInt(5, restauranteId);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        produto.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public List<Produto> listarTodos() {
    List<Produto> produtos = new ArrayList<>();
    String sql = "SELECT * FROM produto";

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Produto p = new Produto(
                rs.getInt("codigo"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                rs.getString("categoria"),
                null
            );
            produtos.add(p);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar produtos: " + e.getMessage());
    }
    return produtos;
}
}