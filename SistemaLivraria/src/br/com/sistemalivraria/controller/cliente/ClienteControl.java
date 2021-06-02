package br.com.sistemalivraria.controller.cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemalivraria.model.cliente.Cliente;
import br.com.sistemalivraria.utils.Constants;

public class ClienteControl {

	static List<Cliente> clientes = new ArrayList<>();

	public static boolean logar(String nome, String senha) {

		try {

			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);

			String sql = "SELECT nome, senha FROM clientes WHERE nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();

			Cliente func = new Cliente();

			while (rs.next()) {
				func.setNome(rs.getString("nome"));
				func.setSenha(rs.getString("senha"));

				clientes.add(func);
			}

			for (Cliente c : clientes) {
				if (c.getNome().equals(nome) && c.getSenha().equals(senha)) {
					return true;
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}