
package br.unitins.lojabike.model;

import java.time.LocalDate;

public class Cliente {

	private Integer id;

//		@NotBlank(message="O nome deve ser informado.")
	private String nome;

//		@Email(message="Email inv�lido.")
	private String login;

//		@Size(min=6, max=20, message="Tamanho incompativel, valor m�nimo: 6 e valor maximo:20.")
	private String senha;
	private Perfil perfil;
	private LocalDate dataNascimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
