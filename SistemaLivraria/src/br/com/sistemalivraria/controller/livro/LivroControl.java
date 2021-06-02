package br.com.sistemalivraria.controller.livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.sistemalivraria.model.livro.Livro;
import br.com.sistemalivraria.utils.BDFunctions;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;

public class LivroControl {

	private ObservableList<Livro> livros = FXCollections.observableArrayList();
	private TableView<Livro> table = new TableView<>();

	private StringProperty isbn = new SimpleStringProperty("");
	private StringProperty titulo = new SimpleStringProperty("");
	
	 public void setEntity(Livro l) {
	        if (l != null) {
	            titulo.set(l.getTitulo());
	            isbn.set(l.getIsbn());
	        }
	    }


	public void adicionar(Livro livro) {

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "insert into livros " + "(isbn, titulo, autor, editora, valor, qtdadeExemplares)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1, livro.getIsbn());
			stmt.setString(2, livro.getTitulo());
			stmt.setString(3, livro.getAutor());
			stmt.setString(4, livro.getEditora());
			stmt.setFloat(5, livro.getValor());
			stmt.setInt(6, livro.getQtdeExemplares());

			stmt.execute();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public Livro pesquisarISNB(Long isbn) {

		Livro pesquisa = null;

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "SELECT isbn, titulo, autor, editora, valor, qtdadeExemplares FROM livros WHERE isbn LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + isbn + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro();
				livro.setIsbn(rs.getLong("isbn"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setEditora(rs.getString("editora"));
				livro.setValor(rs.getFloat("valor"));
				livro.setQtdeExemplares(rs.getInt("qtdadeExemplares"));

				livros.add(livro);
			}

			for (Livro l : livros) {
				if (l.getIsbn().equals(isbn)) {
					pesquisa = l;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pesquisa;
	}

	public Livro pesquisarPorTitulo(String titulo) {

		Livro pesquisa = null;

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "SELECT isbn, titulo, autor, editora, valor, qtdadeExemplares FROM livros WHERE titulo LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + titulo + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro();
				livro.setIsbn(rs.getLong("isbn"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setEditora(rs.getString("editora"));
				livro.setValor(rs.getFloat("valor"));
				livro.setQtdeExemplares(rs.getInt("qtdadeExemplares"));

				livros.add(livro);
			}

			for (Livro l : livros) {
				if (l.getTitulo().contains(titulo)) {
					pesquisa = l;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pesquisa;
	}

	public Livro alterarDados(Livro livroPesquisado) {

		return null;

	}

	public void excluir(Long isbn) {

		try {
			Connection con = BDFunctions.conexaoBD();

			String sql = "delete from livros WHERE isbn LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + isbn + "%");
			stmt.executeQuery();

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	public void generatedTable() {
		// TODO Auto-generated method stub

	}

	public Node getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	public Property isbnProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	public Property tituloProperty() {
		// TODO Auto-generated method stub
		return null;
	}

}
