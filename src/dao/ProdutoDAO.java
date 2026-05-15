package src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.model.Produto;
import src.model.Restaurante;

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
    
    RestauranteDAO restDAO = new RestauranteDAO();

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int restauranteId = rs.getInt("restaurante_id");
            
            Restaurante rest = restDAO.buscarPorId(restauranteId);

            Produto p = new Produto(
                rs.getInt("codigo"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                rs.getString("categoria"),
                rest
            );
            produtos.add(p);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar produtos: " + e.getMessage());
    }
    return produtos;
}

   public boolean salvar(Produto p) {
        String sql = "INSERT INTO produto (codigo, nome, descricao, preco, categoria, restaurante_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDescricao());
            stmt.setDouble(4, p.getPreco());
            stmt.setString(5, p.getCategoria());
            
            stmt.setInt(6, p.getRestaurante().getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Produto produto) {
    String sql = "UPDATE produto SET nome = ?, preco = ?, descricao = ? WHERE codigo = ?";
    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setString(3, produto.getDescricao());
        stmt.setInt(4, produto.getCodigo());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar produto: " + e.getMessage());
        return false;
    }
}

public boolean excluir(int codigo) {
    String sql = "DELETE FROM produto WHERE codigo = ?";
    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao excluir produto: " + e.getMessage());
        return false;
    }
  }

  public Produto buscarPorId(int id) {
    Produto p = null;
    String sql = "SELECT * FROM produto WHERE codigo = ?";

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                p = new Produto(
                    rs.getInt("codigo"),
                    rs.getString("nome"),
                    rs.getDouble("preco")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
    }
    return p;
}
}