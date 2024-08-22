package com.bigcorp.test;

import com.bigcorp.booking.rest.RestaurantTypeDto;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.bigcorp.booking.dao.RestaurantTypeDao;
import com.bigcorp.booking.model.RestaurantType;
import com.bigcorp.booking.service.RestaurantTypeService;
import com.bigcorp.booking.util.Resources;

import jakarta.inject.Inject;

@ExtendWith(ArquillianExtension.class)
public class GettingStartedServiceIT {

	@Deployment
	public static WebArchive createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "RestaurantTypeServiceIT.war")
				.addPackages(true, "com.bigcorp.booking")
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	RestaurantTypeService service;

	@Test
	public void testService() {
		RestaurantTypeDto restaurantTypeDto = new RestaurantTypeDto();
		RestaurantTypeDto saved = this.service.save(restaurantTypeDto);

		Assertions.assertNotNull(saved.getId());
	}

}
