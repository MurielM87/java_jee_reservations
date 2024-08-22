package com.bigcorp.booking.dao;

import java.util.Collections;
import java.util.List;

import com.bigcorp.booking.model.RestaurantType;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * DAO s'appuyant sur un entityManager JPA
 */
@Stateless
public class RestaurantTypeDao {

	@PersistenceContext
	EntityManager entityManager;

	public RestaurantType findById(Long id) {
		return entityManager.find(RestaurantType.class, id);
	}

	public List<RestaurantType> findAll() {
		return this.entityManager.createQuery("select distinct r from RestaurantType r", RestaurantType.class)
				.getResultList();
	}

	public RestaurantType findWithRestaurantsById(Long id) {
		List<RestaurantType> result = this.entityManager.createQuery(
				"select distinct r from RestaurantType r left outer join fetch r.restaurants where r.id = :id",
				RestaurantType.class).setParameter("id", id).getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public List<RestaurantType> findLikeName(String name) {
		if (name == null || name.isEmpty()) {
			return Collections.emptyList();
		}
		TypedQuery<RestaurantType> query = this.entityManager.createQuery(
				"SELECT DISTINCT r FROM RestaurantType r " + " WHERE upper(r.name) like :name", RestaurantType.class);
		query.setParameter("name", "%" + name.toUpperCase() + "%");
		return query.getResultList();
	}

	@TransactionAttribute
	public RestaurantType save(RestaurantType restaurantType) {
		return entityManager.merge(restaurantType);
	}

	public void deleteById(Long id) {
		RestaurantType entity = entityManager.find(RestaurantType.class, id);
		if(entity == null) {
			return;
		}
		entityManager.remove(entity);
	}

}
