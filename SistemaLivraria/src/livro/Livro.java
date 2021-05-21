package livro;

public class Livro {
	
	private Long id;
	private Long isbn;
	private String titulo;
	private String autor;
	private String editora;
	private Double valor;
	private Integer qtdeExemplares;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIsbn() {
		return isbn;
	}
	
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Integer getQtdeExemplares() {
		return qtdeExemplares;
	}
	
	public void setQtdeExemplares(Integer qtdeExemplares) {
		this.qtdeExemplares = qtdeExemplares;
	}
}
