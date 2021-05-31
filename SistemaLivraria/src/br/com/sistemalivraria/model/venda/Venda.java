package br.com.sistemalivraria.model.venda;

import java.time.LocalDate;
import java.util.List;

import br.com.sistemalivraria.model.cliente.Cliente;
import br.com.sistemalivraria.model.livro.Livro;

public class Venda {
	
	private final Long numeroDaVenda;
	private final LocalDate dataCompra;
	private final List<Livro> produtos;
	private final Cliente cliente;
	
	public Venda(Long numeroDaVenda, List<Livro> produtos, Cliente cliente) {
		this.numeroDaVenda = numeroDaVenda;
		this.dataCompra = LocalDate.now();
		this.produtos = produtos;
		this.cliente = cliente;
	}

	public Long getNumeroDaVenda() {
		return numeroDaVenda;
	}
	
	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public List<Livro> getProdutos() {
		return produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return "Venda: " + numeroDaVenda + " - Data: " + dataCompra + " - Cliente: " + cliente.getNome();
	}
}
