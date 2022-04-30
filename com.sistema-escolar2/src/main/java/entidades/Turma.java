package entidades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Disciplina disciplina;
	
	@OneToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
	private Professor professor;
	
	
	@OneToMany(cascade =  {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	private Set<Aluno> listAluno;
	
	public Turma() {
		
	}
	
	public Turma(Disciplina disciplina,Professor professor) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.listAluno = new HashSet<Aluno>();
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Aluno> getListAluno() {
		return new HashSet<Aluno>(listAluno);
	}
	
	public void setListAluno(Set<Aluno> newLista) {
		if(newLista !=null) {
			this.listAluno = newLista;
		}
	}

	public boolean addAluno(Aluno Aluno) {
		return this.listAluno.add(Aluno);
	}

	public Long getId() {
		return id;
	}

	
}
