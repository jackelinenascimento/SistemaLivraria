package livro;

import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
	
	private LivroControl control = new LivroControl();
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void start(Stage stage) throws Exception {
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(20, 0, 20, 20));
        gp.setHgap(7);
        gp.setVgap(7);
	    
        Scene scn = new Scene(gp, 400, 400);
	    
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
	    
	    Label valor = new Label("Valor");
	    gp.add(valor, 2, 4);
	    GridPane.setHalignment(valor, HPos.RIGHT);
	    gp.add(txtValor, 3, 4);
	    txtValor.setMaxWidth(100);

	
	    gp.add(new Label("Quantidade"), 0, 5);
	    gp.add(txtQtdadeExemplares, 1, 5);
	    txtQtdadeExemplares.setMaxWidth(100);
	    
	    gp.add(btnConsultar, 0, 6);
	    gp.add(btnIncluir, 1, 6);
	    gp.add(btnAlterar, 2, 6);
	    gp.add(btnExcluir, 3, 6);
	    
	    stage.setScene(scn);
        stage.setTitle("CRUD de Livros");
        stage.show();
	}
	
	 public static void main(String[] args) {
	        Application.launch(LivroBoundary.class, args);
	    }
}
