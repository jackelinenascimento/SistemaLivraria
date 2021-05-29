package utils;

import javafx.geometry.HPos;
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

}
