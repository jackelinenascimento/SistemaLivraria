package br.com.sistemalivraria.model.cliente;

import br.com.sistemalivraria.model.usuario.Usuario;

public class Cliente extends Usuario{

	public Cliente(Long id, String nome, String senha) {
		super(id, nome, senha);
	}

}
