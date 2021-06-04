package br.com.sistemalivraria.view.telasfuncionario;

import br.com.sistemalivraria.controller.cliente.ClienteControl;
import br.com.sistemalivraria.model.cliente.Cliente;
import br.com.sistemalivraria.utils.AlertMessage;
import br.com.sistemalivraria.utils.CommonFunctions;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClienteBoundary extends Application {

	private TextField txtNome = new TextField();
	private TextField txtUsuario = new TextField();
	private TextField txtSenha = new TextField();

	private Button btnConsultar = new Button("Consultar");
	private Button btnIncluir = new Button("Incluir");
	private Button btnAlterar = new Button("Alterar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnVoltar = new Button("Voltar");

	private ClienteControl control = new ClienteControl();

	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();

		CommonFunctions.tamanhoTela(gp);

		Scene scn =  new Scene(gp, 450, 400);

		gp.add(new Label("Nome"), 0, 0);
		gp.add(txtNome, 1, 0);
		GridPane.setColumnSpan(txtNome, 3);

		gp.add(new Label("Usuario"), 0, 1);
		gp.add(txtUsuario, 1, 1);
		GridPane.setColumnSpan(txtUsuario, 3);

		gp.add(new Label("Senha"), 0, 2);
		gp.add(txtSenha, 1, 2);
		GridPane.setColumnSpan(txtSenha, 3);

		btnConsultar.setMinWidth(75);
		GridPane.setHalignment(btnConsultar, HPos.CENTER);

		btnIncluir.setMinWidth(75);
		GridPane.setHalignment(btnIncluir, HPos.CENTER);

		btnAlterar.setMinWidth(75);
		GridPane.setHalignment(btnAlterar, HPos.CENTER);

		btnExcluir.setMinWidth(75);
		GridPane.setHalignment(btnExcluir, HPos.CENTER);

		btnVoltar.setMinWidth(75);
		GridPane.setHalignment(btnVoltar, HPos.CENTER);

		gp.add(btnConsultar, 0, 6);
		gp.add(btnIncluir, 1, 6);
		gp.add(btnAlterar, 2, 6);
		gp.add(btnExcluir, 3, 6);
		gp.add(btnVoltar, 3, 10);

		btnConsultar.setOnAction((e) -> {

			if (txtNome.getText().isEmpty() && txtUsuario.getText().isEmpty()) {
				AlertMessage.alert("Pesquise por Nome ou Usuario");
				throw new IllegalArgumentException();
			}

			Cliente c = control.pesquisarPorNome(txtNome.getText().trim());

			if (c == null) {
				c = control.pesquisarPorUsuario(txtUsuario.getText().trim());
			}

			if (c == null) {
				AlertMessage.alert("Cliente não encontrado.");
				CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
				throw new IllegalArgumentException();
			}

			this.entityToBoundary(c);

		});

		btnIncluir.setOnAction((e) -> {

			if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty()) {

				AlertMessage.alert("Para incluir, preencha todos os campos");
				throw new IllegalArgumentException();
			}

			Cliente f = control.pesquisarPorUsuario(txtUsuario.getText());

			if (f != null) {
				AlertMessage.alert("Escolha outro nome de usuario");
				CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
			} else {
				try {
					control.adicionar(boundaryToEntity());
					this.entityToBoundary(new Cliente());
					CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
					AlertMessage.alert("Cliente incluido com sucesso!");

				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});

		btnAlterar.setOnAction((e) -> {

		});

		btnExcluir.setOnAction((e) -> {

			if (txtUsuario.getText().isEmpty()) {
				AlertMessage.alert("Preencha o campo usuário");
				throw new IllegalArgumentException();
			}

			control.excluir(txtUsuario.getText());
			AlertMessage.alert("Cliente excluido com sucesso!");

			CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
		});

		btnVoltar.setOnAction((e) -> {

			TelaInicialFuncionario tela = new TelaInicialFuncionario();

			try {
				tela.start(new Stage());
				CommonFunctions.fecharTela(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		stage.setScene(scn);
		stage.setTitle("Clientes");
		stage.show();
	}

	private Cliente boundaryToEntity() throws Exception {

		Cliente f = new Cliente();

		try {

			f.setNome(txtNome.getText().trim());
			f.setUsuario(txtUsuario.getText().trim());
			f.setSenha(txtSenha.getText().trim());

		} catch (Exception e) {
			e.printStackTrace();
			AlertMessage.alert("Erro ao processar a solicitação. Tente novamente.");
		} finally {
			CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
		}

		return f;
	}

	private void entityToBoundary(Cliente f) {

		if (f != null) {

			txtNome.setText(String.valueOf(f.getNome()));
			txtUsuario.setText(String.valueOf(f.getUsuario()));
			txtSenha.setText(String.valueOf(f.getSenha()));

		}

	}

}
