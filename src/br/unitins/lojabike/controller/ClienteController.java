package br.unitins.lojabike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.unitins.lojabike.application.Util;
import br.unitins.lojabike.dao.ClienteDAO;
import br.unitins.lojabike.model.Cliente;
import br.unitins.lojabike.model.Perfil;

public class ClienteController implements Serializable {

		private static final long serialVersionUID = 1646118458024979829L;

		private Cliente cliente;
		
		private List<Cliente> listaCliente = null;
		
		public ClienteController() {
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			cliente = (Cliente) flash.get("clienteFlash");
		}
		
		public List<Cliente> getListacliente(){
			if (listaCliente == null) {
				ClienteDAO dao = new ClienteDAO();
				listaCliente = dao.findAll();
				if (listaCliente == null)
					listaCliente = new ArrayList<Cliente>();
				dao.closeConnection();
			}
			
			return listaCliente;
		}
		
		public void editar(int id) {
			ClienteDAO dao = new ClienteDAO();
			setCliente(dao.findById(id));
		}
		
		
		public void incluir() {
			// encriptando a senha do cliente
			getCliente().setSenha(Util.encrypt(getCliente().getSenha()));
			
			ClienteDAO dao = new ClienteDAO();
			if (dao.create(getCliente())) {
				limpar();
				// para atualizar o data table
				listaCliente = null;
			}
			dao.closeConnection();
		}
		
		public void alterar() {
			// encriptando a senha do cliente
			getCliente().setSenha(Util.encrypt(getCliente().getSenha()));
			
			ClienteDAO dao = new ClienteDAO();
			if (dao.update(getCliente())) {
				limpar();
				// para atualizar o data table
				listaCliente = null;
			}
			dao.closeConnection();
		}
		
		public void excluir() {
			ClienteDAO dao = new ClienteDAO();
			if (dao.delete(getCliente().getId())) {
				limpar();
				// para atualizar o data table
				listaCliente = null;
			}
			dao.closeConnection();
		}
		
		public Perfil[] getListaPerfil() {
			return Perfil.values();
		}
		
		public void limpar() {
			cliente = null;
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



