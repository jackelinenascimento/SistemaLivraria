package br.com.sistemalivraria.controller.estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.sistemalivraria.model.livro.Livro;
import br.com.sistemalivraria.utils.BDFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstoqueController {
	
	public static ObservableList<Livro> getInitialTableData() {

		ArrayList<Livro> livros = new ArrayList<Livro>();

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "SELECT isbn, titulo, autor, editora, valor, qtdeExemplares FROM livros order by titulo asc";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro();
				livro.setIsbn(rs.getLong("isbn"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setEditora(rs.getString("editora"));
				livro.setValor(rs.getFloat("valor"));
				livro.setQtdeExemplares(rs.getInt("qtdeExemplares"));

				livros.add(livro);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		ObservableList<Livro> data = FXCollections.observableList(livros);
		return data;
	}

}
