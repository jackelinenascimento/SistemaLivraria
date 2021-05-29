package br.com.sistemalivraria.dominio.cliente;

import br.com.sistemalivraria.dominio.usuario.Usuario;

public class Cliente extends Usuario{

	public Cliente(Long id, String nome, String senha) {
		super(id, nome, senha);
	}

}
