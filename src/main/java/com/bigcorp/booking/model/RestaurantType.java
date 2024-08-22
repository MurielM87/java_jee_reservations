package com.bigcorp.booking.model;

import com.bigcorp.booking.cours.model.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

/**
 * Entité : décrit des données persistées dans une classe (un POJO)
 */
@Entity
public class RestaurantType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min=5)
//	@jakarta.validation.constraints.Email
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantType")
	private Set<Restaurant> restaurants = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}
