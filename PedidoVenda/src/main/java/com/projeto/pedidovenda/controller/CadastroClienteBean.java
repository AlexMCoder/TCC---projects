package com.projeto.pedidovenda.controller;
 
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Cliente;
import com.projeto.pedidovenda.model.Endereco;
import com.projeto.pedidovenda.model.Usuario;
import com.projeto.pedidovenda.service.CadastroClienteService;
import com.projeto.util.jsf.FacesUtil;

/**
 * @author cqfb
 */

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroClienteService cadastroClienteService;
	
	private Endereco endereco;
	
	private Cliente cliente;
	
	private Endereco enderecoSelecionado;
	
	public CadastroClienteBean() {
		limpar();
	}
	
	private void limpar() {
		cliente = new Cliente();
		endereco = new Endereco();
	}
	
	public void inicializar() {
        if (cliente == null) {
            limpar();
        }  
    }
	
	public void salvar() {
		this.cliente = cadastroClienteService.salvar(this.cliente);
		//limpar();
		
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	public void adicionarEndereco() {
		endereco.setCliente(cliente);
		
		if (endereco.getId() == null){
			cliente.getEnderecos().add(this.endereco);
			FacesUtil.addWarnMessage("Novo endereço associado ao cliente. SALVE PARA COMPLETAR A AÇÃO!");
		
		} else {
			FacesUtil.addWarnMessage("Endereço alterado. SALVE PARA COMPLETAR A ALTERAÇÃO!");
		}
		
		this.endereco = new Endereco();
	}
	
	public void removerEndereco() {
		this.cliente.getEnderecos().remove(enderecoSelecionado);
		
		FacesUtil.addWarnMessage("Endereco excluído. SALVE O CLIENTE PARA COMPLETAR A AÇÃO!");
			
		this.enderecoSelecionado = new Endereco();
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
		/*if (this.cliente != null){
		this.enderecos = this.usuario.getEndereco();
	}*/
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
		this.endereco = enderecoSelecionado;
	}

	public boolean isEditando() {
		return this.cliente.getId() != null;
	}
	
}