package br.com.sistemalivraria.controller.livro;

import java.util.ArrayList;
import java.util.List;

import br.com.sistemalivraria.model.livro.Livro;

public class LivroControl {

	private List<Livro> livros = new ArrayList<Livro>();

	public void adicionar(Livro livro) {
		livros.add(livro);
	}

	public Livro pesquisar(Long isbn, String titulo) {

		Livro pesquisa = null;

		if(isbn != null) {
			for (Livro livro : livros) {
				if (livro.getIsbn() == isbn) {
					pesquisa = livro;
				}
			}
		}	
		
		if(titulo != null) {
			for (Livro livro : livros) {
				if (livro.getTitulo().contains(titulo)) {
				pesquisa = livro;
				}
			}
		}
		
		return pesquisa;
	}

	public Livro alterarDados(Livro livroPesquisado) {

		try {

			for (Livro livro : livros) {
				if (livro.getIsbn().equals(livroPesquisado.getIsbn()) || livro.getTitulo().contains(livroPesquisado.getTitulo())) {

					if (livroPesquisado.getIsbn() != null && livroPesquisado.getIsbn() != livro.getIsbn())
						livro.setIsbn(livroPesquisado.getIsbn());

					if (livroPesquisado.getTitulo() != null && livroPesquisado.getTitulo() != livro.getTitulo())
						livro.setTitulo(livroPesquisado.getTitulo());

					if (livroPesquisado.getAutor() != null && livroPesquisado.getAutor() != livro.getAutor())
						livro.setAutor(livroPesquisado.getTitulo());

					if (livroPesquisado.getEditora() != null && livroPesquisado.getEditora() != livro.getEditora())
						livro.setEditora(livroPesquisado.getEditora());

					if (livroPesquisado.getValor() != null && livroPesquisado.getValor() != livro.getValor())
						livro.setValor(livroPesquisado.getValor());

					if (livroPesquisado.getQtdeExemplares() != null
							&& livroPesquisado.getQtdeExemplares() != livro.getQtdeExemplares())
						livro.setQtdeExemplares(livroPesquisado.getQtdeExemplares());

					return livro;

				}
			}

		} catch (Exception e) {

			e.getLocalizedMessage();
		}
		return null;

	}

	public void excluir(Long isbn) {

		for (Livro livro : livros) {
			if (livro.getIsbn().equals(isbn)) {
				livros.remove(livro);
			}
		}
	}

}
