package DAOs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import entidades.Aluno;
import entidades.Disciplina;

import entidades.Turma;

public class Dao_Turma {
	
	public Dao_Turma() {
		
	}
	
	
	public Long saveTurma(Turma turma){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	    em.persist(turma);
	    em.getTransaction().commit();
	    em.close();
	    return turma.getId();
	
	}
	
	public boolean updateTurma(Long codigo,Turma newTurma){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Turma turma = em.find(Turma.class, codigo);
		if(turma==null) { return false;}
		 if(newTurma.getDisciplina()!=null) { 
			 turma.setDisciplina(newTurma.getDisciplina());
		 }

		 if(newTurma.getProfessor()!= null) {
			 turma.setProfessor(newTurma.getProfessor());
		 }
		 if(newTurma.getListAluno() != null) {
			turma.setListAluno(newTurma.getListAluno());
		 }
		 em.merge(turma);
		 em.getTransaction().commit();
	     em.close();
	     return true;
}
	
	public List<Turma> getAll(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Turma> query = em.createQuery("select u from Turma u",Turma.class);
	    List<Turma> list = query.getResultList();
	    return list;
	}

	@SuppressWarnings("unchecked")
	public List<Turma> getTurmaDisciplina(Disciplina disciplina){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Turma> list = em.createQuery("SELECT c FROM Turma c WHERE c.disciplina LIKE :Cdisciplina").setParameter("Cdisciplina", disciplina).getResultList();
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Turma> getTurmaAlnuo(String matricula){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Turma> list = em.createQuery("SELECT c FROM Turma c WHERE c.aluno.matricula LIKE :Cparamet").setParameter("Cparamet",matricula).getResultList();
	    return list;
	}
	
	public Turma getTurmaCodigo(Long codigo){
		if(codigo== null) { return null;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
	    return em.find(Turma.class, codigo);
	}

	public boolean deleteTurma(Long codigo) {
		
		if(codigo == null){return false;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Turma turmaExcluir = em.find(Turma.class,codigo);
		if(turmaExcluir != null) {
		    em.remove(turmaExcluir);
		    em.getTransaction().commit();
		    em.close();
		    return true;
		}
		em.getTransaction().commit();
	    em.close();
		return false;
	}
	
	
	
	
}
