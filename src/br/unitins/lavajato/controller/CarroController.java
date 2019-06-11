package br.unitins.lavajato.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lavajato.dao.CarroDAO;
import br.unitins.lavajato.model.Carro;
import br.unitins.lavajato.model.Categoria;
import br.unitins.lavajato.model.Marca;

@Named
@ViewScoped
public class CarroController implements Serializable{

	private static final long serialVersionUID = 2602034636682098082L;
	
	private Carro carro;
	
	private List<Carro> listaCarro = null;
	
	public List<Carro> getListaCarro(){
		if (listaCarro == null) {
			CarroDAO dao = new CarroDAO();
			listaCarro = dao.findAll();
			if (listaCarro == null)
				listaCarro = new ArrayList<Carro>();
			dao.closeConnection();
		}
		
		return listaCarro;
	}
	
	public void editar(int id) {
		CarroDAO dao = new CarroDAO();
		setCarro(dao.findById(id));
	}
	
	
	public void incluir() {
		CarroDAO dao = new CarroDAO();

		if (dao.create(getCarro())) {
			limpar();
			// para atualizar o data table
			listaCarro = null;
		}
		dao.closeConnection();
	}
	
	public void alterar() {
		CarroDAO dao = new CarroDAO();
		if (dao.update(getCarro())) {
			limpar();
			// para atualizar o data table
			listaCarro = null;
		}
		dao.closeConnection();
	}
	
	public void excluir() {
		CarroDAO dao = new CarroDAO();
		if (dao.delete(getCarro().getId())) {
			limpar();
			// para atualizar o data table
			listaCarro = null;
		}
		dao.closeConnection();
	}
	
	public Marca[] getListaMarca() {
		return Marca.values();
	}
	
	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}
	
	public void limpar() {
		carro = null;
	}
	

	public Carro getCarro() {
		if (carro == null) {
			carro = new Carro();
		}
		
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
}
