package com.bigcorp.booking.cours.DAO;

import com.bigcorp.booking.cours.model.Restaurant;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class RestaurantDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Restaurant findRestaurantById(Integer id) {
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        return restaurant;
    }

    //merge pour update - sauvegarder un restaurant ou le creer
    @TransactionAttribute
    public Restaurant save(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    //methode delete par id - ne fait rien si le resto n'existe pas en base
    @TransactionAttribute
    public void delete(Integer id) {
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        if(restaurant == null) {
            return;
        }
        entityManager.remove(restaurant);
    }

    //methode pour recuperer a partir du nom
    public List<Restaurant> findByNom(String nom) {
        TypedQuery<Restaurant> query = entityManager.createQuery("select r from Restaurant r where r.nom = :nom", Restaurant.class);
        query.setParameter("nom", nom);
        List<Restaurant> resultat = query.getResultList();
        return resultat;
    }

    //methode pour recuperer a partir du nom
    public List<Restaurant> findByNomLike(String nom) {
        TypedQuery<Restaurant> query = entityManager.createQuery("""
                                                 select r from Restaurant r where r.nom like :nom
                                                 """, Restaurant.class);
        query.setParameter("nom", "%" + nom + "%");
        List<Restaurant> resultat = query.getResultList();
        return resultat;
    }

    //methode Update par id //possible par name et autre...
    public int deleteByIdWithJpql(Integer id) {
        Query query = entityManager.createQuery("delete r from Restaurant r where r.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    //methode pour recuperer a partir du nom
    public List<Restaurant> findRestaurantWithRestaurantTypeById(Integer id) {
        TypedQuery<Restaurant> query = entityManager.createQuery("""
                                                 select r 
                                                 from Restaurant r 
                                                 left join fetch r.restaurantType
                                                 where r.id = :id
                                                 """, Restaurant.class);
        query.setParameter("id", id);
        List<Restaurant> resultat = query.getResultList();
        if(resultat.isEmpty()) {
            return null;
        }
        resultat.get(0);
        return resultat;
    }
}
