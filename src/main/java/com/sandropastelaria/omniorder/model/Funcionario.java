package com.sandropastelaria.omniorder.model;

import java.sql.Date;

import com.sandropastelaria.omniorder.enums.Cargo;

public class Funcionario {
	
	private Integer idFuncionario;
	private String nome;
	private String senha;
	private Long cpf;
	private String rg;
	private Integer ctpsNum;
	private Integer ctpsSerie;
	private Date dataNascimento;
	private String filiacao;
	private Cargo cargo;
	private Integer jornadaSemanal;
	private Float remuneracao;
	
	
	public Funcionario() {
		super();
	}
	
	public Funcionario(String nome, String senha, Long cpf, String rg, Integer ctpsNum, Integer ctpsSerie,
			Date dataNascimento, String filiacao, Cargo cargo, Integer jornadaSemanal, Float remuneracao) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.rg = rg;
		this.ctpsNum = ctpsNum;
		this.ctpsSerie = ctpsSerie;
		this.dataNascimento = dataNascimento;
		this.filiacao = filiacao;
		this.cargo = cargo;
		this.jornadaSemanal = jornadaSemanal;
		this.remuneracao = remuneracao;
	}
	
	public Funcionario(Integer idFuncionario, String nome, String senha, Long cpf, String rg, Integer ctpsNum,
			Integer ctpsSerie, Date dataNascimento, String filiacao, Cargo cargo, Integer jornadaSemanal,
			Float remuneracao) {
		super();
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.rg = rg;
		this.ctpsNum = ctpsNum;
		this.ctpsSerie = ctpsSerie;
		this.dataNascimento = dataNascimento;
		this.filiacao = filiacao;
		this.cargo = cargo;
		this.jornadaSemanal = jornadaSemanal;
		this.remuneracao = remuneracao;
	}
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getCpf() {
		return cpf;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public Integer getCtpsNum() {
		return ctpsNum;
	}
	
	public void setCtpsNum(Integer ctpsNum) {
		this.ctpsNum = ctpsNum;
	}
	
	public Integer getCtpsSerie() {
		return ctpsSerie;
	}
	
	public void setCtpsSerie(Integer ctpsSerie) {
		this.ctpsSerie = ctpsSerie;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getFiliacao() {
		return filiacao;
	}
	
	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}
	
	public String getCargo() {
		if(this.cargo != null) {
			return this.cargo.getDescricao();
		}
		return "";
	}
	
	public void setCargo(String cargo) {
		this.cargo = Cargo.toEnum(cargo);
	}
	
	public Integer getJornadaSemanal() {
		return jornadaSemanal;
	}
	
	public void setJornadaSemanal(Integer jornadaSemanal) {
		this.jornadaSemanal = jornadaSemanal;
	}
	
	public Float getRemuneracao() {
		return remuneracao;
	}
	
	public void setRemuneracao(Float remuneracao) {
		this.remuneracao = remuneracao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (idFuncionario == null) {
			if (other.idFuncionario != null)
				return false;
		} else if (!idFuncionario.equals(other.idFuncionario))
			return false;
		return true;
	}
}
