package br.unitins.lavajato.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lavajato.application.Session;
import br.unitins.lavajato.model.Usuario;

@Named
@ViewScoped
public class MenuController implements Serializable {
	
	private static final long serialVersionUID = -3540211319186607809L;
	
	Usuario usuarioLogado = null;
	
	public MenuController() {
		usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
}
