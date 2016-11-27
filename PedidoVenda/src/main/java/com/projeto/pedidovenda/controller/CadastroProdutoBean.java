package com.projeto.pedidovenda.controller;

import com.projeto.pedidovenda.model.Categoria;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Produto;
import com.projeto.pedidovenda.repository.Categorias;
import com.projeto.pedidovenda.service.CadastroProdutoService;
import com.projeto.util.jsf.FacesUtil;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	@Inject
	private CadastroProdutoService cadastroProdutoService;

	private Produto produto;
	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;

	public CadastroProdutoBean() {
		limpar();
	}

	@PostConstruct
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			categoriasRaizes = categorias.raizes();
		}
	}

	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}

	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();

		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

}
