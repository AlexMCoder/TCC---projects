package com.projeto.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Produto;
import com.projeto.pedidovenda.repository.Produtos;
import com.projeto.pedidovenda.repository.filter.ProdutoFilter;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	
	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}
	
}