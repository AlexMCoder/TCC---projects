package com.projeto.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto.pedidovenda.model.Usuario;
import com.projeto.pedidovenda.repository.Usuarios;
import com.projeto.util.cdi.CDIServiceLocator;

/**
 * @author alex
 * Um converter é uma classe que implementa a interface javax.faces.convert.Converter, 
 * implementando os dois métodos desta interface, o getAsObject e o getAsString.
 */

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	//@Inject (não funciona em conversores para essa versão)
	private Usuarios usuarios;
	
	public UsuarioConverter() {
		usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = usuarios.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		
		return "";
	}

}
