/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController.beans;


import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.DTO.Clase;

/**
 *
 * @author lchinchilla
 */
public class ClaseJpaController implements Serializable {

    public ClaseJpaController(EntityManagerFactory _emf) {
        this.emf = javax.persistence.Persistence.createEntityManagerFactory("ControlClasesLibPU");        
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
     return emf.createEntityManager();
     }
    public void create(Clase clase) {
        EntityManager em = null;
        try {
            //em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clase clase) throws  Exception {
        EntityManager em = null;
        try {
            //em = getEntityManager();
            em.getTransaction().begin();
            clase = em.merge(clase);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clase.getCodigo();
                if (findClase(id) == null) {
                    throw new Exception("The clase with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws Exception {
        EntityManager em = null;
        try {
            //em = getEntityManager();
            em.getTransaction().begin();
            Clase clase;
            try {
                clase = em.getReference(Clase.class, id);
                clase.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The clase with id " + id + " no longer exists.", enfe);
            }
            em.remove(clase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clase> findClaseEntities() {
        return findClaseEntities(true, -1, -1);
    }

    public List<Clase> findClaseEntities(int maxResults, int firstResult) {
        return findClaseEntities(false, maxResults, firstResult);
    }

    private List<Clase> findClaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clase.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
                            
            }
            catch(Exception ex){
            ex.printStackTrace();    
            }
         finally {
            em.close();
        }
        return null;
    }

    public Clase findClase(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clase.class, id);
        } finally {
            em.close();
        }
    }

    public List<Clase> findByReferenciaClase(String _referencia) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clase.class));
            Query q = em.createNamedQuery("Clase.findByReferenciaClase");
            q.setParameter("nombreClase", _referencia);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Clase> findByInfoClase(String... _criterio) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clase.class));
            Query q = em.createNamedQuery("Clase.findByReferenciaClase");
            q.setParameter("nombreClase", _criterio[0]);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getClaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clase> rt = cq.from(Clase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
