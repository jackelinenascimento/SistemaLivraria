package br.com.sistemalivraria.controller.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemalivraria.model.funcionario.Funcionario;
import br.com.sistemalivraria.utils.BDFunctions;

public class FuncionarioControl {

	static List<Funcionario> funcionarios = new ArrayList<>();

	public static boolean logar(String usuario, String senha) {

		funcionarios.clear();
		
		try {

			Connection con = BDFunctions.conexaoBD();
			
			String sql = "SELECT usuario, senha FROM funcionarios WHERE usuario LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + usuario + "%");
			ResultSet rs = stmt.executeQuery();

			Funcionario c = new Funcionario();

			while (rs.next()) {
				c.setUsuario(rs.getString("usuario"));
				c.setSenha(rs.getString("senha"));

				funcionarios.add(c);
			}

			for (Funcionario func : funcionarios) {
				if (func.getUsuario().equals(usuario) && func.getSenha().equals(senha)) {
					return true;
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	public Funcionario pesquisarPorNome(String nome) {
		
		funcionarios.clear();
		
		if(nome == null) {
			return null;
		}

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

		funcionarios.clear();
		
		if(usuario == null) {
			return null;
		}

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

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "insert into funcionarios " + "(nome, usuario, senha) VALUES (?, ?, ?)";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getUsuario());
			stmt.setString(3, f.getSenha());

			stmt.execute();

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	public void excluir(String usuario) {

		try {			
			Connection con = BDFunctions.conexaoBD();
			
			String sql = "delete from funcionarios WHERE usuario LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + usuario + "%");
			stmt.executeQuery();
					
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}
