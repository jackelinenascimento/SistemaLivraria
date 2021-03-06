package br.com.sistemalivraria.view.telascliente;

import br.com.sistemalivraria.utils.CommonFunctions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaInicialCliente extends Application{

	private Button btnComprar = new Button("Comprar");
	private Button btnMeuCarrinho = new Button("Meu Carrinho");
	private Button btnFecharTela = new Button("Fechar");
	

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gp = new GridPane();
		
		CommonFunctions.tamanhoTela(gp);

		Scene scn = new Scene(gp, 600, 400);
		
		gp.add(new Label("Sistema de Cliente"), 0, 0);
		
		gp.add(btnComprar, 0, 1);
		gp.add(btnMeuCarrinho, 0, 2);
		
		btnComprar.setOnAction((e) -> {
			
		//	LivroBoundary tela = new LivroBoundary();
		//	try {
		//		tela.start(new Stage());
		//		CommonFunctions.fecharTela(stage);
				
		//	} catch (Exception e1) {
		//		e1.printStackTrace();
		//	}
		});
		
		CommonFunctions.tamanhoBotao(150, 80, btnComprar, btnMeuCarrinho);

		gp.add(btnFecharTela, 1, 10);
		CommonFunctions.tamanhoBotao(50, 20, btnFecharTela);
		
		btnFecharTela.setOnMouseClicked((e) -> {
			CommonFunctions.fecharTela(stage);
		});

		btnFecharTela.setOnKeyPressed((e) -> {
			if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
				CommonFunctions.fecharTela(stage);
			}
		});
		
		stage.setScene(scn);
		stage.setTitle("*** Tela Inicial - Cliente ***");
		stage.show();

	}

}
