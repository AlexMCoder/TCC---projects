package com.projeto.pedidovenda.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import com.projeto.util.jsf.FacesUtil;
import com.projeto.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioProdutosEmitidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private double valorInicio;
	private double valorFim;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("valorU_inicio", this.valorInicio);
		parametros.put("valorU_fim", this.valorFim);
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_produtos_emitidos.jasper",
				this.response, parametros, "Produtos emitidos.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

	
	

	public void setValorInicio(double dataInicio) {
		this.valorInicio = dataInicio;
	}

	@NotNull
	public double getValorInicio() {
		return valorInicio;
	}

	@NotNull
	public double getValorFim() {
		return valorFim;
	}

	public void setValorFim(double dataFim) {
		this.valorFim = dataFim;
	}

}
