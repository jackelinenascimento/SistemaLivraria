package br.com.sistemalivraria.view.telascomuns;

import br.com.sistemalivraria.utils.CommonFunctions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Principal extends Application {

	private Label labelTitulo = new Label("Sistema Livraria");
	private Button btnEntrar = new Button("Entrar");
	private Button btnSair = new Button("Fechar");

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();

		CommonFunctions.tamanhoTela(gp);

		Scene scn = new Scene(gp, 450, 400);

		labelTitulo.setTextFill(Color.DARKBLUE);
		labelTitulo.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		gp.add(labelTitulo, 4, 0);
		GridPane.setColumnSpan(labelTitulo, 4);

		btnEntrar.setBorder(null);
		gp.add(btnEntrar, 4, 4);
		gp.add(btnSair, 7, 4);

		btnEntrar.setOnAction((e) -> {
			Login login = new Login();
			try {
				login.start(new Stage());
				CommonFunctions.fecharTela(stage);
				
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		});


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
