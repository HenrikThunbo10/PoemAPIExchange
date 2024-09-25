package app.DAOs;

import app.DTOs.PoemDTO;
import app.entities.Poem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PoemDAO
{
    private EntityManagerFactory emf;

    public PoemDAO(EntityManagerFactory emf)
    {
        this.emf = emf;
    }


    public Set<Poem> getAllPoems()
    {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Poem> query = em.createQuery("SELECT p FROM Poem p", Poem.class);
            List<Poem> poemList = query.getResultList();
            return poemList.stream().collect(Collectors.toSet());
        }
    }


    public Poem getPoemById(long id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.find(Poem.class, id);
        }

    }

    public void createPoem(PoemDTO poemDTO)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Poem poem = new Poem(poemDTO.getTitle(), poemDTO.getPoem(), poemDTO.getAuthor(), poemDTO.getType());
            em.persist(poem);
            em.getTransaction().commit();
        }

    }

    public Poem updatePoem(long id, Poem updatedPoem)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Poem poemToBeUpdated = getPoemById(id);
            if (poemToBeUpdated != null)
            {
                poemToBeUpdated.setTitle(updatedPoem.getTitle());
                poemToBeUpdated.setPoem(updatedPoem.getPoem());
                poemToBeUpdated.setAuthor(updatedPoem.getAuthor());
                poemToBeUpdated.setType(updatedPoem.getType());
                em.merge(poemToBeUpdated);
                em.getTransaction().commit();
                return poemToBeUpdated;
            }
            em.getTransaction().rollback();
            return null;
        }
    }

    public void deletePoem(long id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Poem poem = em.find(Poem.class, id);
            if (poem != null)
            {
                em.remove(poem);
                em.getTransaction().commit(); // Complete the transaction only when deletion is successful
            } else
            {
                em.getTransaction().rollback(); // Rollback only if necessary
            }
        } catch (Exception e)
        {
            // Add logging here if necessary
            throw e;
        }
    }
}
