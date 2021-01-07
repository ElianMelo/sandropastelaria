package com.sandropastelaria.omniorder.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sandropastelaria.omniorder.config.FabricaDeConexao;
import com.sandropastelaria.omniorder.enums.Cargo;
import com.sandropastelaria.omniorder.model.Funcionario;

public class LoginDAO {

	public Funcionario validar(Funcionario funcionario) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		
		Funcionario funcionarioAcessado = null;
		String sql = "SELECT * FROM funcionario WHERE ctps_num=? AND senha=?";
		
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, funcionario.getCtpsNum());
			stmt.setString(2, funcionario.getSenha());
			
			ResultSet retorno = stmt.executeQuery();
			
			if (retorno.next()) {
				Integer id = retorno.getInt("id_funcionario");
				String nome = retorno.getString("nome_completo");
				String senha = retorno.getString("senha");
				Long cpf = retorno.getLong("cpf");
				String rg = retorno.getString("rg");
				Integer ctpsNum = retorno.getInt("ctps_num");
				Integer ctpsSerie = retorno.getInt("ctps_serie");
				Date data = retorno.getDate("data_nascimento");
				String filiacao = retorno.getString("filiacao");
				String cargo = retorno.getString("cargo");
				Integer jornadaSemanal = retorno.getInt("jornada_semanal");
				Float remuneracao = retorno.getFloat("remuneracao");
				funcionarioAcessado = new Funcionario(id, nome, senha, cpf, rg, ctpsNum, ctpsSerie, data, filiacao, 
																Cargo.toEnum(cargo), jornadaSemanal, remuneracao);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return funcionarioAcessado;
	}
	
}
