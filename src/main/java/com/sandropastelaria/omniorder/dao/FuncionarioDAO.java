package com.sandropastelaria.omniorder.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sandropastelaria.omniorder.config.FabricaDeConexao;
import com.sandropastelaria.omniorder.enums.Cargo;
import com.sandropastelaria.omniorder.model.Funcionario;

public class FuncionarioDAO {
	
	public void inserir(Funcionario funcionario) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "insert into funcionario"
				+ "(nome_completo, senha, cpf, rg, ctps_num, ctps_serie, data_nascimento, filiacao, cargo,"
				+ "jornada_semanal, remuneracao)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getSenha());
			stmt.setLong(3, funcionario.getCpf());
			stmt.setString(4, funcionario.getRg());
			stmt.setInt(5, funcionario.getCtpsNum());
			stmt.setInt(6, funcionario.getCtpsSerie());
			stmt.setDate(7, funcionario.getDataNascimento());
			stmt.setString(8, funcionario.getFiliacao());
			stmt.setString(9, funcionario.getCargo());
			stmt.setInt(10, funcionario.getJornadaSemanal());
			stmt.setFloat(11, funcionario.getRemuneracao());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Funcionario> todos() {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from funcionario;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			ResultSet retorno = stmt.executeQuery();
			while (retorno.next()) {
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
				Funcionario funcionario = new Funcionario(id, nome, senha, cpf, rg, ctpsNum, ctpsSerie, data, filiacao, Cargo.toEnum(cargo), jornadaSemanal, remuneracao);
				lista.add(funcionario);
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
		String sql = "delete from funcionario where id_funcionario = ?";
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
	
	public void atualizar(Funcionario funcionario) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "update funcionario set nome_completo=?,senha=?,cpf=?,rg=?,ctps_num=?,ctps_serie=?,"
				+ "data_nascimento=?,filiacao=?,cargo=?,jornada_semanal=?,remuneracao=?"
				+ " where id_funcionario = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getSenha());
			stmt.setLong(3, funcionario.getCpf());
			stmt.setString(4, funcionario.getRg());
			stmt.setInt(5, funcionario.getCtpsNum());
			stmt.setInt(6, funcionario.getCtpsSerie());
			stmt.setDate(7, funcionario.getDataNascimento());
			stmt.setString(8, funcionario.getFiliacao());
			stmt.setString(9, funcionario.getCargo());
			stmt.setInt(10, funcionario.getJornadaSemanal());
			stmt.setFloat(11, funcionario.getRemuneracao());
			stmt.setFloat(12,  funcionario.getIdFuncionario());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Funcionario buscaPorId(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from funcionario where id_funcionario = ?;";
		Funcionario funcionario = null;
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet retorno = stmt.executeQuery();
			retorno.next();
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
			funcionario = new Funcionario(id, nome, senha, cpf, rg, ctpsNum, ctpsSerie, data, filiacao, Cargo.toEnum(cargo), jornadaSemanal, remuneracao);
			stmt.close();
			conexao.close();
		} catch (

		SQLException e) {
			System.out.println(e.getMessage());
		}
		return funcionario;
	}
	
}
