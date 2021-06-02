package br.com.sistemalivraria.view.telascomuns;

import br.com.sistemalivraria.controller.cliente.ClienteControl;
import br.com.sistemalivraria.controller.funcionario.FuncionarioControl;
import br.com.sistemalivraria.utils.AlertMessage;
import br.com.sistemalivraria.utils.CommonFunctions;
import br.com.sistemalivraria.view.telascliente.TelaInicialCliente;
import br.com.sistemalivraria.view.telasfuncionario.TelaInicialFuncionario;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {

	private TextField txtUsuario = new TextField();
	private TextField txtSenha = new PasswordField();
	private Button btnConfirmar = new Button("Confirmar");
	private Button btnCancelar = new Button("Cancelar");
	private Button btnSair = new Button("Fechar");

	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();

		CommonFunctions.tamanhoTela(gp);

		Scene scn = new Scene(gp, 600, 400);

		gp.add(new Label("Usuário"), 0, 0);
		gp.add(txtUsuario, 0, 1);
		GridPane.setColumnSpan(txtUsuario, 4);

		gp.add(new Label("Senha"), 0, 2);
		gp.add(txtSenha, 0, 3);
		GridPane.setColumnSpan(txtSenha, 4);

		btnConfirmar.setMinWidth(75);
		GridPane.setHalignment(btnConfirmar, HPos.CENTER);
		gp.add(btnConfirmar, 0, 4);

		btnConfirmar.setOnMouseClicked((e) -> {

			Application tela = null;

			if (FuncionarioControl.logar(txtUsuario.getText(), txtSenha.getText())) {

				tela = new TelaInicialFuncionario();
			}

			if (ClienteControl.logar(txtUsuario.getText(), txtSenha.getText())) {

				tela = new TelaInicialCliente();
			}

			if (tela != null) {
				try {
					tela.start(new Stage());
					CommonFunctions.fecharTela(stage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				AlertMessage.alert("Dados incorretos. Tente novamente.");
				CommonFunctions.limparCampos(txtUsuario, txtSenha);
			}

		});

		btnCancelar.setMinWidth(75);
		GridPane.setHalignment(btnCancelar, HPos.CENTER);
		gp.add(btnCancelar, 1, 4);

		btnCancelar.setOnMouseClicked((e) -> {
			CommonFunctions.limparCampos(txtUsuario, txtSenha);
		});

		btnCancelar.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
				CommonFunctions.limparCampos(txtUsuario, txtSenha);
			}
		});

		btnSair.setMinWidth(75);
		GridPane.setHalignment(btnSair, HPos.CENTER);
		gp.add(btnSair, 3, 4);

		btnSair.setOnMouseClicked((e) -> {
			CommonFunctions.fecharTela(stage);
		});

		btnSair.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
				CommonFunctions.fecharTela(stage);
			}
		});

		stage.setScene(scn);
		stage.setTitle("*** Login ***");
		stage.show();
	}
}
