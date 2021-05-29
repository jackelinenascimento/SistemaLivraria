package br.com.sistemalivraria.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.CommonFunctions;

public class TelaInicialFuncionario extends Application {

	private Button btnCliente = new Button("Cliente");
	private Button btnFuncionario = new Button("Funcion�rio");
	private Button btnLivros = new Button("Livros");
	
	private Button btnEstoque = new Button("Estoque");
	private Button btnSair = new Button("Fechar");
	

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();
		gp.setPadding(new Insets(60, 0, 20, 20));
		gp.setHgap(10);
		gp.setVgap(10);

		Scene scn = new Scene(gp, 430, 400);
		
		gp.add(new Label("Sistema de Funcion�rio"), 0, 0);
		
		gp.add(btnCliente, 0, 1);
		gp.add(btnFuncionario, 0, 2);
		gp.add(btnLivros, 1, 1);	
		gp.add(btnEstoque, 1, 2);
		
		CommonFunctions.tamanhoBotao(150, 80, btnCliente, btnFuncionario, btnLivros, btnEstoque);

		gp.add(btnSair, 0, 4);
		CommonFunctions.tamanhoBotao(50, 20, btnSair);
		
		btnSair.setOnMouseClicked((e) -> {
			CommonFunctions.fecharTela(stage);
		});

		btnSair.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
				CommonFunctions.fecharTela(stage);
			}
		});
		
		stage.setScene(scn);
		stage.setTitle("*** Tela Inicial - Funcion�rio ***");
		stage.show();

	}

}