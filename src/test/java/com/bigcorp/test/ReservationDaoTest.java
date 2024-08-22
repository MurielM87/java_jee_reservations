package com.bigcorp.test;

import com.bigcorp.booking.cours.DAO.ClientDao;
import com.bigcorp.booking.cours.DAO.ReservationDao;
import com.bigcorp.booking.cours.model.Client;
import com.bigcorp.booking.cours.model.Reservation;
import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(ArquillianExtension.class)
public class ReservationDaoTest {
/*
    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "RestaurantTypeServiceIT.war")
                .addPackages(true, "com.bigcorp.booking")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ReservationDao reservationDao;
    @Inject
    private ClientDao clientDao;

    @Test
    public void testReservationDao () {
        Reservation reservation = new Reservation();
        reservation.setEmail("jd@use.com");

        Reservation reservationSauvegardee = reservationDao.save(reservation);
        Assertions.assertNotNull(reservationSauvegardee);
        Assertions.assertNotNull(reservationSauvegardee.getId());
        System.out.println("id réservation : " + reservationSauvegardee.getId());

        Reservation reservationNotee = reservationDao.findReservationById(reservationSauvegardee.getId());
        Assertions.assertEquals(1, reservationNotee.getId());
        Assertions.assertEquals(reservation.getEmail(), reservationNotee.getEmail());
    }

    @Test
    public void testDeleteReservation() {
        Reservation reservation = new Reservation();
        Reservation reservationSauvegardee = reservationDao.save(reservation);
        Reservation reservationEnregistree = reservationDao.findReservationById(reservationSauvegardee.getId());
        Assertions.assertNotNull(reservationEnregistree);

        reservationDao.deleteReservationById(reservationSauvegardee.getId());
        reservationEnregistree = reservationDao.findReservationById(reservationSauvegardee.getId());
        Assertions.assertNull(reservationEnregistree);
    }

    @Test
    public void testSaveAndFindByEmail() {
        Reservation reservation = new Reservation();
        reservation.setEmail("use@use.com");
        System.out.println("essai");
        Reservation reservationSauvegardee = reservationDao.save(reservation);
        List<Reservation> result = reservationDao.findByEmail(reservationSauvegardee.getEmail());
        Assertions.assertEquals(1, result.size());
        Reservation reservationEnregistree = result.get(0);
        Assertions.assertEquals(reservation.getEmail(), reservationEnregistree.getEmail());
    }

    @Test
    public void testSaveAndFindByNameLike() {
        Reservation reservation = new Reservation();
        reservation.setEmail("use@use.com");

        Reservation reservationSauvegardee = reservationDao.save(reservation);
        List<Reservation> result = reservationDao.findByEmailLike("use");
        Assertions.assertEquals(1, result.size());
        Reservation reservationEnregistree = result.get(0);
        Assertions.assertEquals(reservation.getEmail(), reservationEnregistree.getEmail());
    }

    @Test
    public void testSaveWithClient() {
        //creation du client et save
        Client client = new Client();
        client.setLastName("Dupond");
        Client clientSauvegarde = clientDao.save(client);

        //creation de la reservation, la liée au client et save
        Reservation reservation = new Reservation();
        reservation.setClient(clientSauvegarde);
        reservationDao.save(reservation);
        System.out.println(reservation.getClient().getLastName());

    }

    @Test
    public void testFindReservationByClientLastName() {

        //creation du client et save
        Client client = new Client();
        client.setLastName("Dupond");
        Client clientSauvegarde = clientDao.save(client);

        //creation de la reservation, la liée au client et save
        Reservation reservation = new Reservation();
        reservation.setEmail("use@use.com");
        reservation.setClient(clientSauvegarde);
        reservationDao.save(reservation);

        //trouver la reservation par nom
        reservationDao.findClientByName(clientSauvegarde.getLastName());
        System.out.println("nom : " + clientSauvegarde.getLastName());

        Reservation reservationSauvegardee = reservationDao.save(reservation);
        List<Reservation> result = reservationDao.findClientByName("Dupond");
        System.out.println("nom : " + clientSauvegarde.getLastName());
        System.out.println("result : " + result);

        //Assertions
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(clientSauvegarde.getLastName(), result.get(0).getClient().getLastName());
        System.out.println("le nom de la réservation : " + result.get(0).getClient().getLastName());
    }

    @Test
    public void testFindReservationByClientId() {

        //creation du client et save
        Client client = new Client();
        client.setLastName("Dupond");
        Client clientSauvegarde = clientDao.save(client);

        //creation de la reservation, la liée au client et save
        Reservation reservation = new Reservation();
        reservation.setEmail("use@use.com");
        reservation.setClient(clientSauvegarde);
        reservationDao.save(reservation);

        //trouver la reservation par id
        reservationDao.findClientById(clientSauvegarde.getId());
        System.out.println("L'id du client : " + clientSauvegarde.getId());

        Reservation reservationSauvegardee = reservationDao.save(reservation);
        List<Reservation> result = reservationDao.findClientById(1);
        System.out.println("id client sauvegardé : " + clientSauvegarde.getId());
        System.out.println("result : " + result);

        //Assertions
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(clientSauvegarde.getId(), result.get(0).getClient().getId());
        System.out.println("l'id du client de la réservation : " + result.get(0).getClient().getId());
    }

    @Test
    public void testFindReservationsByClientId() {
        //creation du client et save
        Client client = new Client();
        client.setLastName("Dupond");
        Client clientSauvegarde = clientDao.save(client);

        //creation de la reservation, la liée au client et save
        Reservation reservation1 = new Reservation();
        reservation1.setEmail("user@use.com");
        reservation1.setClient(clientSauvegarde);
        reservationDao.save(reservation1);
        //creation deuxième reservation
        Reservation reservation2 = new Reservation();
        reservation2.setEmail("user2@use.com");
        reservation2.setClient(clientSauvegarde);
        reservationDao.save(reservation2);

        List<Reservation> result = reservationDao.findAllReservationsByClientId(clientSauvegarde.getId());
        System.out.println(result);

        // Afficher les résultats
        System.out.println("L'id du client : " + clientSauvegarde.getId());
        result.forEach(resa -> System.out.println("Réservation ID : " + resa.getId()));
        // Assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(clientSauvegarde.getId(), result.get(0).getClient().getId());
        System.out.println("L'email du client de la première réservation : " + result.get(0).getClient().getEmail());
        System.out.println("L'email du client de la deuxième réservation : " + result.get(1).getClient().getEmail());
    }*/

}
