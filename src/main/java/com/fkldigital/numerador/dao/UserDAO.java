package com.fkldigital.numerador.dao;

import com.fkldigital.numerador.bean.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author fabioklopes [fabioklopes@live.com]
 */
public class UserDAO {

    private static User instance;
    protected EntityManager em;

    
    public static User getInstance(){
         if (instance == null){
            instance = new User();
         }

         return instance;
       }

       public UserDAO() {
         em = getEntityManager();
       }

       private EntityManager getEntityManager() {
        EntityManagerFactory factory =
        Persistence.createEntityManagerFactory("crudHibernatePU");
        if (em == null) {
          em = factory.createEntityManager();
        }

        return em;
       }

    public List<User> findAll() {
        // este retorno pega o nome da classe pelo seu próprio nome
        return em.createQuery("FROM " + User.class.getName()).getResultList();
    }

    public void persist(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            System.out.println("Transaction has been successfully");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void remove(User user) {
        try {
            em.getTransaction().begin();
            user = em.find(User.class, user.getId());
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void merge(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public User getById(final int id) {
        return em.find(User.class, id);
    }

    public void removeById(final int id) {
        try {
            User user = getById(id);
            remove(user);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
