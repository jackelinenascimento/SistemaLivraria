package br.com.sistemalivraria.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.sistemalivraria.controller.livro.LivroControl;
import br.com.sistemalivraria.model.livro.Livro;
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

public class LivroBoundary extends Application {

	private TextField txtIsbn = new TextField();
	private TextField txtTitulo = new TextField();
	private TextField txtAutor = new TextField();
	private TextField txtEditora = new TextField();
	private TextField txtLancamento = new TextField();
	private TextField txtValor = new TextField();
	private TextField txtQtdadeExemplares = new TextField();

	private Button btnConsultar = new Button("Consultar");
	private Button btnIncluir = new Button("Incluir");
	private Button btnAlterar = new Button("Alterar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnVoltar = new Button("Voltar");

	private LivroControl control = new LivroControl();

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

		gp.add(new Label("Lançamento"), 0, 4);
		gp.add(txtLancamento, 1, 4);
		txtLancamento.setMaxWidth(100);
		txtLancamento.setPromptText("_ _ / _ _ / _ _ _ _ ");

		Label valor = new Label("Valor");
		gp.add(valor, 2, 4);
		GridPane.setHalignment(valor, HPos.CENTER);
		gp.add(txtValor, 3, 4);
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

		btnConsultar.setOnAction((e) -> {

			Long isbn = null;

			try {
				isbn = Long.parseLong(txtIsbn.getText().trim());
			} catch (Exception e1) {
				e1.getMessage();
			} finally {
				isbn = null;
			}

			Livro livro = control.pesquisar(isbn, txtTitulo.getText().trim());

			if (livro == null) {
				AlertMessage.alert("Livro não encontrado.");
				CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtLancamento, txtValor,
						txtQtdadeExemplares);
				
			}

			this.entityToBoundary(livro);
		});

		btnIncluir.setOnAction((e) -> {

			Livro livro = control.pesquisar(Long.parseLong(txtIsbn.getText()), txtTitulo.getText());

			if (livro != null) {
				AlertMessage.alert("ISBN já incluso no sistema!");
			} else {
				try {
					txtValor.getText().replaceAll(",", ".");
					control.adicionar(boundaryToEntity());
					this.entityToBoundary(new Livro());
					CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtLancamento, txtValor,
							txtQtdadeExemplares);

				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});

		btnAlterar.setOnAction((e) -> {

			if (txtIsbn.getText() != null) {
				AlertMessage.alert("Pesquise por ISBN");
			}

			Livro livro = new Livro();

			if (txtIsbn.getText() != null || txtTitulo.getText() != null) {
				livro = control.pesquisar(Long.parseLong(txtIsbn.getText()), txtTitulo.getText());
			}

			if (livro != null) {
				try {
					txtLancamento.setText(txtLancamento.getText().replace("-", ""));
					txtLancamento.setText(txtLancamento.getText().substring(4, 5)
							+ txtLancamento.getText().substring(6, 7) + txtLancamento.getText().substring(0, 3));
					control.alterarDados(this.boundaryToEntity());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

		btnExcluir.setOnAction((e) -> {

			Livro livro = new Livro();

			if (txtIsbn.getText() != null || txtTitulo.getText() != null) {
				livro = control.pesquisar(Long.parseLong(txtIsbn.getText()), txtTitulo.getText());
			}

			if (livro == null) {
				AlertMessage.alert("Livro não encontrado. Erro ao excluir");
			} else {
				control.excluir(livro.getIsbn());
				AlertMessage.alert("Livro excluido com sucesso!");
			}
			CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtLancamento, txtValor,
					txtQtdadeExemplares);
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
			livro.setLancamento(LocalDate.parse(txtLancamento.getText().trim(), dtf));
			livro.setValor(Float.parseFloat(txtValor.getText().trim()));

			livro.setQtdeExemplares(Integer.parseInt(txtQtdadeExemplares.getText().trim()));

			AlertMessage.alert("Livro incluido com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			AlertMessage.alert("Erro ao processar a solicitação. Tente novamente.");
		} finally {
			CommonFunctions.limparCampos(txtIsbn, txtTitulo, txtAutor, txtEditora, txtLancamento, txtValor,
					txtQtdadeExemplares);
		}

		return livro;
	}

	private void entityToBoundary(Livro livro) {

		if (livro != null) {

			txtIsbn.setText(String.valueOf(livro.getIsbn()));
			txtTitulo.setText(String.valueOf(livro.getTitulo()));
			txtAutor.setText(String.valueOf(livro.getAutor()));

			txtEditora.setText(String.valueOf(livro.getEditora()));
			txtLancamento.setText(String.valueOf(livro.getLancamento()));
			txtValor.setText(String.valueOf(livro.getValor()));

			txtQtdadeExemplares.setText(String.valueOf(livro.getQtdeExemplares()));
		}
	}

}
