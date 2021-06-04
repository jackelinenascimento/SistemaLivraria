package br.com.sistemalivraria.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemalivraria.model.cliente.Cliente;
import br.com.sistemalivraria.utils.BDFunctions;

public class ClienteControl {

	static List<Cliente> clientes = new ArrayList<>();

	public static boolean logar(String usuario, String senha) {

		clientes.clear();
		
		try {

			Connection con = BDFunctions.conexaoBD();
			
			String sql = "SELECT usuario, senha FROM clientes WHERE usuario LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + usuario + "%");
			ResultSet rs = stmt.executeQuery();

			Cliente c = new Cliente();

			while (rs.next()) {
				c.setUsuario(rs.getString("usuario"));
				c.setSenha(rs.getString("senha"));

				clientes.add(c);
			}

			for (Cliente cli : clientes) {
				if (cli.getUsuario().equals(usuario) && cli.getSenha().equals(senha)) {
					return true;
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Cliente pesquisarPorNome(String nome) {
		
		clientes.clear();
		
		Cliente pesquisa = null;

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "SELECT nome, usuario, senha FROM clientes WHERE nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setNome(rs.getString("nome"));
				c.setUsuario(rs.getString("usuario"));
				c.setSenha(rs.getString("senha"));

				clientes.add(c);
			}

			for (Cliente cli : clientes) {
				if (cli.getNome().equals(nome)) {
					pesquisa = cli;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pesquisa;
	}

	public Cliente pesquisarPorUsuario(String usuario) {
		
		clientes.clear();
		
		Cliente pesquisa = null;

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "SELECT nome, usuario, senha FROM clientes WHERE usuario LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + usuario + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setNome(rs.getString("nome"));
				c.setUsuario(rs.getString("usuario"));
				c.setSenha(rs.getString("senha"));

				clientes.add(c);
			}

			for (Cliente cli : clientes) {
				if (cli.getUsuario().equals(usuario)) {
					pesquisa = cli;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pesquisa;
	}

	public void adicionar(Cliente cliente) {

		try {

			Connection con = BDFunctions.conexaoBD();

			String sql = "insert into clientes " + "(nome, usuario, senha) VALUES (?, ?, ?)";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getUsuario());
			stmt.setString(3, cliente.getSenha());

			stmt.execute();

		} catch (Exception e) {
			e.getStackTrace();
		}

		
	}

	public void excluir(String usuario) {
		
		try {			
			Connection con = BDFunctions.conexaoBD();
			
			String sql = "delete from clientes WHERE usuario LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + usuario + "%");
			stmt.executeQuery();
					
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}

}
