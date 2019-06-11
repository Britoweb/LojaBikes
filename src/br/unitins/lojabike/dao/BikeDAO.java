package br.unitins.lojabike.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.lojabike.application.Util;
import br.unitins.lojabike.model.Bike;
import br.unitins.lojabike.model.Categoria;
import br.unitins.lojabike.model.Marca;

public class BikeDAO extends DAO<Bike>  {
	
	@Override
	public boolean create(Bike obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO bike ( "
										+ "  nome, "
										+ "  categoria, "
										+ "  modelo, "
										+ "  marca ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ? ) ");
			stat.setString(1, obj.getNome());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setString(3, obj.getModelo());
			stat.setInt(4, obj.getMarca().getValue());
			
			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public boolean update(Bike obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE carro SET "
												   + "  placa = ?, "
												   + "  categoria = ?, "
												   + "  modelo = ?, "
												   + "  marca = ?  " 
												   + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setString(3, obj.getModelo());
			stat.setInt(4, obj.getMarca().getValue());
			stat.setInt(5, obj.getId());
			
			stat.execute();
			Util.addMessageError("Altera��o realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Alterar.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
		
	}

	@Override
	public boolean delete(int id) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("DELETE FROM carro WHERE id = ? ");
			stat.setInt(1, id);
			
			stat.execute();
			Util.addMessageError("Exclus�o realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Excluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public Bike findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Bike bike = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Carro WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setNome(rs.getString("nome"));
				bike.setModelo(rs.getString("modelo"));
				bike.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
				bike.setMarca(Marca.valueOf(rs.getInt("marca")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bike;
	}

	@Override
	public List<Bike> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Bike> listaCarro = new ArrayList<Bike>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Carro");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Bike c = new Bike();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setModelo(rs.getString("modelo"));
				c.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
				c.setMarca(Marca.valueOf(rs.getInt("marca")));

				listaCarro.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaCarro = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCarro;
	}
	

}
