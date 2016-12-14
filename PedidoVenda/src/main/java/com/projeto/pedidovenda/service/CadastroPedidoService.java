package com.projeto.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.projeto.pedidovenda.model.Pedido;
import com.projeto.pedidovenda.model.StatusPedido;
import com.projeto.pedidovenda.repository.Pedidos;
import com.projeto.util.jpa.Transactional;

/**
 * @author alex
 *
 */
public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;

	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}

		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}

}