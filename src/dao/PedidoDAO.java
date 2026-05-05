package src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.model.Cliente;
import src.model.Entregador;
import src.model.Pedido;
import src.model.Restaurante;
import src.model.itemPedido;

public class PedidoDAO {

    public boolean inserir(Pedido pedido) {
        String sql = "INSERT INTO pedido ( status, data_pedido, cliente_id, restaurante_id, entregador_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pedido.getStatus());
            stmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.setInt(3, pedido.getCliente().getCodigo()); 
            stmt.setInt(4, pedido.getRestaurante().getCodigo());

            if (pedido.getEntregador() != null) {
                stmt.setInt(5, pedido.getEntregador().getCodigo());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
        
            int affectedRows = stmt.executeUpdate();

        if (affectedRows > 0) {
    
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setCodigo(rs.getInt(1));
                }
            }
            return salvarItens(pedido, conn);
        }
            return false;
        }catch (SQLException e) {
            System.err.println("Erro ao salvar pedido: " + e.getMessage());
            return false;
        }
    }

    private boolean salvarItens(Pedido pedido, Connection conn) {
        String sqlItem = "INSERT INTO itempedido (quantidade, preco_unitario, pedido_id, produto_id) VALUES (?, ?, ?, ?)";

        try {
            for (itemPedido item : pedido.getItens()) {
                try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {
                    stmtItem.setInt(1, item.getQuantidade());
                    stmtItem.setDouble(2, item.getPrecoUnitario());
                    stmtItem.setInt(3, pedido.getCodigo());
                    stmtItem.setInt(4, item.getProduto().getCodigo());

                    stmtItem.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar itens do pedido: " + e.getMessage());
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
                
                Pedido p = new Pedido(rest, cli); 
                p.setCodigo(rs.getInt("codigo"));
                p.setStatus(rs.getString("status"));
            
                int entregadorId = rs.getInt("entregador_id");
                if (!rs.wasNull()) {
                    Entregador ent = entregadorDAO.buscarPorId(entregadorId);
                    p.atribuirEntregador(ent);
                }

                p.setItens(buscarItensDoPedido(p.getCodigo(), conn));


                pedidos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    public List<itemPedido> buscarItensDoPedido(int pedidoId, Connection conn) {
        List<itemPedido> itens = new ArrayList<>();
        String sql = "SELECT * FROM itempedido WHERE pedido_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    itemPedido item = new itemPedido();
                    item.setCodigo(rs.getInt("codigo"));
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setPrecoUnitario(rs.getDouble("preco_unitario"));
                    itens.add(item);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar itens do pedido " + pedidoId + ": " + e.getMessage());
        }
        return itens;
    }

    public boolean atualizarStatus(int codigo, String novoStatus) {
        String sql = "UPDATE pedido SET status = ? WHERE codigo = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int codigo) {
        String sqlItens = "DELETE FROM itempedido WHERE pedido_id = ?";
        String sqlPedido = "DELETE FROM pedido WHERE codigo = ?";

        try (Connection conn = ConexaoBD.getConexao()) {
            conn.setAutoCommit(false);
            try {
                // Deleta itens
                try (PreparedStatement stItem = conn.prepareStatement(sqlItens)) {
                    stItem.setInt(1, codigo);
                    stItem.executeUpdate();
                }
                // Deleta pedido
                try (PreparedStatement stPedido = conn.prepareStatement(sqlPedido)) {
                    stPedido.setInt(1, codigo);
                    int deletado = stPedido.executeUpdate();
                    conn.commit();
                    return deletado > 0;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir pedido: " + e.getMessage());
            return false;
        }
    }
}