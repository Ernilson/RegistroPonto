package br.com.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.Entity.MarcacoesFeitas;

public class MarcacoesFeitasDAO {

	 private EntityManagerFactory entityManagerFactory;

	    public MarcacoesFeitasDAO() {
	        entityManagerFactory = Persistence.createEntityManagerFactory("RegistroPontoUnit");
	    }

	    public void salvar(MarcacoesFeitas registroPonto) {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.persist(registroPonto);
	            entityManager.getTransaction().commit();
	        } catch (Exception e) {
	            entityManager.getTransaction().rollback();	        	
	        } finally {
	            entityManager.close();
	        }
	    }

	    public void atualizar(MarcacoesFeitas registroPonto) {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            entityManager.getTransaction().begin();
	            entityManager.merge(registroPonto);
	            entityManager.getTransaction().commit();
	        } catch (Exception e) {
	            entityManager.getTransaction().rollback();
	        } finally {
	            entityManager.close();
	        }
	    }
	    
	    public MarcacoesFeitas buscarPorCpf(String cpf) {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            TypedQuery<MarcacoesFeitas> query = entityManager.createQuery(
	                "SELECT m FROM MarcacoesFeitas m WHERE m.cpf = :cpf", MarcacoesFeitas.class);
	            query.setParameter("cpf", cpf);
	            List<MarcacoesFeitas> resultados = query.getResultList();
	            if (resultados.isEmpty()) {
	                return null;  // ou lançar uma exceção adequada
	            } else if (resultados.size() > 1) {
	                // Tratar o caso de mais de um resultado retornado, se necessário
	            }
	            return resultados.get(0);
	        } finally {
	            entityManager.close();
	        }
	    }



	    public List<MarcacoesFeitas> listarTodos() {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            String jpql = "SELECT m FROM MarcacoesFeitas m";
	            TypedQuery<MarcacoesFeitas> query = entityManager.createQuery(jpql, MarcacoesFeitas.class);
	            return query.getResultList();
	        } finally {
	            entityManager.close();
	        }
	    }

	    public void excluir(MarcacoesFeitas registroPonto) {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            entityManager.getTransaction().begin();
	            MarcacoesFeitas entity = entityManager.find(MarcacoesFeitas.class, registroPonto.getId());
	            if (entity != null) {
	                entityManager.remove(entity);
	            }
	            entityManager.getTransaction().commit();
	        } catch (Exception e) {
	            entityManager.getTransaction().rollback();
	        } finally {
	            entityManager.close();
	        }
	    }
	    
	    public void excluirPorCPF(String cpf) {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            entityManager.getTransaction().begin();
	            
	            // Consulta para encontrar os registros de ponto pelo CPF
	            Query query = entityManager.createQuery("SELECT h FROM HorarioDeTrabalho h WHERE h.cpf = :cpf");
	            query.setParameter("cpf", cpf);
	            List<MarcacoesFeitas> registros = query.getResultList();
	            
	            // Remover os registros encontrados
	            for (MarcacoesFeitas registro : registros) {
	                entityManager.remove(registro);
	            }
	            
	            entityManager.getTransaction().commit();
	        } catch (Exception e) {
	            entityManager.getTransaction().rollback();
	        } finally {
	            entityManager.close();
	        }
	    }

	   

}
