package br.com.sistemalivraria.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.CommonFunctions;

public class Principal extends Application {

	private Text labelTitulo = new Text("Sistema Livraria");
	private Button btnEntrar = new Button("Entrar");
	private Button btnSair = new Button("Fechar");

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();
		gp.setPadding(new Insets(60, 0, 20, 20));
		gp.setHgap(10);
		gp.setVgap(10);

		Scene scn = new Scene(gp, 430, 400);

		gp.add(labelTitulo, 2, 0);

		gp.add(btnEntrar, 0, 1);

		btnEntrar.setOnAction((e) -> {
			Login login = new Login();
			try {
				login.start(new Stage());
				CommonFunctions.fecharTela(stage);
				
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		});

		gp.add(btnSair, 3, 1);

		btnSair.setOnMouseClicked((e) -> {
			CommonFunctions.fecharTela(stage);
		});

		btnSair.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
				CommonFunctions.fecharTela(stage);
			}
		});

		stage.setScene(scn);
		stage.setTitle("*** Tela Inicial ***");
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(Principal.class, args);
	}

}
