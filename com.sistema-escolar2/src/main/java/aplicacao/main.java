package aplicacao;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder.In;

import DAOs.Dao_Aluno;
import DAOs.Dao_Disciplina;
import DAOs.Dao_Professor;
import DAOs.Dao_Turma;
import entidades.Aluno;
import entidades.Chamada;
import entidades.Disciplina;
import entidades.Professor;
import entidades.Turma;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class main extends Application{
	
	
	static void  n() {
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		
	Aluno a1 = new Aluno("ICARO DA CONCEI��O SANTOS",new Date(1997, 12, 24),"070.783.325-63","icarosalna@hotmail.com");
	Aluno a2 = new Aluno("CALIMA OLIVEIRA SILVA",new Date(1997, 12, 24),"070.783.325-63","Camila@hotmail.com");
	Professor p1 = new Professor("PAULO CESA",new Date(1997, 12, 24),"7854659", "PAULOCESA@HOTMAIL.COM");
	Disciplina d1 = new Disciplina("MATEMATICA", "MUITO LEGAL ALGEBRA");
	Turma t2 = new Turma(d1, p1);
	Dao_Aluno Da = new Dao_Aluno();
	Dao_Professor Dp = new Dao_Professor();
	Dao_Disciplina Dd = new Dao_Disciplina();
	Dao_Turma Dt = new Dao_Turma();
	

	 Turma t1 = Dt.getTurmaCodigo(1L);
	 t1.addAluno(Da.getAlunoMatricula(4L));
	 Dt.updateTurma(t1.getId(), t1);
	//Da.saveAluno(a1);
	
	
	
	//t2.addAluno(a2);
	//Dp.saveProfessor(p1);
	//t2.setProfessor(Dp.getProfessorMatricula(2L));
	//Dt.updateTurma(1L,t2);
	//Dt.saveTurma(t1);

	//	Button a = new Button("INICIO");
			//Turma t1 =  new Turma();
			
			
			//VBox box = new VBox();
			//box.setAlignment(Pos.CENTER);
			//Button b = new Button("FIM");
			//box.getChildren().add(a);
			//box.getChildren().add(b);
			//Scene cenaUnica = new Scene(box);
			//primaryStage.setScene(cenaUnica);
		//	primaryStage.show();
			
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
			
		launch(args);
		
	}

	

}
