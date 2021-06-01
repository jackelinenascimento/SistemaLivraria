package br.com.sistemalivraria.utils;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CommonFunctions {

	public static void fecharTela(Stage stage) {
		stage.close();
	}

	public static void limparCampos(TextInputControl... campos) {

		for(int i=0; i < campos.length; i++) {
			campos[i].setText("");
		}
	}
	
	public static void tamanhoBotao(int largura, int comprimento, Button... botoes) {
		
		for(int i=0; i < botoes.length; i++) {
			
			GridPane.setHalignment(botoes[i], HPos.CENTER);
			
			botoes[i].setMinHeight(comprimento);
			botoes[i].setMinWidth(largura);
		}
	}

	public static void tamanhoTela(GridPane gp) {
		gp.setPadding(new Insets(60, 60, 0, 60));
		gp.setHgap(10);
		gp.setVgap(10);
	}
	
}
