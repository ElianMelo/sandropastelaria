package com.sandropastelaria.omniorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sandropastelaria.omniorder.config.FabricaDeConexao;
import com.sandropastelaria.omniorder.model.ItemPedido;

public class ItemPedidoDAO {

	public void inserir(ItemPedido item) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "insert into item_pedido" + "(id_pedido, id_produto, quantidade)"
				+ " values (?,?,?)";
		try {
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, item.getIdPedido());
			stmt.setInt(2, item.getIdProduto());
            stmt.setInt(3, item.getQuantidade());
			
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
            System.out.println(e);
		}
	}

	public List<ItemPedido> todos() {
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from item_pedido;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			ResultSet retorno = stmt.executeQuery();
			while (retorno.next()) {
				Integer idPedido = retorno.getInt("id_pedido");
				Integer idProduto = retorno.getInt("id_produto");
				Integer quantidade = retorno.getInt("quantidade");
				
				ItemPedido item = new ItemPedido(idPedido, idProduto, quantidade);
				lista.add(item);
			}
			retorno.close();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public void excluir(int idPedido, int idProduto) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "delete from item_pedido where id_pedido = ? and id_produto = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idPedido);
			stmt.setInt(1, idProduto);
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(ItemPedido item) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "update item_pedido set quantidade = ?"
				+ " where id_pedido = ? and id_produto = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, item.getQuantidade());
			stmt.setInt(2, item.getIdPedido());
			stmt.setInt(3, item.getIdProduto());

			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ItemPedido buscaPorId(int idPedidoBusca, int idProdutoBusca) {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from item_pedido where id_pedido = ? and id_produto = ?;";
		ItemPedido item = null;
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idPedidoBusca);
			stmt.setInt(2, idProdutoBusca);
			ResultSet retorno = stmt.executeQuery();

			retorno.next();
			
			Integer idPedido = retorno.getInt("id_pedido");
			Integer idProduto = retorno.getInt("id_produto");
			Integer quantidade = retorno.getInt("quantidade");

			item = new ItemPedido(idPedido, idProduto, quantidade);
			stmt.close();
			conexao.close();
		} catch ( SQLException e) {
			System.out.println(e.getMessage());
		}
		return item;
	}

	public List<ItemPedido> buscaPorIdPedido(int id) {
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select i.*, p.nome_produto from item_pedido i inner join produto p on p.id_produto = i.id_produto where id_pedido = ?;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet retorno = stmt.executeQuery();
			while (retorno.next()) {
				Integer idPedido = retorno.getInt("id_pedido");
				Integer idProduto = retorno.getInt("id_produto");
				Integer quantidade = retorno.getInt("quantidade");
				String nomeProduto = retorno.getString("nome_produto");
				
				ItemPedido item = new ItemPedido(idPedido, idProduto, quantidade, nomeProduto);
				lista.add(item);
			}
			retorno.close();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
}