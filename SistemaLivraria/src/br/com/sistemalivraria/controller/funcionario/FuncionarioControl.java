package br.com.sistemalivraria.controller.funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemalivraria.model.funcionario.Funcionario;
import br.com.sistemalivraria.utils.Constants;

public class FuncionarioControl {

	static List<Funcionario> funcionarios = new ArrayList<>();

	public static boolean logar(String nome, String senha) {

		try {

			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);

			String sql = "SELECT id, nome, senha FROM funcionarios WHERE nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario func = new Funcionario();
				func.setId(rs.getLong("id"));
				func.setNome(rs.getString("nome"));
				func.setSenha(rs.getString("senha"));

				funcionarios.add(func);
			}

			for (Funcionario f : funcionarios) {
				if (f.getNome().equals(nome) && f.getSenha().equals(senha)) {
					return true;
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}
