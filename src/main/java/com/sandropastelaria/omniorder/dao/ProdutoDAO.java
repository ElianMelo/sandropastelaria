package com.sandropastelaria.omniorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sandropastelaria.omniorder.config.FabricaDeConexao;
import com.sandropastelaria.omniorder.enums.TipoProduto;
import com.sandropastelaria.omniorder.model.Produto;

public class ProdutoDAO {

	public void inserir(Produto produto) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "insert into produto" + "(nome_produto, quantidade, tipo_produto)"
				+ " values (?,?,?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNomeProduto());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setString(3, produto.getTipoProduto());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Produto> todos() {
		List<Produto> lista = new ArrayList<Produto>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from produto;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			ResultSet retorno = stmt.executeQuery();
			while (retorno.next()) {
				Integer id = retorno.getInt("id_produto");
				String nomeProduto = retorno.getString("nome_produto");
				Integer quantidade = retorno.getInt("quantidade");
				String tipoProduto = retorno.getString("tipo_produto");
				
				Produto produto = new Produto(nomeProduto, quantidade, TipoProduto.toEnum(tipoProduto), id);
				lista.add(produto);
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
		String sql = "delete from produto where id = ?";
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

	public void atualizar(Produto produto) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "update produto set nome_produto=?,quantidade=?,tipo_produto=?"
				+ " where id = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNomeProduto());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setString(3, produto.getTipoProduto());
			stmt.setInt(4, produto.getIdProduto());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Produto buscaPorId(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from produto where id = ?;";
		Produto produto = null;
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet retorno = stmt.executeQuery();
			retorno.next();
			String nomeProduto = retorno.getString("nome_produto");
			Integer quantidade = retorno.getInt("quantidade");
			String tipoProduto = retorno.getString("tipo_produto");
			produto = new Produto(nomeProduto, quantidade, TipoProduto.toEnum(tipoProduto), id);
			stmt.close();
			conexao.close();
		} catch (

		SQLException e) {
			System.out.println(e.getMessage());
		}
		return produto;
	}
}