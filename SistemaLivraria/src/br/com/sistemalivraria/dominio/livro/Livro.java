package br.com.sistemalivraria.dominio.livro;

import java.time.LocalDate;

public class Livro {
	
	private Long isbn;
	private String titulo;
	private String autor;
	private String editora;
	private LocalDate lancamento;
	private Float valor;
	private Integer qtdeExemplares;
	
	public Livro() {}
	
	public Livro(Long isbn, String titulo, String autor, String editora, LocalDate lancamento, Float valor,
			Integer qtdeExemplares) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.lancamento = lancamento;
		this.valor = valor;
		this.qtdeExemplares = qtdeExemplares;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public void setQtdeExemplares(Integer qtdeExemplares) {
		this.qtdeExemplares = qtdeExemplares;
	}

	public Long getIsbn() {
		return isbn;
	}
		
	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getEditora() {
		return editora;
	}
		
	public Float getValor() {
		return valor;
	}
		
	public Integer getQtdeExemplares() {
		return qtdeExemplares;
	}
	
	public LocalDate getLancamento() {
		return lancamento;
	}

}
