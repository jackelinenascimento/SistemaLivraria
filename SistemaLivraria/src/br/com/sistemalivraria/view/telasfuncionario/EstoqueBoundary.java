package br.com.sistemalivraria.view.telasfuncionario;

import br.com.sistemalivraria.controller.estoque.EstoqueController;
import br.com.sistemalivraria.model.livro.Livro;
import br.com.sistemalivraria.utils.CommonFunctions;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EstoqueBoundary extends Application {

	private TableView<Livro> table;
	private ObservableList<Livro> data;
	private Text actionStatus;
	private Button btnVoltar = new Button("Voltar");
	

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Estoque");

		Label label = new Label("Livros Cadastrados");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(label);

		table = new TableView<Livro>();
		data = EstoqueController.getInitialTableData();
		table.setItems(data);

		TableColumn isbnCol = new TableColumn<Object, Object>("ISBN");
		isbnCol.setCellValueFactory(new PropertyValueFactory<Object, Object>("isbn"));

		TableColumn tituloCol = new TableColumn<Object, Object>("Titulo");
		tituloCol.setCellValueFactory(new PropertyValueFactory<Object, Object>("titulo"));

		TableColumn autorCol = new TableColumn<Object, Object>("Autor");
		autorCol.setCellValueFactory(new PropertyValueFactory<Object, Object>("autor"));

		TableColumn valorCol = new TableColumn<Object, Object>("Valor");
		valorCol.setCellValueFactory(new PropertyValueFactory<Object, Object>("valor"));

		TableColumn qtdadeExemplaresCol = new TableColumn<Object, Object>("Estoque");
		qtdadeExemplaresCol.setCellValueFactory(new PropertyValueFactory<Object, Object>("qtdeExemplares"));

		table.getColumns().setAll(isbnCol, tituloCol, autorCol, valorCol, qtdadeExemplaresCol);
		table.setPrefWidth(450);
		table.setPrefHeight(300);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());

		actionStatus = new Text();
		actionStatus.setFill(Color.FIREBRICK);

		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(25, 25, 25, 25));
		;
		vbox.getChildren().addAll(hb, table, actionStatus, btnVoltar);

		btnVoltar.setOnAction((e) -> {

			TelaInicialFuncionario tela = new TelaInicialFuncionario();

			try {
				tela.start(new Stage());
				CommonFunctions.fecharTela(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		
		// Scene
		Scene scene = new Scene(vbox, 500, 475); // w x h
		stage.setScene(scene);
		stage.show();

	}

	private class RowSelectChangeListener implements ChangeListener<Object> {

		@Override
		public void changed(ObservableValue valorObservado, Object antigoValor, Object novoValor) {

			int ix = Integer.valueOf((String) novoValor);
			
			if(ix == data.size()) {
				return;
			}
			
			Livro livro = data.get(ix);
			actionStatus.setText(livro.toString());

		}
	}
}
