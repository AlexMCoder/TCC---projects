package com.projeto.pedidovenda;

import com.projeto.pedidovenda.model.Categoria;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Produto;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	private Produto produto;
	
	private List<Categoria> categoriasRaizes;
	
	public CadastroProdutoBean() {
		produto = new Produto();
	}
	
	public void inicializar() {
		System.out.println("Inicializando...");
		
		categoriasRaizes = manager.createQuery("from Categoria", Categoria.class).getResultList();
	}
	
	public void salvar() {
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

}
