package com.bigcorp.test;

import com.bigcorp.booking.cours.DAO.RestaurantDao;
import com.bigcorp.booking.cours.model.Prix;
import com.bigcorp.booking.cours.model.Restaurant;
import com.bigcorp.booking.dao.RestaurantTypeDao;
import com.bigcorp.booking.model.RestaurantType;
import com.bigcorp.booking.service.RestaurantTypeService;
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
public class RestaurantDaoTest {
/*
	@Deployment
	public static WebArchive createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "RestaurantTypeServiceIT.war")
				.addPackages(true, "com.bigcorp.booking")
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	private RestaurantDao restaurantDao;
	@Inject
	private RestaurantTypeDao restaurantTypeDao;

	@Test
	public void testSaveAndGet() {
		//Assertions.assertNotNull(restaurantDao);

//		Restaurant restaurant = restaurantDao.findRestaurantById(1);
//		Assertions.assertNull(restaurant);

		Restaurant restaurant = new Restaurant();
		restaurant.setAdresse("666 rue de la paix");
		restaurant.setNom("taverne typique");
		restaurant.setPrix(Prix.MOYEN);

		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);
		Assertions.assertNotNull(restaurantSauvegarde);
		Assertions.assertNotNull(restaurantSauvegarde.getId());
		System.out.println("restaurant sauvegardé" + restaurantSauvegarde.getId());

		Restaurant restaurantCharge = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());

		Assertions.assertEquals(1, restaurantCharge.getId());
		Assertions.assertEquals("666 rue de la paix", restaurantCharge.getAdresse());
		Assertions.assertEquals("taverne typique", restaurantCharge.getNom());
		Assertions.assertEquals(restaurant.getPrix(), restaurantCharge.getPrix());
	}

	@Test
	public void testDelete() {
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("taverne typique");

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);
		Restaurant restaurantEnBase = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());
		Assertions.assertNotNull(restaurantEnBase);

		//Suppression du restaurant
		restaurantDao.delete(restaurantSauvegarde.getId());

		restaurantEnBase = restaurantDao.findRestaurantById(restaurantSauvegarde.getId());
		Assertions.assertNull(restaurantEnBase);

	}

	@Test
	public void testSaveAndFindByNom(){
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("taverne typique");

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);
		List<Restaurant> resultat =  restaurantDao.findByNom(restaurantSauvegarde.getNom());

		Assertions.assertEquals(1, resultat.size());
		Restaurant restaurantCharge = resultat.get(0);

		Assertions.assertEquals(restaurant.getNom(), restaurantCharge.getNom());

	}

	@Test
	public void testSaveAndFindByNomLike(){
		//Création du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("taverne bavarde");

		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);
		List<Restaurant> resultat =  restaurantDao.findByNomLike("bava");

		Assertions.assertEquals(1, resultat.size());
		Restaurant restaurantCharge = resultat.get(0);

		Assertions.assertEquals(restaurant.getNom(), restaurantCharge.getNom());

	}

	@Test
	public void testSaveWithRestaurantType(){
		//Création du restaurantType et save
		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setName("taverne suisse");
		RestaurantType restaurantTypeSauvegarde = restaurantTypeDao.save(restaurantType);

		//Creation du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("la table des Alpes");
		restaurant.setRestaurantType(restaurantTypeSauvegarde);
		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//Assertion
		Assertions.assertNotNull(restaurantDao.findRestaurantById(restaurantSauvegarde.getId()));
		System.out.println("nom : " + restaurantSauvegarde.getRestaurantType().getName());
	}

	@Test
	public void testSaveWithRestaurantTypeById(){
		//Création du restaurantType et save
		RestaurantType restaurantType = new RestaurantType();
		restaurantType.setName("taverne suisse");
		RestaurantType restaurantTypeSauvegarde = restaurantTypeDao.save(restaurantType);

		//Creation du restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setNom("la table des Alpes");
		restaurant.setRestaurantType(restaurantTypeSauvegarde);
		//Sauvegarde du restaurant
		Restaurant restaurantSauvegarde = restaurantDao.save(restaurant);

		//Assertion
		Assertions.assertNotNull(restaurantDao.findRestaurantById(restaurantSauvegarde.getId()));
		System.out.println("nom : " + restaurantSauvegarde.getRestaurantType().getName());
	}
*/
}
