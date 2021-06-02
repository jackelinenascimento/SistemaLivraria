package br.com.sistemalivraria.view;

import br.com.sistemalivraria.controller.livro.LivroControl;
import br.com.sistemalivraria.model.livro.Livro;
import br.com.sistemalivraria.utils.AlertMessage;
import br.com.sistemalivraria.utils.CommonFunctions;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LivroBoundary extends Application {

	private TextField txtIsbn = new TextField();
	private TextField txtTitulo = new TextField();
	private TextField txtAutor = new TextField();
	private TextField txtEditora = new TextField();
	private TextField txtValor = new TextField();
	private TextField txtQtdadeExemplares = new TextField();

	private Button btnConsultar = new Button("Consultar");
	private Button btnIncluir = new Button("Incluir");
	private Button btnAlterar = new Button("Alterar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnVoltar = new Button("Voltar");

	private LivroControl control = new LivroControl();

	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();

		CommonFunctions.tamanhoTela(gp);

		Scene scn = new Scene(gp, 430, 400);

		gp.add(new Label("ISBN"), 0, 0);
		gp.add(txtIsbn, 1, 0);
		txtIsbn.setMaxWidth(100);

		gp.add(new Label("Titulo"), 0, 1);
		gp.add(txtTitulo, 1, 1);
		GridPane.setColumnSpan(txtTitulo, 3);

		gp.add(new Label("Autor"), 0, 2);
		gp.add(txtAutor, 1, 2);
		GridPane.setColumnSpan(txtAutor, 3);

		gp.add(new Label("Editora"), 0, 3);
		gp.add(txtEditora, 1, 3);
		GridPane.setColumnSpan(txtEditora, 3);

		gp.add(new Label("Valor"), 0, 4);
		gp.add(txtValor, 1, 4);
		txtValor.setMaxWidth(100);
		txtValor.setPromptText("R$ ___ , __ ");

		gp.add(new Label("Quantidade"), 0, 5);
		gp.add(txtQtdadeExemplares, 1, 5);
		txtQtdadeExemplares.setMaxWidth(100);

		btnConsultar.setMinWidth(75);
		GridPane.setHalignment(btnConsultar, HPos.CENTER);

		btnIncluir.setMinWidth(75);
		GridPane.setHalignment(btnIncluir, HPos.CENTER);

		btnAlterar.setMinWidth(75);
		GridPane.setHalignment(btnAlterar, HPos.CENTER);

		btnExcluir.setMinWidth(75);
		GridPane.setHalignment(btnExcluir, HPos.CENTER);

		gp.add(btnConsultar, 0, 6);
		gp.add(btnIncluir, 1, 6);
		gp.add(btnAlterar, 2, 6);
		gp.add(btnExcluir, 3, 6);
		gp.add(btnVoltar, 3, 10);

		txtIsbn.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}?")) {
					txtIsbn.setText("");
				}
			}
		});

		txtValor.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}([\\,]\\d{0,4})?")) {
					txtValor.setText("");
				}
			}
		});

		txtQtdadeExemplares.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}?")) {
					txtQtdadeExemplares.setText("");
				}
			}
		});

		btnConsultar.setOnAction((e) -> {

			if (txtIsbn.getText().isEmpty() && txtTitulo.getText().isEmpty()) {
				AlertMessage.alert("Pesquise por ISBN ou Titulo da obra");
				throw new IllegalArgumentException();
			}

			Livro livro = control.pesquisarPorTitulo(txtTitulo.getText().trim());

			if (livro == null) {
				livro = control.pesquisarISNB(Long.parseLong(txtIsbn.getText().trim()));
			}

			if (livro == null) {
				AlertMessage.alert("Livro não encontrado.");
				CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtValor, txtQtdadeExemplares);
				throw new IllegalArgumentException();
			}

			this.entityToBoundary(livro);

		});

		btnIncluir.setOnAction((e) -> {

			if (txtIsbn.getText().isEmpty() || txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty()
					|| txtEditora.getText().isEmpty() || txtEditora.getText().isEmpty()
					|| txtQtdadeExemplares.getText().isEmpty()) {

				AlertMessage.alert("Para incluir, preencha todos os campos");
				throw new IllegalArgumentException();
			}

			Livro livro = control.pesquisarISNB(Long.parseLong(txtIsbn.getText()));

			if (livro != null) {
				AlertMessage.alert("ISBN já incluso no sistema!");
				CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtValor, txtQtdadeExemplares);
			} else {
				try {
					control.adicionar(boundaryToEntity());
					this.entityToBoundary(new Livro());
					CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtValor,
							txtQtdadeExemplares);
					AlertMessage.alert("Livro incluido com sucesso!");

				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});

		btnAlterar.setOnAction((e) -> {

		});

		btnExcluir.setOnAction((e) -> {

			control.excluir(Long.parseLong(txtIsbn.getText()));
			AlertMessage.alert("Livro excluido com sucesso!");

			CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtValor, txtQtdadeExemplares);
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
		stage.setTitle("CRUD Livros");
		stage.show();
	}

	private Livro boundaryToEntity() throws Exception {

		Livro livro = new Livro();

		try {

			livro.setIsbn(Long.parseLong(txtIsbn.getText().trim()));
			livro.setTitulo(txtTitulo.getText().trim());
			livro.setAutor(txtAutor.getText().trim());

			livro.setEditora(txtEditora.getText().trim());
			livro.setValor(Float.parseFloat(txtValor.getText().trim().replace(",", ".")));
			livro.setQtdeExemplares(Integer.parseInt(txtQtdadeExemplares.getText().trim()));

		} catch (Exception e) {
			e.printStackTrace();
			AlertMessage.alert("Erro ao processar a solicitação. Tente novamente.");
		} finally {
			CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtValor, txtQtdadeExemplares);
		}

		return livro;
	}

	private void entityToBoundary(Livro livro) {

		if (livro != null) {

			txtIsbn.setText(String.valueOf(livro.getIsbn()));
			txtTitulo.setText(String.valueOf(livro.getTitulo()));
			txtAutor.setText(String.valueOf(livro.getAutor()));

			txtEditora.setText(String.valueOf(livro.getEditora()));
			txtValor.setText(String.valueOf(livro.getValor()));
			txtQtdadeExemplares.setText(String.valueOf(livro.getQtdeExemplares()));
		}
	}

}
