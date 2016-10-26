/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.pedidovenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable{
        
        private static final long serialVersionUID = 1L;
    
	private List<Integer> pedidosFiltrados;
	
	public PesquisaUsuarioBean() {
		pedidosFiltrados = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			pedidosFiltrados.add(i);
		}
	}

	public List<Integer> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	
}
