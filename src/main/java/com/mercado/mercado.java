package com.mercado;

import java.util.List;
import java.util.Scanner;

public class mercado 
{

	public static void main(String[] args)
	{
		int operacao;
        int id;
        String nome;
        double preco;
        
        ProdutoDAO OPERACAO = new ProdutoDAO();

        OPERACAO.verificarBancoTabela();

        // Nome do programa
        System.out.println("MERCADO");

        Scanner scanner = new Scanner(System.in);

        // Pedindo as entradas
        System.out.println(" ");
        System.out.print("OPERAÇÃO A SER REALIZADA: " +
                        "\n1 - Cadastrar Produto."+
                        "\n2 - Atualizar Produto."+
                        "\n3 - Remover Produto."+
                        "\n4 - Listar Produtos."+
                        "\n5 - Pesquisar Produto.\n");
        operacao = scanner.nextInt();

        if (operacao == 1)
        {
            // Adicionar um produto
        	System.out.print("ID DO PRODUTO: ");
            id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("NOME DO PRODUTO: ");
            nome = scanner.nextLine();

            System.out.print("PRECO DO PRODUTO: ");
            preco = scanner.nextDouble();
            
            Produto produto = new Produto(id, nome, preco);
            
            OPERACAO.cadastrarProduto(produto);
            System.out.println("Produto cadastrado!");
        }
        else if (operacao == 2) 
        {
            // Atualizar preço de um produto
        	System.out.print("ID DO PRODUTO: ");
            id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("PRECO DO PRODUTO: ");
            preco = scanner.nextDouble();
            
            OPERACAO.atualizarPrecoProduto(id, preco);  
            
            System.out.println("Preço do produto atualizado!");
        }
        else if (operacao == 3) 
        {
            // Remover produto
        	System.out.print("ID DO PRODUTO: ");
            id = scanner.nextInt();
            
            OPERACAO.removerProduto(id); 
            System.out.println("Produto removido!");
        }
        else if (operacao == 4) 
        {

        	// Listar todos os produtos
            List<Produto> produtos = OPERACAO.listarProduto();

            if (produtos.isEmpty())
            {
                System.out.println("Não há produtos cadastrados!");
            }
            else
            {
                for (Produto produto : produtos) 
                {
                    System.out.println("ID: " + produto.getIdProduto());
                    System.out.println("Nome: " + produto.getNomeProduto());
                    System.out.println("Preço: " + produto.getPrecoProduto());
                    System.out.println("============================================");
                }
            }
            
        }
        else if (operacao == 5) 
        {
            // Pesquisar produto
        	System.out.print("ID DO PRODUTO: ");
            id = scanner.nextInt();            
        	
            Produto produto = OPERACAO.pesquisarProduto(id);

            if (produto != null)
            {
                System.out.println("ID: " + produto.getIdProduto());
                System.out.println("Nome: " + produto.getNomeProduto());
                System.out.println("Preço: " + produto.getPrecoProduto());
            }
            else
            {
                System.out.println("Produto não cadastrado!");
            }
        }

        scanner.close();
	}

}
