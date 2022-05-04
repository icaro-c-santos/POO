package DAOs;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import entidades.Aluno;
import entidades.Chamada;
import entidades.Turma;

public class Dao_Chamada {
	
	
	public Long saveChamada(Chamada chamada){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	    em.persist(chamada);
	    em.getTransaction().commit();
	    em.close();
	    return chamada.getId();
	
	}

	public boolean updateChamada(Long id,List<Aluno> listAluno, Date data){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Chamada chamada = em.find(Chamada.class, id);
		if(chamada==null) { return false;}
		 em.merge(chamada);
		 if(listAluno!=null) {
			 chamada.setListAluno(listAluno);
		 }
		 if(data!= null) {
			 chamada.setData(data);
		 }
	     em.getTransaction().commit();
	     em.close();
	     return true;
	}
	
	public List<Chamada> getAll(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Chamada> query = em.createQuery("select u from Chamada u",Chamada.class);
	    List<Chamada> list = query.getResultList();
	    return list;
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> getChamadaDate(Date data){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Chamada> list = em.createQuery("SELECT c FROM Chamada c WHERE c.data LIKE :Cdata").setParameter("Cdata", data).getResultList();
	    return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Chamada> getChamadaTurma(Turma turma){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		List<Chamada> list = em.createQuery("SELECT c FROM Chamada c WHERE c.turma LIKE :Cturma").setParameter("Cturma", turma).getResultList();
	    return list;
	}

	public boolean deleteChamada(Long codigo) {
		
		if(codigo == null){return false;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Chamada chamada = em.find(Chamada.class,codigo);
		if(chamada != null) {
		    em.remove(chamada);
		    em.getTransaction().commit();
		    em.close();
		    return true;
		}
		em.getTransaction().commit();
	    em.close();
		return false;
	}
}