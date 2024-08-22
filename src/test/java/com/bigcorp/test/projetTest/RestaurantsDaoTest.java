package com.bigcorp.test.projetTest;

import com.bigcorp.booking.projet.dao.RestaurantsDao;
import com.bigcorp.booking.projet.model.Restaurants;
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
public class RestaurantsDaoTest {

    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "RestaurantTypeServiceIT.war")
                .addPackages(true, "com.bigcorp.booking")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private RestaurantsDao restaurantsDao;

    @Test
    public void testSaveAndGetRestaurant() {
       //create a restaurant
        Restaurants restaurant = new Restaurants();
        restaurant.setAddress("666 rue de la paix");
        restaurant.setName("taverne typique");
        //save a restaurant
        Restaurants restaurantSaved = restaurantsDao.save(restaurant);
        Assertions.assertNotNull(restaurantSaved);
        Assertions.assertNotNull(restaurantSaved.getId());
        System.out.println("l'id du restaurant : " + restaurantSaved.getId());
        System.out.println("le nom du restaurant : " + restaurantSaved.getName());

        Restaurants restaurantRegistred = restaurantsDao.findRestaurantById(restaurantSaved.getId());
        Assertions.assertEquals(1, restaurantRegistred.getId());
        Assertions.assertEquals(restaurant.getName(), restaurantRegistred.getName());
        Assertions.assertEquals(restaurant.getAddress(), restaurantRegistred.getAddress());

    }

    @Test
    public void testDeleteRestaurantById() {
        //create a restaurant
        Restaurants restaurant = new Restaurants();
        restaurant.setName("taverne typique");
        //save
        Restaurants restaurantSaved = restaurantsDao.save(restaurant);
        Restaurants restaurantRegistred = restaurantsDao.findRestaurantById(restaurantSaved.getId());
        Assertions.assertNotNull(restaurantRegistred);
        //delete a restaurant by id
        restaurantsDao.deleteRestaurantById(restaurantSaved.getId());
        restaurantRegistred = restaurantsDao.findRestaurantById(restaurantSaved.getId());
        Assertions.assertNull(restaurantRegistred);

    }

    @Test
    public void testSaveAndFindRestaurantByName() {
        //create a restaurant
        Restaurants restaurant = new Restaurants();
        restaurant.setName("taverne typique");
        //save
        Restaurants restaurantSaved = restaurantsDao.save(restaurant);
        List<Restaurants> result = restaurantsDao.findRestaurantByName(restaurantSaved.getName());
        Assertions.assertEquals(1, result.size());
        Restaurants restaurantRegistred = result.get(0);
        Assertions.assertEquals(restaurant.getName(), restaurantRegistred.getName());
    }

    @Test
    public void testSaveAndFindRestaurantByNameLike() {
        //create a restaurant
        Restaurants restaurant = new Restaurants();
        restaurant.setName("taverne bavarde");
        //save
        Restaurants restaurantSaved = restaurantsDao.save(restaurant);
        List<Restaurants> result = restaurantsDao.findRestaurantByNameLike("bava");
        Assertions.assertEquals(1, result.size());
        Restaurants restaurantRegistred = result.get(0);
        Assertions.assertEquals(restaurant.getName(), restaurantRegistred.getName());
    }
}
