package com.sandropastelaria.omniorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sandropastelaria.omniorder.config.FabricaDeConexao;
import com.sandropastelaria.omniorder.enums.EstadoCozinha;
import com.sandropastelaria.omniorder.enums.EstadoPedido;
import com.sandropastelaria.omniorder.enums.TipoProduto;
import com.sandropastelaria.omniorder.model.ItemPedido;
import com.sandropastelaria.omniorder.model.Mesa;
import com.sandropastelaria.omniorder.model.Pedido;
import com.sandropastelaria.omniorder.model.Produto;

public class PedidoDAO {

	public void inserir(Pedido pedido, List<ItemPedido> itens) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		Integer idPedido;
		String sql = "insert into pedido" + "(estado_pedido, estado_cozinha, id_mesa)"
				+ " values (?,?,?)";
		try {
			stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, EstadoPedido.ABERTO.getDescricao());
            stmt.setString(2, EstadoCozinha.PREPARANDO.getDescricao());
			stmt.setInt(3, pedido.getIdMesa());
			
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Falha na criação do pedido.");
			}

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idPedido = (int) generatedKeys.getLong(1);
				} else {
					throw new SQLException("Falha na criação do pedido.");
				}
			}
			
			Integer idMesa = pedido.getIdMesa();

			// Inserir ItemPedido
			ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			for(ItemPedido item : itens) {
				itemPedidoDAO.inserir(new ItemPedido(idPedido, item.getIdProduto(), item.getQuantidade()));
				// Muda Produto
				Produto produto = produtoDAO.buscaPorId(item.getIdProduto());
				if(!produto.getTipoProduto().equals(TipoProduto.PASTEL.getDescricao())) {
					produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
					if(produto.getQuantidade() < 0) {
						throw new SQLException("Quantidade excedente em estoque.");
					}
				}
				produtoDAO.atualizar(produto);
			}

			// Mudar EstadoMesa
			MesaDAO mesaDAO = new MesaDAO();
			Mesa mesa = new Mesa(idMesa, true, false);
			mesaDAO.atualizar(mesa);

			stmt.close();
			conexao.close();
		} catch (Exception e) {
            System.out.println(e);
		}
	}

	public List<Pedido> todos() {
		List<Pedido> lista = new ArrayList<Pedido>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from pedido;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			ResultSet retorno = stmt.executeQuery();
			while (retorno.next()) {
				Integer idPedido = retorno.getInt("id_pedido");
				String estadoPedido = retorno.getString("estado_pedido");
				String estadoCozinha = retorno.getString("estado_cozinha");
				Integer idMesa = retorno.getInt("id_mesa");
				
				Pedido pedido = new Pedido(idPedido, estadoPedido, estadoCozinha, idMesa);
				lista.add(pedido);
			}
			retorno.close();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public void excluir(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "delete from pedido where id_pedido = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(Pedido pedido) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "update pedido set estado_pedido=?,estado_cozinha=?,id_mesa=?"
				+ " where id_pedido = ?";
		try {
            stmt = conexao.prepareStatement(sql);
            
			stmt.setString(1, pedido.getEstadoPedido());
			stmt.setString(2, pedido.getEstadoCozinha());
			stmt.setInt(3, pedido.getIdMesa());
            stmt.setInt(4, pedido.getIdPedido());

			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Pedido buscaPorId(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from pedido where id_pedido = ?;";
		Pedido pedido = null;
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet retorno = stmt.executeQuery();
			retorno.next();
			Integer idPedido = retorno.getInt("id_pedido");
			String estadoPedido = retorno.getString("estado_pedido");
            String estadoCozinha = retorno.getString("estado_cozinha");
            Integer idMesa = retorno.getInt("id_mesa");
			pedido = new Pedido(idPedido, estadoPedido, estadoCozinha, idMesa);
			stmt.close();
			conexao.close();
		} catch (

		SQLException e) {
			System.out.println(e.getMessage());
		}
		return pedido;
	}
}