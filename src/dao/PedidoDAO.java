package src.dao;

import src.Pedido;
import src.itemPedido;
import java.sql.*;

public class PedidoDAO {

    public boolean inserir(Pedido pedido) {
        String sql = "INSERT INTO pedido (cliente_id, restaurante_id, entregador_id, status, valor_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getCliente().getId());
            stmt.setInt(2, pedido.getRestaurante().getId());
            if (pedido.getEntregador() != null) {
                stmt.setInt(3, pedido.getEntregador().getId());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            stmt.setString(4, "Pendente");
            stmt.setDouble(5, pedido.getValorTotal());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setId(rs.getInt(1));
                    }
                }
                
                return salvarItens(pedido ); 
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar pedido: " + e.getMessage());
            return false;
        }
    }

    private boolean salvarItens(Pedido pedido) {
    String sql = "INSERT INTO itempedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        for (itemPedido item : pedido.getItens()) {
            stmt.setInt(1, pedido.getId()); 
            stmt.setInt(2, item.getProduto().getId()); 
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPrecoUnitario());
            
            stmt.addBatch();
        }
        stmt.executeBatch();
        return true;

    } catch (SQLException e) {
        System.err.println("Erro ao salvar itens no banco: " + e.getMessage());
        return false;
    }
    
  }
}