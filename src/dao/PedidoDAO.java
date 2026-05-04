package src.dao;

import src.Cliente;
import src.Entregador;
import src.Pedido;
import src.Restaurante;
import src.itemPedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Pedido> listarTodos() {
    List<Pedido> pedidos = new ArrayList<>();
    String sql = "SELECT * FROM pedido";

    RestauranteDAO restauranteDAO = new RestauranteDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    EntregadorDAO entregadorDAO = new EntregadorDAO();

    try (Connection conn = ConexaoBD.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Restaurante rest = restauranteDAO.buscarPorId(rs.getInt("restaurante_id"));
            Cliente cli = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
            Entregador ent = entregadorDAO.buscarPorId(rs.getInt("entregador_id"));

            Pedido p = new Pedido(rest, cli); 
            p.setId(rs.getInt("codigo"));
        
            int entregadorId = rs.getInt("entregador_id");
            if (!rs.wasNull()) {
                p.atribuirEntregador(ent);
            }

            pedidos.add(p);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar pedidos: " + e.getMessage());
    }
    return pedidos;
    }
}