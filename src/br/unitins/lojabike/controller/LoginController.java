package br.unitins.lojabike.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.lojabike.application.Session;
import br.unitins.lojabike.application.Util;
import br.unitins.lojabike.dao.ClienteDAO;
import br.unitins.lojabike.model.Cliente;

@Named
@RequestScoped
public class LoginController {

	private Cliente cliente;
	
	public void entrar() {
		ClienteDAO dao = new ClienteDAO();
		// gerando o hash da senha informada na tela de login
		String senhaEncriptada = Util.encrypt(getCliente().getSenha());
		
		Cliente cliLogado = dao.findCliente(getCliente().getLogin(), senhaEncriptada);
		
		// comparando os dados da tela de login com o banco de dados
		if (cliLogado != null) {
			Session.getInstance().setAttribute("usuarioLogado", cliLogado);
			// login valido
			Util.redirect("menu.xhtml");
		} else 
			Util.addMessageError("Usu�rio ou senha inv�lido.");
		
	}
	
	public void limpar() {
		setCliente(null);
	}

	public Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
