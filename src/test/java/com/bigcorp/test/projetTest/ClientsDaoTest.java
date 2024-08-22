package com.bigcorp.test.projetTest;

import com.bigcorp.booking.projet.dao.ClientsDao;
import com.bigcorp.booking.projet.dao.ReservationsDao;
import com.bigcorp.booking.projet.model.Clients;
import com.bigcorp.booking.projet.model.Reservations;
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
public class ClientsDaoTest {

    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "ClientsDaoTest.war")
                .addPackages(true, "com.bigcorp.booking")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ClientsDao clientsDao;

    @Inject
    private ReservationsDao reservationsDao;

    @Test
    public void testClientsDaoCreateById() {
        Clients client = new Clients();
        client.setLastName("Doe");
        client.setFirstName("Jane");
        client.setEmail("user@use.com");

        Clients clientSaved = clientsDao.save(client);
        Assertions.assertNotNull(clientSaved);
        Assertions.assertNotNull(clientSaved.getId());
        System.out.println("l'id du client : " + clientSaved.getId());

        Clients clientRegistred = clientsDao.findClientById(clientSaved.getId());
        System.out.println(clientRegistred);

        Assertions.assertEquals(clientSaved.getId(), clientRegistred.getId());
        Assertions.assertEquals(clientSaved.getLastName(), clientRegistred.getLastName());
    }

    @Test
    public void testDeleteClientById() {
        Clients client = new Clients();
        Clients clientSaved = clientsDao.save(client);
        Clients clientRegistred = clientsDao.findClientById(clientSaved.getId());
        Assertions.assertNotNull(clientRegistred);

        clientsDao.deleteClientById(clientRegistred.getId());
        clientRegistred = clientsDao.findClientById(clientSaved.getId());
        Assertions.assertNull(clientRegistred);
    }

    @Test
    public void testSaveAndFindClientByName() {
        // Create a new client
        Clients client = new Clients();
        client.setFirstName("Jane");
        client.setLastName("Danamimosa");
        client.setEmail("user@use.com");

        // Save client
        Clients clientSaved = clientsDao.save(client);
        List<Clients> result = clientsDao.findClientByName(clientSaved.getLastName());
        Assertions.assertEquals(1, result.size());
        Clients clientRegistered = result.get(0);
        Assertions.assertEquals(client.getLastName(), clientRegistered.getLastName());
        System.out.println("nom client : " + client.getLastName());
        System.out.println("nom enregistré : " + clientRegistered.getLastName());
    }

    @Test
    public void testSaveAndFindByNameLike() {
        //creation client
        Clients client = new Clients();
        client.setFirstName("Jane");
        client.setLastName("Danamimosa");
        client.setEmail("user@use.com");

        //save client
        Clients clientSaved = clientsDao.save(client);
        List<Clients> result = clientsDao.findClientByNameLike("mimo");
        Assertions.assertEquals(1, result.size());
        Clients clientRegistred = result.get(0);
        Assertions.assertEquals(client.getLastName(), clientRegistred.getLastName());
    }

    @Test
    public void testFindReservationWithClient() {
        //create and save a reservation
        Reservations reservation = new Reservations();
        reservation.setEmailConfirmation("user@use.com");
        Reservations reservationSaved = reservationsDao.save(reservation);

        //create and save a client
        Clients client = new Clients();
        client.setEmail("user@use.com");
        client.setReservation(reservationSaved);
        clientsDao.save(client);
        System.out.println("client " + client.getReservation().getEmailConfirmation());

    }

    @Test
    public void testFindReservationByClientEmail() {
        //create a reservation and save
        Reservations reservation = new Reservations();
        reservation.setEmailConfirmation("user@use.com");
        Reservations reservationSaved = reservationsDao.save(reservation);
        //create a client and link with the reservation
        Clients client = new Clients();
        client.setEmail("user@use.com");
        client.setReservation(reservationSaved);
        Clients clientSaved = clientsDao.save(client);
        // Find client by email confirmation of the reservation
        List<Clients> result = clientsDao.findReservationByEmail(reservationSaved.getEmailConfirmation());

        // Print results for debugging
        System.out.println("email confirmation: " + reservationSaved.getEmailConfirmation());
        System.out.println("email client : " + client.getEmail());

        // Validate the result
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(clientSaved.getEmail(), result.get(0).getReservation().getEmailConfirmation());
        System.out.println("email de la réservation: " + result.get(0).getReservation().getEmailConfirmation());
    }


}
