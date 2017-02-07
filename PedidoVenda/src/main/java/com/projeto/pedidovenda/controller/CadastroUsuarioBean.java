/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto.pedidovenda.model.Grupo;
import com.projeto.pedidovenda.model.Usuario;
import com.projeto.pedidovenda.repository.Grupos;
import com.projeto.pedidovenda.service.CadastroUsuarioService;
import com.projeto.pedidovenda.service.NegocioException;
import com.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	private Grupos grupos;

	private Usuario usuario;
	private Grupo grupo;
	private Grupo grupoSelecionado;

	private List<Grupo> listaGrupos;

	public CadastroUsuarioBean() {
		limpar();
	}

	public void inicializar() {
		// if (FacesUtil.isNotPostback()) {
		listaGrupos = grupos.listarGrupos();

		// }

	}

	private void limpar() {
		usuario = new Usuario();
		grupoSelecionado = null;
		grupo = null;
		listaGrupos = new ArrayList<>();
	}

	public void salvar() {
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		limpar();

		FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
	}

	public void adicionarGrupo() {
		if (grupoSelecionado != null) {
			for (Grupo grupo : this.usuario.getGrupos()) {
				if (grupoSelecionado.equals(grupo)) {
					throw new NegocioException("Este grupo já foi adicionado");
				}
			}
			this.usuario.getGrupos().add(grupoSelecionado);
			FacesUtil.addInfoMessage("Grupo adicionado: " + this.grupoSelecionado.getDescricao());
		}

	}

	public void removerGrupo() {
		this.usuario.getGrupos().remove(grupoSelecionado);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

		/*
		 * if (this.usuario != null){ this.grupo = this.usuario.getGrupos(); }
		 */
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

}
