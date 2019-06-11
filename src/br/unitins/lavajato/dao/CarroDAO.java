package br.unitins.lavajato.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.lavajato.application.Util;
import br.unitins.lavajato.model.Carro;
import br.unitins.lavajato.model.Categoria;
import br.unitins.lavajato.model.Marca;

public class CarroDAO extends DAO<Carro>  {
	
	@Override
	public boolean create(Carro obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO carro ( "
										+ "  placa, "
										+ "  categoria, "
										+ "  modelo, "
										+ "  marca ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ? ) ");
			stat.setString(1, obj.getPlaca());
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
	public boolean update(Carro obj) {
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
			stat.setString(1, obj.getPlaca());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setString(3, obj.getModelo());
			stat.setInt(4, obj.getMarca().getValue());
			stat.setInt(5, obj.getId());
			
			stat.execute();
			Util.addMessageError("Alteração realizada com sucesso!");
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
			Util.addMessageError("Exclusão realizada com sucesso!");
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
	public Carro findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Carro carro = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Carro WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				carro = new Carro();
				carro.setId(rs.getInt("id"));
				carro.setPlaca(rs.getString("placa"));
				carro.setModelo(rs.getString("modelo"));
				carro.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
				carro.setMarca(Marca.valueOf(rs.getInt("marca")));
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
		return carro;
	}

	@Override
	public List<Carro> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Carro> listaCarro = new ArrayList<Carro>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Carro");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Carro c = new Carro();
				c.setId(rs.getInt("id"));
				c.setPlaca(rs.getString("placa"));
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
