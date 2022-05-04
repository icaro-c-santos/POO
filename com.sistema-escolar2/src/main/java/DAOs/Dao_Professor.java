package DAOs;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entidades.Professor;


public class Dao_Professor {
	

	
	public Long saveProfessor(Professor professor){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	    em.persist(professor);
	    em.getTransaction().commit();
	    em.close();
	    return professor.getCodigo();
	
	}
	
	public boolean updateProfessor(Long codigo,String nome,Date datanascimento,String cpf){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Professor professor = em.find(Professor.class, codigo);
		if(professor==null) { return false;}
		 em.merge(professor);
		 
		 if(nome!=null) {
			 professor.setNome(nome);
		 }
		 
		 if(datanascimento!=null) {
			 professor.setNascimento(datanascimento);
		 }
		 if(cpf!= null) {
			 professor.setCpf(cpf);
			 
		 }
	     em.getTransaction().commit();
	     em.close();
	     
	     return true;
	
}
	
	public List<Professor> getAll(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Professor> query = em.createQuery("select u from Professor u",Professor.class);
	    List<Professor> list = query.getResultList();
	    return list;
	}

	@SuppressWarnings("unchecked")
	public List<Professor> getProfessorNome(String nome){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Professor> list = em.createQuery("SELECT c FROM Professor c WHERE c.nome LIKE :Cnome").setParameter("Cnome", nome).getResultList();
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> getProfessorCpf(String cpf){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Professor> list = em.createQuery("SELECT c FROM Professor c WHERE c.cpf LIKE :Cparamet").setParameter("Cparamet", cpf).getResultList();
	    return list;
	}
	
	public Professor getProfessorMatricula(Long codigo){
		if(codigo== null) { return null;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
	    return em.find(Professor.class, codigo);
	}

	public boolean deleteProfessor(Long codigo) {
		
		if(codigo == null){return false;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Professor professor = em.find(Professor.class,codigo);
		if(professor != null) {
		    em.remove(professor);
		    em.getTransaction().commit();
		    em.close();
		    return true;
		}
		em.getTransaction().commit();
	    em.close();
		return false;
	}
	
}

