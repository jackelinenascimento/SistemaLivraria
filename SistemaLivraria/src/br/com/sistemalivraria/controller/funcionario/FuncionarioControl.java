package br.com.sistemalivraria.controller.funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemalivraria.model.funcionario.Funcionario;
import br.com.sistemalivraria.utils.BDFunctions;
import br.com.sistemalivraria.utils.Constants;

public class FuncionarioControl {

	static List<Funcionario> funcionarios = new ArrayList<>();

	public static boolean logar(String usuario, String senha) {

		try {

			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);

			String sql = "SELECT id, nome, usuario, senha FROM funcionarios WHERE usuario LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + usuario + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario func = new Funcionario();
				func.setId(rs.getLong("id"));
				func.setNome(rs.getString("nome"));
				func.setUsuario(rs.getString("usuario"));
				func.setSenha(rs.getString("senha"));

				funcionarios.add(func);
			}

			for (Funcionario f : funcionarios) {
				if (f.getUsuario().equals(usuario) && f.getSenha().equals(senha)) {
					return true;
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Funcionario pesquisarPorNome(String nome) {

		Funcionario pesquisa = null;

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "SELECT nome, usuario, senha FROM funcionarios WHERE nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setNome(rs.getString("nome"));
				f.setUsuario(rs.getString("usuario"));
				f.setSenha(rs.getString("senha"));

				funcionarios.add(f);
			}

			for (Funcionario f : funcionarios) {
				if (f.getNome().equals(nome)) {
					pesquisa = f;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pesquisa;
	}

	public Funcionario pesquisarPorUsuario(String usuario) {
		Funcionario pesquisa = null;

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "SELECT nome, usuario, senha FROM funcionarios WHERE usuario LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + usuario + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setNome(rs.getString("nome"));
				f.setUsuario(rs.getString("usuario"));
				f.setSenha(rs.getString("senha"));

				funcionarios.add(f);
			}

			for (Funcionario f : funcionarios) {
				if (f.getUsuario().equals(usuario)) {
					pesquisa = f;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pesquisa;
	}

	public void adicionar(Funcionario f) {
		// TODO Auto-generated method stub
		
	}

	public void excluir(String usuario) {
		// TODO Auto-generated method stub
		
	}

}
