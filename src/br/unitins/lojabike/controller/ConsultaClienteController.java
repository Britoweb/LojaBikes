package br.unitins.lojabike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.unitins.lojabike.application.Util;
import br.unitins.lojabike.dao.ClienteDAO;
import br.unitins.lojabike.model.Cliente;

public class ConsultaClienteController implements Serializable{
	
	private static final long serialVersionUID = 1646118458024979823L;

	private String nome;
	
	private List<Cliente> listaCliente = null;
	
	public List<Cliente> getListaCliente(){
		if (listaCliente == null) {
			ClienteDAO dao = new ClienteDAO();
			listaCliente = dao.findByNome(getNome());
			if (listaCliente == null)
				listaCliente = new ArrayList<Cliente>();
			dao.closeConnection();
		}
		
		return listaCliente;
	}
	
	public void pesquisar() {
		listaCliente = null;
	}
	
	public void editar(int id) {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("clienteFlash", cliente);
		Util.redirect("usuario.xhtml");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
