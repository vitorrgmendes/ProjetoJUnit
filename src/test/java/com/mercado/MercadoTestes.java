package com.mercado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestMethodOrder(OrderAnnotation.class)
public class MercadoTestes 
{
    @BeforeAll
    static void verificarTabela()
    {
        ProdutoDAO OPERACAO = new ProdutoDAO();
        OPERACAO.verificarBancoTabela();
    }

    @ParameterizedTest
    @Order(1)
    @CsvFileSource(resources = "./CadastrarTeste.csv", numLinesToSkip = 1) //pular linha de cabeçalho
    void testCadastrar(int id, String nome, double preco) 
    {
        ProdutoDAO OPERACAO = new ProdutoDAO();

        Produto produto = new Produto(id, nome, preco);
        OPERACAO.cadastrarProduto(produto);
        Produto produtoPesquisado = OPERACAO.pesquisarProduto(id);
        assertEquals(produto.getIdProduto(), produtoPesquisado.getIdProduto());
    }

    @ParameterizedTest
    @Order(2)
    @CsvFileSource(resources = "./ListarTeste.csv", numLinesToSkip = 1) //pular linha de cabeçalho
    void testListar(int id) 
    {
        ProdutoDAO OPERACAO = new ProdutoDAO();
        List<Integer> produtos = OPERACAO.listarProdutos();
        Produto produto = OPERACAO.pesquisarProduto(id);

        assertTrue(produtos.contains(produto.getIdProduto()));
    }

    @ParameterizedTest
    @Order(3)
    @CsvFileSource(resources = "./PesquisarTeste.csv", numLinesToSkip = 1) //pular linha de cabeçalho
    void testPesquisar(int id) 
    {
        ProdutoDAO OPERACAO = new ProdutoDAO();
        Produto produto = OPERACAO.pesquisarProduto(id);

        assertEquals(id, produto.getIdProduto());
    }

    @ParameterizedTest
    @Order(4)
    @CsvFileSource(resources = "./AtualizarTeste.csv", numLinesToSkip = 1) //pular linha de cabeçalho
    void testAtualizarPreco(int id, double preco) 
    {
        ProdutoDAO OPERACAO = new ProdutoDAO();
        OPERACAO.atualizarPrecoProduto(id, preco);
        Produto produto = OPERACAO.pesquisarProduto(id);
        assertEquals(preco, produto.getPrecoProduto());
    }

    @ParameterizedTest
    @Order(5)
    @CsvFileSource(resources = "./RemoverTeste.csv", numLinesToSkip = 1) //pular linha de cabeçalho
    void testRemover(int id) 
    {
        ProdutoDAO OPERACAO = new ProdutoDAO();
        OPERACAO.removerProduto(id);
        assertEquals(null, OPERACAO.pesquisarProduto(id));
    }
}
