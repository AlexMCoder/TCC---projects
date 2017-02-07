package com.projeto.pedidovenda.controller;

import com.projeto.pedidovenda.model.Categoria;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Produto;
import com.projeto.pedidovenda.repository.Categorias;
import com.projeto.pedidovenda.service.CadastroProdutoService;
import com.projeto.pedidovenda.service.NegocioException;
import com.projeto.util.jsf.FacesUtil;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
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

	public void inicializar() {
		if (this.produto == null) {
			limpar();
		}

		categoriasRaizes = categorias.raizes();

		if (this.categoriaPai != null) {
			carregarSubcategorias();
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
		try {
			this.produto = cadastroProdutoService.salvar(this.produto);
			limpar();

			FacesUtil.addInfoMessage("Produto salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;

		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
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

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

}
