package com.projeto.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Cliente;
import com.projeto.pedidovenda.repository.Clientes;
import com.projeto.pedidovenda.repository.filter.ClienteFilter;
import com.projeto.util.jsf.FacesUtil;

/**
 * @author alex
 */

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	private ClienteFilter filtro;

	private List<Cliente> clientesFiltrados;

	private Cliente clienteSelecionado;

	public PesquisaClienteBean() {
		filtro = new ClienteFilter();
	}

	public void excluir() {
		clientes.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
	}

	public void pesquisar() {
		clientesFiltrados = clientes.filtrados(filtro);
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}