package com.projeto.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto.pedidovenda.model.ItemPedido;
import com.projeto.pedidovenda.model.Pedido;
import com.projeto.pedidovenda.repository.Pedidos;
import com.projeto.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}
	
}
