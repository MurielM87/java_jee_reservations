package com.bigcorp.test;

import com.bigcorp.booking.cours.DAO.ClientDao;
import com.bigcorp.booking.cours.model.Client;
import com.bigcorp.booking.cours.model.Position;
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
public class ClientDaoTest {

    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "RestaurantTypeServiceIT.war")
                .addPackages(true, "com.bigcorp.booking")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ClientDao clientDao;


    @Test
    public void testClientDao() {

        //Client client = clientDao.findClientById(1);
        //Assertions.assertNull(client);

        Client client = new Client();
        client.setFirstName("Jane");
        client.setLastName("Doe");
        client.setEmail("jd@use.com");
        client.setPosition(Position.AVANT);

        Client clientSauvegarde = clientDao.save(client);
        Assertions.assertNotNull(clientSauvegarde);
        Assertions.assertNotNull(clientSauvegarde.getId());
        System.out.println("id client : " + clientSauvegarde.getId());

        Client clientCharge = clientDao.findClientById(clientSauvegarde.getId());
        System.out.println(clientCharge);

        Assertions.assertEquals(1, clientCharge.getId());
        Assertions.assertEquals("Doe", clientCharge.getLastName());
        Assertions.assertEquals("Jane", clientCharge.getFirstName());
        Assertions.assertEquals("jd@use.com", clientCharge.getEmail());
        //autre solution pour tester
        Assertions.assertEquals(clientSauvegarde.getFirstName(), clientCharge.getFirstName());
        Assertions.assertEquals(client.getPosition(), clientCharge.getPosition());

    }

    @Test
    public void testDeleteClient() {
        Client client = new Client();

        Client clientSauvegarde = clientDao.save(client);
        Client clientEnBase = clientDao.findClientById(clientSauvegarde.getId());
        Assertions.assertNotNull(clientEnBase);

        clientDao.deleteClientById(clientSauvegarde.getId());
        clientEnBase = clientDao.findClientById(clientSauvegarde.getId());
        Assertions.assertNull(clientEnBase);

    }

    @Test
    public void testSaveAndFindByName() {
        //creation client
        Client client = new Client();
        client.setFirstName("Jane");
        client.setLastName("Danamimosa");
        client.setEmail("jd@use.com");

        //sauvegarde client
        Client clientSauvegarde = clientDao.save(client);
        List<Client> resultat = clientDao.findClientByName(clientSauvegarde.getLastName());
        Assertions.assertEquals(1, resultat.size());
        Client clientCharge = resultat.get(0);
        Assertions.assertEquals(client.getLastName(), clientCharge.getLastName());
    }

    @Test
    public void testSaveAndFindByNameLike() {
        //creation client
        Client client = new Client();
        client.setFirstName("Jane");
        client.setLastName("Danamimosa");
        client.setEmail("jd@use.com");

        //sauvegarde client
        Client clientSauvegarde = clientDao.save(client);
        List<Client> resultat = clientDao.findClientByNameLike("mimo");

        Assertions.assertEquals(1, resultat.size());
        Client clientCharge = resultat.get(0);
        Assertions.assertEquals(client.getLastName(), clientCharge.getLastName());
    }

}
