package com.bigcorp.test.projetTest;

import com.bigcorp.booking.projet.dao.ReservationsDao;
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

import java.time.LocalDate;
import java.util.List;

@ExtendWith(ArquillianExtension.class)
public class ReservationsDaoTest {
    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "RestaurantTypeServiceIT.war")
                .addPackages(true, "com.bigcorp.booking")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ReservationsDao reservationsDao;

    @Test
    public void testCreateReservationDao() {
        Reservations reservation = new Reservations();
        reservation.setEmailConfirmation("user@use.com");

        Reservations reservationSaved = reservationsDao.save(reservation);
        Assertions.assertNotNull(reservationSaved);
        Assertions.assertNotNull(reservationSaved.getId());
        System.out.println("l'id de la réservation : " + reservationSaved.getId());

        Reservations reservationRegistred = reservationsDao.findReservationById(reservationSaved.getId());
        Assertions.assertEquals(1, reservationRegistred.getId());
        Assertions.assertEquals(reservation.getEmailConfirmation(), reservationRegistred.getEmailConfirmation());
    }

    @Test
    public void testDeleteReservationById() {
        Reservations reservation = new Reservations();
        Reservations reservationSaved = reservationsDao.save(reservation);
        Reservations reservationRegistred = reservationsDao.findReservationById(reservationSaved.getId());
        Assertions.assertNotNull(reservationRegistred);

        reservationsDao.deleteReservationById(reservationSaved.getId());
        reservationRegistred = reservationsDao.findReservationById(reservationSaved.getId());
        Assertions.assertNull(reservationRegistred);
    }

    @Test
    public void testSaveAndFindReservationByEmail() {
        Reservations reservation = new Reservations();
        reservation.setEmailConfirmation("user@use.com");

        Reservations reservationSaved = reservationsDao.save(reservation);
        List<Reservations> result = reservationsDao.findReservationByEmail(reservationSaved.getEmailConfirmation());
        Assertions.assertEquals(1, result.size());
        Reservations reservationRegistred = result.get(0);
        Assertions.assertEquals(reservation.getEmailConfirmation(), reservationRegistred.getEmailConfirmation());

    }

    @Test
    public void testSaveAndFindReservationByNameLike() {
        Reservations reservation = new Reservations();
        reservation.setEmailConfirmation("user@use.com");

        Reservations reservationSaved = reservationsDao.save(reservation);
        List<Reservations> result = reservationsDao.findReservationByEmailLike("use");
        Assertions.assertEquals(1, result.size());
        Reservations reservationRegistred = result.get(0);
        Assertions.assertEquals(reservation.getEmailConfirmation(), reservationRegistred.getEmailConfirmation());
    }


}
