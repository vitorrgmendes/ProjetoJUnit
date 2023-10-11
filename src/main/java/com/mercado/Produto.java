package com.mercado;

public class Produto 
{
	private int idProduto;
    private String nomeProduto;
    private double precoProduto;

    public Produto(int id, String nome, double preco)
    {   
        this.idProduto = id;
        this.nomeProduto = nome;
        this.precoProduto = preco;
    }

    public int getIdProduto() 
    {
        return idProduto;
    }

    public String getNomeProduto() 
    {
        return nomeProduto;
    }

    public double getPrecoProduto() 
    {
        return precoProduto;
    }
}
