package com.sandropastelaria.omniorder.config;
import java.sql.DriverManager;
import java.sql.Connection;

public class FabricaDeConexao {
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/sandro_pastelaria?serverTimezone=UTC", "root", "");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		FabricaDeConexao.getConnection();
	}
}
