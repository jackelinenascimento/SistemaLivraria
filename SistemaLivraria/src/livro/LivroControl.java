package livro;

import java.util.ArrayList;
import java.util.List;

public class LivroControl {

	private List<Livro> livros = new ArrayList<Livro>();

	public void adicionar(Livro livro) {
		livros.add(livro);
	}

	public List<Livro> pesquisarPorTitulo(String titulo) {

		List<Livro> pesquisa = new ArrayList<Livro>();

		for (Livro livro : livros) {
			if (livro.getTitulo().contains(titulo)) {
				pesquisa.add(livro);
			}
		}
		return pesquisa;
	}

	public Livro pesquisarPorIsbn(String isbn) {

		Livro pesquisa = null;

		for (Livro livro : livros) {
			if (livro.getTitulo().contains(isbn)) {
				pesquisa = livro;
			}
		}
		return pesquisa;
	}

	public Livro alterarDadosBuscaPorId(Livro livroPesquisado) {

		try {

			for (Livro livro : livros) {
				if (livro.getIsbn() == livroPesquisado.getIsbn()) {

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

	public void excluirPorId(Long isbn) {

		for (Livro livro : livros) {
			if (livro.getIsbn() == isbn) {
				livros.remove(livro);
			}
		}
	}

}
