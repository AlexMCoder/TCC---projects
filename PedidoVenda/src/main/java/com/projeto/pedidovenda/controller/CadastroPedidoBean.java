/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.pedidovenda.controller;

import java.io.Serializable;

import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Cliente;
import com.projeto.pedidovenda.model.EnderecoEntrega;
import com.projeto.pedidovenda.model.FormaPagamento;
import com.projeto.pedidovenda.model.Pedido;
import com.projeto.pedidovenda.model.Usuario;
import com.projeto.pedidovenda.repository.Clientes;
import com.projeto.pedidovenda.repository.Usuarios;
import com.projeto.pedidovenda.service.CadastroPedidoService;
import com.projeto.util.jsf.FacesUtil;

/**
 * @author Alex
 * 
 */

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	
	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.vendedores = this.usuarios.vendedores();
		}
	}
	
	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void salvar() {
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return this.clientes.porNome(nome);
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

}