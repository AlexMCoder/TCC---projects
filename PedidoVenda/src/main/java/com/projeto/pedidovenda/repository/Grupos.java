package com.projeto.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projeto.pedidovenda.model.Grupo;

public class Grupos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}

	//  
	/**
	 * Metodo com Query JPQL (Java Persistence Query Language)
	 * @return lista de Grupo (não retorna colunas mas sim atributos de classe)
	 */
	public List<Grupo> listarGrupos() {
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
}
