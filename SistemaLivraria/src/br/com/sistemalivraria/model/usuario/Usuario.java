package br.com.sistemalivraria.model.usuario;

public class Usuario {
	
	protected Long id;
	protected String nome;
	protected String senha;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
