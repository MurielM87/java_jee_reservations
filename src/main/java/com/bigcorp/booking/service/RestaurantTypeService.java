package com.bigcorp.booking.service;

import java.util.ArrayList;
import java.util.List;

import com.bigcorp.booking.rest.RestaurantTypeDto;
import org.jboss.logging.Logger;

import com.bigcorp.booking.dao.RestaurantTypeDao;
import com.bigcorp.booking.model.RestaurantType;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;

/**
 * Service de gestion des RestaurantType
 * Contient le code fonctionnel des RestaurantType
 */
@Stateless
public class RestaurantTypeService {

	private static final Logger LOGGER = Logger.getLogger(RestaurantTypeService.class);

	@Inject
	private RestaurantTypeDao restaurantTypeDao;

	public RestaurantTypeDto findById(Long id) {
		return toDto(this.restaurantTypeDao.findById(id));
	}

	public RestaurantTypeDto findWithRestaurantsById(Long id) {
		return toDto(this.restaurantTypeDao.findWithRestaurantsById(id));
	}

	@TransactionAttribute
	public RestaurantTypeDto save(RestaurantTypeDto restaurantTypeDto) {
		LOGGER.info("Sauvegarde de : " + restaurantTypeDto);
		RestaurantType restaurantTypeSauvegarde = this.restaurantTypeDao.save(toEntity(restaurantTypeDto));
		return toDto(restaurantTypeSauvegarde);
	}

	public List<RestaurantTypeDto> findLikeName(String restaurantTypeFilter) {
		List<RestaurantType> restaurantsCharges = this.restaurantTypeDao.findLikeName(restaurantTypeFilter);
		return toDtos(restaurantsCharges);
	}

	public List<RestaurantTypeDto> findAll() {
		List<RestaurantType> restaurantsCharges =  this.restaurantTypeDao.findAll();
		return toDtos(restaurantsCharges);
	}

	public void deleteById(Long id) {
		this.restaurantTypeDao.deleteById(id);
	}

	/**
	 * Transforme une entity en DTO
	 * @param entity
	 * @return
	 */
	private RestaurantTypeDto toDto(RestaurantType entity) {
		if(entity == null){
			return null;
		}
		RestaurantTypeDto dto = new RestaurantTypeDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		if(entity.getName().startsWith("miam")){
			dto.setNameCommenceParMiam(true);
		}
		return dto;
	}

	/**
	 * Transforme un DTO en entity
	 * @param dto
	 * @return
	 */
	private RestaurantType toEntity(RestaurantTypeDto dto){
		if(dto == null){
			return null;
		}
		RestaurantType entity = new RestaurantType();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}


	/**
	 * Transforme une liste d'entités en liste de DTOs
	 * @param entities non null (non mais !)
	 * @return une collection, peut être vide, mais pas nulle.
	 */
	private List<RestaurantTypeDto> toDtos(List<RestaurantType> entities) {
		List<RestaurantTypeDto> dtos = new ArrayList<>();
		for(RestaurantType entity : entities){
			dtos.add(toDto(entity));
		}
		return dtos;
	}

}