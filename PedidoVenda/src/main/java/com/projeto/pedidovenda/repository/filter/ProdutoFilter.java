package com.projeto.pedidovenda.repository.filter;

import java.io.Serializable;

import validation.SKU;

public class ProdutoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;
	private String nome;

	@SKU
	public String getSku() {
		return sku;
	}

	/**
	 * Metodo com operador condicional ternário
	 * @param sku
	 */
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
