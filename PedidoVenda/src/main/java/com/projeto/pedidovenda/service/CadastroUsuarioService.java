package com.projeto.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto.pedidovenda.model.Usuario;
import com.projeto.pedidovenda.repository.Usuarios;
import com.projeto.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	/**
	 * Metodo que salva apenas se NOME ainda não existe (NOME deve ser único) ou
	 * se for uma edição
	 * 
	 * @param usuario
	 * @return
	 */
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um usuário com o Email informado.");
		}

		return usuarios.guardar(usuario);

	}

}
