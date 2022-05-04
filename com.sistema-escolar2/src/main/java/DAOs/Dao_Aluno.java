package DAOs;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entidades.Aluno;

public class Dao_Aluno {
	
	public Long saveAluno(Aluno aluno){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	    em.persist(aluno);
	    em.getTransaction().commit();
	    em.close();
	    return aluno.getMatricula();
	
	}
	
	public boolean updateAluno(Long matricula,String nome,Date datanascimento,String cpf){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Aluno aluno = em.find(Aluno.class, matricula);
		if(aluno==null) { return false;}
		 if(nome!=null) {
			 aluno.setNome(nome);
		 }
		 
		 if(datanascimento!=null) {
			 aluno.setNascimento(datanascimento);
		 }
		 if(cpf!= null) {
			 aluno.setCpf(cpf);
			 
		 }
		 em.merge(aluno);
	     em.getTransaction().commit();
	     em.close();
	     
	     return true;
	
}
	
	public List<Aluno> getAll(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Aluno> query = em.createQuery("select u from Aluno u",Aluno.class);
	    List<Aluno> list = query.getResultList();
	    return list;
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> getAlunoNome(String nome){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Aluno> list = em.createQuery("SELECT c FROM Aluno c WHERE c.nome LIKE :Cnome").setParameter("Cnome", nome).getResultList();
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> getAlunoCpf(String cpf){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Aluno> list = em.createQuery("SELECT c FROM Aluno c WHERE c.cpf LIKE :Cparamet").setParameter("Cparamet", cpf).getResultList();
	    return list;
	}
	
	public Aluno getAlunoMatricula(Long matricula){
		if(matricula== null) { return null;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
	    return em.find(Aluno.class, matricula);
	}

	public boolean deleteAluno(Long matricula) {
		
		if(matricula == null){return false;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Aluno alunoExcluir = em.find(Aluno.class,matricula);
		if(alunoExcluir != null) {
		    em.remove(alunoExcluir);
		    em.getTransaction().commit();
		    em.close();
		    return true;
		}
		em.getTransaction().commit();
	    em.close();
		return false;
	}
	
}


