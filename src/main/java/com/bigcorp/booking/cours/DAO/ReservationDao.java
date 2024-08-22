package com.bigcorp.booking.cours.DAO;

import com.bigcorp.booking.cours.model.Reservation;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;


    public Reservation findReservationById(Integer id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        return reservation;
    }

    @TransactionAttribute
    public Reservation save(Reservation reservation) {
        return entityManager.merge(reservation);
    }

    public void deleteReservationById(Integer id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
    }
    //methode pour trouver par email dans la Class Reservation
    public List<Reservation> findReservationByEmail(String emailContact) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                    select resa from Reservation resa
                    where UPPER(r.emailContact) = UPPER(:emailContact)
                """, Reservation.class);
        query.setParameter("emailContact", emailContact);
        return query.getResultList();
    }

    public List<Reservation> findReservationByEmailLike(String emailContact) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                    select resa from Reservation resa
                    where LOWER(r.emailContact) like LOWER(:emailContact)
                """, Reservation.class);
        query.setParameter("emailContact", "%" + emailContact + "%");
        return query.getResultList();
    }

    //methode pour trouver toutes les reservations
    public List<Reservation> findAllReservations() {
        return this.entityManager.createQuery("""
                    select resa from Reservation resa
                """, Reservation.class).getResultList();
    }

    //methode pour trouver reservation par nom à partir de la Class Client
    public List<Reservation> findReservationByClientLastName(String lastName) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                    select resa from Reservation resa
                    inner join fetch resa.client
                    where upper(client.lastName) = upper(:lastName)
                """, Reservation.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    //methode pour trouver reservation à partir de l'id de la Class Client
    public List<Reservation> findReservationByClientId(Integer clientId) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
            select resa from Reservation resa
            where resa.client.id = :clientId
        """, Reservation.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    //methode pour trouver toutes les reservations d'un client
    //(et afficher les mails de contact d'un client)
    public List<Reservation> findAllReservationsByIdClient(Integer id) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                select resa from Reservation resa
                left join fetch resa.client
                where client.id = :id
            """, Reservation.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    //methode pour trouver avec le ClientId de la reservation
    public List<Reservation> findReservationWithClientId (Integer clientId) {
        TypedQuery<Reservation> query = entityManager.createQuery("""
                select resa from Reservation resa
                where resa.clientId = :client.id
                """, Reservation.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

}