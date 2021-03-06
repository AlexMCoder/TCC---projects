/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.pedidovenda.repository;

import com.projeto.pedidovenda.model.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author alex
 */
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Categoria porId(Long id) {
        return manager.find(Categoria.class, id);
    }

	//Query JPQL (Java Persistence Query Language) que retorna objetos e seus atributos (não colunas)
    public List<Categoria> raizes() {
        return manager.createQuery("from Categoria where categoriaPai is null",
                Categoria.class).getResultList();
    }

    public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
        return manager.createQuery("from Categoria where categoriaPai = :raiz",
                Categoria.class).setParameter("raiz", categoriaPai).getResultList();
    }

}
