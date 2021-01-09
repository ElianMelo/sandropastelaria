package com.sandropastelaria.omniorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sandropastelaria.omniorder.config.FabricaDeConexao;
import com.sandropastelaria.omniorder.model.Mesa;

public class MesaDAO {

	public List<Mesa> todos() {
		List<Mesa> lista = new ArrayList<Mesa>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from mesa;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			ResultSet retorno = stmt.executeQuery();
			while (retorno.next()) {
                Integer idMesa = retorno.getInt("id_mesa");
				Boolean limpa = retorno.getBoolean("limpa");
                Boolean livre = retorno.getBoolean("livre");
                
                Mesa mesa = new Mesa(idMesa, limpa, livre);
				
				lista.add(mesa);
			}
			retorno.close();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public void atualizar(Mesa mesa) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "update mesa set limpa = ?, livre = ?"
				+ " where id_mesa = ?";
		try {
            stmt = conexao.prepareStatement(sql);
            
			stmt.setBoolean(1, mesa.isLimpa());
			stmt.setBoolean(2, mesa.isLivre());
            stmt.setInt(3, mesa.getIdMesa());
            
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

    public Mesa buscaPorId(int id) {
        Connection conexao = FabricaDeConexao.getConnection();
        String sql = "select * from mesa where id_mesa = ?;";
        Mesa mesa = null;
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet retorno = stmt.executeQuery();
            retorno.next();
            Integer idMesa = retorno.getInt("id_mesa");
            Boolean limpa = retorno.getBoolean("limpa");
            Boolean livre = retorno.getBoolean("livre");
            mesa = new Mesa(idMesa, limpa, livre);
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mesa;
    }

}
