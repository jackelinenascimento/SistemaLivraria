package br.com.sistemalivraria.view;

import br.com.sistemalivraria.controller.funcionario.FuncionarioControl;
import br.com.sistemalivraria.model.funcionario.Funcionario;
import br.com.sistemalivraria.utils.AlertMessage;
import br.com.sistemalivraria.utils.CommonFunctions;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FuncionarioBoundary {
	private TextField txtNome = new TextField();
	private TextField txtUsuario = new TextField();
	private TextField txtSenha = new TextField();

	private Button btnConsultar = new Button("Consultar");
	private Button btnIncluir = new Button("Incluir");
	private Button btnAlterar = new Button("Alterar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnVoltar = new Button("Voltar");

	private FuncionarioControl control = new FuncionarioControl();

	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();

		CommonFunctions.tamanhoTela(gp);

		Scene scn = new Scene(gp, 430, 400);

		gp.add(new Label("Nome"), 0, 0);
		gp.add(txtNome, 1, 0);
		txtNome.setMaxWidth(100);

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

			Funcionario f = control.pesquisarPorNome(txtNome.getText().trim());

			if (f == null) {
				f = control.pesquisarPorUsuario(txtUsuario.getText().trim());
			}

			if (f == null) {
				AlertMessage.alert("Funcionario não encontrado.");
				CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
				throw new IllegalArgumentException();
			}

			this.entityToBoundary(f);

		});

		btnIncluir.setOnAction((e) -> {

			if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty()) {

				AlertMessage.alert("Para incluir, preencha todos os campos");
				throw new IllegalArgumentException();
			}

			Funcionario f = control.pesquisarPorUsuario(txtUsuario.getText());

			if (f != null) {
				AlertMessage.alert("Escolha outro usuario");
				CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
			} else {
				try {
					control.adicionar(boundaryToEntity());
					this.entityToBoundary(new Funcionario());
					CommonFunctions.limparCampos(txtNome, txtUsuario, txtSenha);
					AlertMessage.alert("Funcionário incluido com sucesso!");

				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});

		btnAlterar.setOnAction((e) -> {

		});

		btnExcluir.setOnAction((e) -> {

			control.excluir(txtUsuario.getText());
			AlertMessage.alert("Funcionario excluido com sucesso!");

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
		stage.setTitle("Funcionários");
		stage.show();
	}

	private Funcionario boundaryToEntity() throws Exception {

		Funcionario f = new Funcionario();

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

	private void entityToBoundary(Funcionario f) {

		if (f != null) {

			txtNome.setText(String.valueOf(f.getNome()));
			txtUsuario.setText(String.valueOf(f.getUsuario()));
			txtSenha.setText(String.valueOf(f.getSenha()));

		}
	}

}
