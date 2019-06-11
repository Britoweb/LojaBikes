package br.unitins.lojabike.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.lojabike.application.Util;
import br.unitins.lojabike.model.Cliente;
import br.unitins.lojabike.model.Perfil;

public class ClienteDAO extends DAO<Cliente>{
	
	
	public Cliente findCliente(String login, String senha) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Cliente cliente = null;
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Cliente WHERE login = ? AND senha = ? ");
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				cliente.setDataNascimento(rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));
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
		return cliente;		
	}

	@Override
	public boolean create(Cliente obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO Cliente ( "
										+ " nome, "
										+ " login, "
										+ " senha, "
										+ " perfil,"
										+ " datanascimento ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?,"
										+ " ? ) ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getLogin());
			stat.setString(3, obj.getSenha());			
			stat.setInt(4, obj.getPerfil().getValue());
			stat.setDate(5, (obj.getDataNascimento() == null ? null : java.sql.Date.valueOf(obj.getDataNascimento())));
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
	public boolean update(Cliente obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE Cliente SET "
												   + "  nome = ?, "
												   + "  login = ?, "
												   + "  senha = ?, "
												   + "  perfil = ?,  "
												   + "  datanascimento = ?  "												   
												   + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getLogin());
			stat.setString(3, obj.getSenha());
			stat.setInt(4, obj.getPerfil().getValue());
			stat.setDate(5, (obj.getDataNascimento() == null ? null : java.sql.Date.valueOf(obj.getDataNascimento())));
			stat.setInt(6, obj.getId());
			
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
			stat =	getConnection().prepareStatement("DELETE FROM Cliente WHERE id = ? ");
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
	public Cliente findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Cliente cliente = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Cliente WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				cliente.setDataNascimento(rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));
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
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Cliente");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Cliente Cliente = new Cliente();
				Cliente.setId(rs.getInt("id"));
				Cliente.setNome(rs.getString("nome"));
				Cliente.setLogin(rs.getString("login"));
				Cliente.setSenha(rs.getString("senha"));
				Cliente.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				Cliente.setDataNascimento(rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));

				listaCliente.add(Cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaCliente = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCliente;

	}
	
	public List<Cliente> findByNome(String nome) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Cliente WHERE nome ILIKE ?");
			stat.setString(1, (nome == null? "%" : "%"+nome+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Cliente Cliente = new Cliente();
				Cliente.setId(rs.getInt("id"));
				Cliente.setNome(rs.getString("nome"));
				Cliente.setLogin(rs.getString("login"));
				Cliente.setSenha(rs.getString("senha"));
				Cliente.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				Cliente.setDataNascimento(rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));

				listaCliente.add(Cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaCliente = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCliente;

	}

	
	
}
