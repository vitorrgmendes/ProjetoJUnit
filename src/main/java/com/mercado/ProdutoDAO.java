package com.mercado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO 
{
	public void verificarBancoTabela()
	{
		String nomeTabela = "produto";
		String criarTabela = "CREATE TABLE " + nomeTabela + " (idproduto int NOT NULL," + " nomeproduto varchar(100) NOT NULL," + " precoproduto float NOT NULL," +
                		"PRIMARY KEY (idproduto))";

		try 
		{
			boolean tabelaExiste = false;

			// Verificar existência da Tabela
			Connection conexao = Conexao.getConexao();                  
            Statement statement = conexao.createStatement(); 
            ResultSet rs = statement.executeQuery("SELECT EXISTS (SELECT * FROM pg_tables WHERE schemaname = 'public' AND tablename = 'produto')"); 
            while(rs.next())
			{
				if(rs.getBoolean(1))
				{
					tabelaExiste = true;
				}
			}
			rs.close();


            if (!tabelaExiste) 
			{
				PreparedStatement ps = conexao.prepareStatement(criarTabela);
				ps.execute();
				ps.close();
            }
		} 
		catch (Exception e) 
		{
			System.out.println("Erro: Tabela inexistente.\n" + e.getMessage());
		}
	}
	
	public void cadastrarProduto(Produto produto)
	{
		String SQL = "INSERT INTO PRODUTO (IDPRODUTO, NOMEPRODUTO, PRECOPRODUTO) VALUES (?, ?, ?)";
		
		PreparedStatement ps = null;
		
		try 
		{
			ps = Conexao.getConexao().prepareStatement(SQL);
			
			ps.setInt(1, produto.getIdProduto());
			ps.setString(2, produto.getNomeProduto());
			ps.setDouble(3, produto.getPrecoProduto());
			
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro ao cadastrar produto no Banco de Dados.\nERRO: " + e.getMessage());
		}
	}
	
	public void atualizarPrecoProduto(int idProduto, double precoProduto)
	{
		String SQL = "UPDATE PRODUTO SET PRECOPRODUTO = ? WHERE IDPRODUTO = ?";
		
		PreparedStatement ps = null;
		
		try 
		{
			ps = Conexao.getConexao().prepareStatement(SQL);
			
			ps.setDouble(1, precoProduto);
			ps.setInt(2, idProduto);
			
			
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro ao atualizar preço do produto no Banco de Dados.\nERRO: " + e.getMessage());
		}
	}
	
	public void removerProduto(int idProduto)
	{
		String SQL = "DELETE FROM PRODUTO WHERE IDPRODUTO = ?";
		
		PreparedStatement ps = null;
		
		try 
		{
			ps = Conexao.getConexao().prepareStatement(SQL);
			
			ps.setInt(1, idProduto);
			
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro ao remover o produto no Banco de Dados.\nERRO: " + e.getMessage());
		}
	}
	
	public Produto pesquisarProduto(int idproduto)
	{
		String SQL = "SELECT * FROM PRODUTO WHERE IDPRODUTO = ?";
		
		String nomeProduto = "";
		Double precoProduto = 0.0;
		Produto produtoSelecionado = null;
		
		ResultSet rsProduto = null;		
		PreparedStatement ps = null;
		
		try 
		{
			ps = Conexao.getConexao().prepareStatement(SQL);
			
			ps.setInt(1, idproduto);
			
			rsProduto = ps.executeQuery();
			
			while (rsProduto.next()) 
            {
				nomeProduto = rsProduto.getString("NOMEPRODUTO");
                precoProduto = rsProduto.getDouble("PRECOPRODUTO");
				produtoSelecionado = new Produto(idproduto, nomeProduto, precoProduto);                
            }			
            
			ps.close();
			
            return produtoSelecionado;			
		} 
		catch (SQLException e) 
		{
			System.out.println("Produto não cadastrado no Banco de Dados.");
			return null;
		}
	}
	
	public List<Integer> listarProdutos()
	{
		String SQL = "SELECT * FROM PRODUTO";
		
		int idProduto = 0;
		String nomeProduto = "";
		Double precoProduto = 0.0;
		
		List<Integer> listaProduto = new ArrayList<Integer>();
		ResultSet rsProduto = null;
		
		PreparedStatement ps = null;
		
		try 
		{
			ps = Conexao.getConexao().prepareStatement(SQL);
			
			rsProduto = ps.executeQuery();
			
			while (rsProduto.next()) 
            {
				idProduto = rsProduto.getInt("IDPRODUTO");
				nomeProduto = rsProduto.getString("NOMEPRODUTO");
                precoProduto = rsProduto.getDouble("PRECOPRODUTO");
                
                Produto produto = new Produto(idProduto, nomeProduto, precoProduto);
                listaProduto.add(produto.getIdProduto());
            }
            
			ps.close();
			
            return listaProduto;			
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro ao listar produtos do Banco de Dados.\nERRO: " + e.getMessage());
			return null;
		}
	}

	public List<Produto> listarProduto()
	{
		String SQL = "SELECT * FROM PRODUTO";
		
		int idProduto = 0;
		String nomeProduto = "";
		Double precoProduto = 0.0;
		
		List<Produto> listaProduto = new ArrayList<Produto>();
		ResultSet rsProduto = null;
		
		PreparedStatement ps = null;
		
		try 
		{
			ps = Conexao.getConexao().prepareStatement(SQL);
			
			rsProduto = ps.executeQuery();
			
			while (rsProduto.next()) 
            {
				idProduto = rsProduto.getInt("IDPRODUTO");
				nomeProduto = rsProduto.getString("NOMEPRODUTO");
                precoProduto = rsProduto.getDouble("PRECOPRODUTO");
                
                Produto produto = new Produto(idProduto, nomeProduto, precoProduto);
                listaProduto.add(produto);
            }
            
			ps.close();
			
            return listaProduto;			
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro ao listar produtos do Banco de Dados.\nERRO: " + e.getMessage());
			return null;
		}
	}

}
