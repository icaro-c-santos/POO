package Telas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import DAOs.Dao_Aluno;
import Utilitarios.Utilitario;
import br.com.caelum.stella.validation.CPFValidator;
import entidades.Aluno;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

public class Tela_Aluno {

	public Tela_Aluno() {
	
	}
	public static Utilitario utilitario = new Utilitario();
	public void alert(Stage primaryStage,String mensagem) {
		
		Alert alerta = new Alert(AlertType.INFORMATION);
    	alerta.setContentText(mensagem);
    	alerta.show();
	}
	
	public void star(Stage primaryStage) {
		  
		  primaryStage.close();
		  Button botaoCadastrarAluno = new Button("CADASTRAR ALUNO");
		  Button botaoVerAluno = new Button("CONSULTAR ALUNO!");
		  botaoCadastrarAluno.setOnAction( n -> {
			 this.cadastrarAluno(primaryStage); 
		    });
		  botaoVerAluno.setOnAction( n -> {
				 this.consularAluno(primaryStage);
			    });
		    
			HBox box = new HBox();
		    box.setAlignment(Pos.CENTER);
		    box.setSpacing(20);
			box.getChildren().add(botaoCadastrarAluno);
			box.getChildren().add(botaoVerAluno);
			Scene cenaUnica = new Scene(box);
			primaryStage.setWidth(800);
			primaryStage.setHeight(600);
			primaryStage.setScene(cenaUnica);
		    primaryStage.show();
	}
	
	
	
	public void cadastrarAluno(Stage primaryStage) {
		
		primaryStage.close();
        Button botaoCadastrarAluno = new Button("CADASTRAR ALUNO");
        Button botaoVoltar = new Button("VOLTAR");
	    
        TextField nome  = new  TextField("NOME");
	    TextField cpf  = new  TextField("cpf");
	    TextField email  = new  TextField("email");
	    HBox data = new HBox();
	    TextField dia = new TextField("DIA");
	    TextField mes = new TextField("MES");
	    TextField ano = new TextField("ANO");
	    dia.setMaxWidth(40);
	    mes.setMaxWidth(40);
	    ano.setMaxWidth(40);
	    
	    data.getChildren().addAll(dia,mes,ano);
	    data.setAlignment(Pos.CENTER);
	    
	    nome.setMaxWidth(200);
	    cpf.setMaxWidth(200);
	    email.setMaxWidth(200);
	    
	    botaoCadastrarAluno.setMaxWidth(200);
	   
	    botaoVoltar.setOnAction( n -> {
	    	this.star(primaryStage);
		});
	    
	    botaoCadastrarAluno.setOnAction( n -> {
	    	
	        List<String> msgErro = new ArrayList<String>();
	    	String nameUser = nome.getText();
	    	String cpfUser = cpf.getText();
	    	String emailUser = email.getText();
	    	Date date = new Date();
			try {
			  	@SuppressWarnings("deprecation")
			 	Date dt  = new Date(Integer.parseInt(ano.getText()),Integer.parseInt(mes.getText()),Integer.parseInt(dia.getText()));
			  	@SuppressWarnings("deprecation")
				Date dateMin = new Date(1920,1, 1);
		    	Date dateMax = new Date();
			  	if(date == null  || date.after(dateMax) || date.before(dateMin)) {
		    	    msgErro.add("ERRO: DATA INVALIDA");
		    	}
			  	date = dt;
			}catch (Exception e) {
				 msgErro.add("ERRO: DATA INVALIDA");
			}
	    	
	    	

	    	if(cpfUser == null  || !utilitario.validarCpf(cpfUser)) {
	    		 msgErro.add("ERRO: CPF INVALIDO");
	    	}
	    	
	    	if(nameUser == null || nameUser =="") {
	    		 msgErro.add("ERRO: NOME INVALIDO");
	    	}
	    	
	    	if(emailUser == null || !utilitario.validarEmail(emailUser)) {
	    		 msgErro.add("ERRO: EMAIL INVALIDO");
	    	}
	    	if(msgErro.isEmpty()) {
	    		Dao_Aluno da = new Dao_Aluno();
		    	Aluno aluno = new Aluno(nameUser,date,cpfUser,emailUser);
		    	Long id = da.saveAluno(aluno);
		    	Alert alerta = new Alert(AlertType.INFORMATION);
		    	alerta.setContentText("ALUNO CADASTRADO COM SUCESSO!\n MATRICULA: "+id);
		    	alerta.show();
		    	nome.clear();
		    	cpf.clear();
		    	email.clear();
		    	dia.clear();
		    	mes.clear();
		    	ano.clear();
	    	}else {
	    		String erro ="";
	    			
	    		for (int i=0; i< msgErro.size(); i++) {
	    			erro = erro+"\n"+msgErro.get(i);
	    		}
	    		alert(primaryStage, erro);
	    		msgErro.clear();
	    	}
	    	
		});
	    
	    
	    VBox box = new VBox();
	    box.setAlignment(Pos.CENTER);
	    box.setSpacing(20);
		box.getChildren().addAll(nome,data,cpf,email,botaoCadastrarAluno,botaoVoltar);
		
		Scene cenaCadastroAluno = new Scene(box);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setScene(cenaCadastroAluno);
	    primaryStage.show();
	    
	  
	    
	}
	    
	    public void consularAluno(Stage primaryStage) {
			
			  primaryStage.close();
			  TextField matricula = new TextField("MATRICULA DO ALUNO");
			  matricula.setPromptText("MATRICULA DO ALUNO");
			  Button consultar = new Button("Consultar Aluno");
			  Button voltar = new Button("Voltar");
			    voltar.setOnAction( n -> {
				 	star(primaryStage);
			    });
			    consultar.setOnAction( n -> {
				 	
			    	Dao_Aluno da = new Dao_Aluno();
			    	Aluno aluno = da.getAlunoMatricula(Long.parseLong(matricula.getText()));
			    	Alert alerta = new Alert(AlertType.INFORMATION);
			    	if(aluno != null) {
			    		alerta.setContentText(aluno.toString());
			    	}else {
			    		alerta.setContentText("ALUNO N�O ENCONTRADO!");
			    	}
			    
			    	alerta.show();
			    	
			    });
			  
			    consultar.setMaxWidth(200);
			    voltar.setMaxWidth(200);
			    matricula.setMaxWidth(200);
			    HBox boxh = new HBox();
			    boxh.setAlignment(Pos.CENTER);
			    boxh.setSpacing(40);
			    boxh.getChildren().addAll(voltar,consultar);
				VBox box = new VBox();
			    box.setAlignment(Pos.CENTER);
			    box.setSpacing(20);
				box.getChildren().addAll(matricula,boxh);
				Scene cenaUnica = new Scene(box);
				primaryStage.setWidth(800);
				primaryStage.setHeight(600);
				primaryStage.setScene(cenaUnica);
			    primaryStage.show();	
			    
			
		}
	    
}