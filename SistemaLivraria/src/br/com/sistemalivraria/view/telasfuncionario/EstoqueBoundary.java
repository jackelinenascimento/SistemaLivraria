package br.com.sistemalivraria.view.telasfuncionario;

import br.com.sistemalivraria.controller.livro.LivroControl;
import br.com.sistemalivraria.utils.CommonFunctions;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EstoqueBoundary extends Application {

	private TextField txtISBN = new TextField();
	private TextField txtTitulo = new TextField();

	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnVoltar = new Button("Voltar");

	private LivroControl control = new LivroControl();

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();
		BorderPane panePrincipal = new BorderPane();
		Scene scn = new Scene(panePrincipal, 600, 400);

		gp.add(new Label("ISBN"), 0, 0);
		gp.add(txtISBN, 1, 0);

		gp.add(new Label("Titulo"), 0, 1);
		gp.add(txtTitulo, 1, 1);

		btnPesquisar.setMinWidth(75);
		GridPane.setHalignment(btnPesquisar, HPos.CENTER);
		gp.add(btnPesquisar, 3, 2);

		btnVoltar.setMinWidth(75);
		GridPane.setHalignment(btnVoltar, HPos.CENTER);
		gp.add(btnVoltar, 3, 3);

		control.generatedTable();
		panePrincipal.setTop(gp);
		panePrincipal.setCenter(control.getTable());

		//Bindings.bindBidirectional(txtISBN.textProperty(), control.isbnProperty());
		//Bindings.bindBidirectional(txtTitulo.textProperty(), control.tituloProperty());

		stage.setScene(scn);
		stage.setTitle("Estoque");
		stage.show();

		btnVoltar.setOnAction((e) -> {

			TelaInicialFuncionario tela = new TelaInicialFuncionario();

			try {
				tela.start(new Stage());
				CommonFunctions.fecharTela(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

}
