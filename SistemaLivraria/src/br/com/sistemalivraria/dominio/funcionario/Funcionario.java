package br.com.sistemalivraria.dominio.funcionario;

import br.com.sistemalivraria.dominio.usuario.Usuario;

public class Funcionario extends Usuario{

	public Funcionario(Long id, String nome, String senha) {
		super(id, nome, senha);
	}

}
