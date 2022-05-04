package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Chamada {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Aluno> listAluno;

	private Date data;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Turma turma;
	
	public Chamada() {
			
	}
	
	public Chamada(List<Aluno> listAluno, Date data, Turma turma) {
		this.listAluno = new HashSet<Aluno>(listAluno);;
		this.data = data;
		this.turma = turma;
	}
	

	public List<Aluno> getListAluno() {
		return  new ArrayList<Aluno>(listAluno);
	}

	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = new HashSet<Aluno>(listAluno);
	}
	
	public boolean addAlunoList(Aluno aluno) {
		return this.listAluno.add(aluno);
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CODIGO: "+getId()+"\nTurma: "+getTurma()+"\nData: "+getData()+"\nLista Alunos ||\n"+listAluno.toString()+"";
	}
	
	
}