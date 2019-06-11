package br.unitins.lojabike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojabike.dao.BikeDAO;
import br.unitins.lojabike.model.Bike;
import br.unitins.lojabike.model.Categoria;
import br.unitins.lojabike.model.Marca;

public class BikeController implements Serializable {
	

	@Named
	@ViewScoped
	public class CarroController implements Serializable{

		private static final long serialVersionUID = 2602034636682098082L;
		
		private Bike bike;
		
		private List<Bike> listaBike = null;
		
		public List<Bike> getListaCarro(){
			if (listaBike == null) {
				BikeDAO dao = new BikeDAO();
				listaBike = dao.findAll();
				if (listaBike == null)
					listaBike = new ArrayList<Bike>();
				dao.closeConnection();
			}
			
			return listaBike;
		}
		
		public void editar(int id) {
			BikeDAO dao = new BikeDAO();
			setBike(dao.findById(id));
		}
		
		
		public void incluir() {
			BikeDAO dao = new BikeDAO();

			if (dao.create(getBike())) {
				limpar();
				// para atualizar o data table
				listaBike = null;
			}
			dao.closeConnection();
		}
		
		public void alterar() {
			BikeDAO dao = new BikeDAO();
			if (dao.update(getBike())) {
				limpar();
				// para atualizar o data table
				listaBike = null;
			}
			dao.closeConnection();
		}
		
		public void excluir() {
			BikeDAO dao = new BikeDAO();
			if (dao.delete(getBike().getId())) {
				limpar();
				// para atualizar o data table
				listaBike = null;
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
			bike = null;
		}
		

		public Bike getBike() {
			if (bike == null) {
				bike = new Bike();
			}
			
			return bike;
		}

		public void setBike(Bike carro) {
			this.bike = carro;
		}
		
	}


}
