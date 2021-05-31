package br.com.sistemalivraria.model.usuario;

public class Usuario {
	
	protected Long id;
	protected String nome;
	protected String senha;
	
	public Usuario(Long id, String nome, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}
	
	public void consultarLivro() {};
}
