package com.bigcorp.booking.cours.DAO;

import com.bigcorp.booking.cours.model.Client;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    //methode create
    @TransactionAttribute
    public Client save(Client client) {
        return entityManager.merge(client);
    }

    public Client findClientById(Integer id) {
        Client client = entityManager.find(Client.class, id);
        return client;
    }

    public List<Client> findAllClients() {
        return this.entityManager.createQuery("""
                select distinct c from Client c
                """, Client.class).getResultList();
    }

    @TransactionAttribute
    public void deleteClientById(Integer id) {
        Client client = entityManager.find(Client.class, id);
        if(client == null) {
            return;
        }
        entityManager.remove(client);
    }

    //methode pour recuperer a partir du nom
    public List<Client> findClientByName(String lastName) {
        TypedQuery<Client> query = entityManager.createQuery("select c from Client c where UPPER(c.lastName) = UPPER(:lastName)", Client.class);
        query.setParameter("lastName", lastName);
        List<Client> resultat = query.getResultList();
        return resultat;
    }

    public List<Client> findClientByNameLike(String lastName) {
        TypedQuery<Client> query = entityManager.createQuery("select c from Client c where LOWER(c.lastName) like LOWER(:lastName)", Client.class);
        query.setParameter("lastName", "%" + lastName + "%");
        List<Client> resultat = query.getResultList();
        return resultat;
    }

}
