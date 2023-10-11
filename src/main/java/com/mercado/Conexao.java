package com.mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao 
{
	private static final String url = "jdbc:postgresql://localhost:5432/mercado";
	private static final String user = "postgres";
	private static final String password = "132022";
	
	private static Connection conexao;
	
	public static Connection getConexao() 
	{		
		try 
		{
			if (conexao == null)
			{
				conexao = DriverManager.getConnection(url, user, password);
				return conexao;
			}
			else
			{
				return conexao;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro na conexão com o Banco de Dados.\nERRO: " + e.getMessage());
			return null;
		}
	}

}
