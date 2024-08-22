package com.bigcorp.booking.rest;

import java.util.List;

import com.bigcorp.booking.service.RestaurantTypeService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Un contrôleur REST de base pour /restaurant-types
 * L'URL de base est dans Path
 */
@Path("/restaurant-types")
@RequestScoped
public class RestaurantTypeRestController {

    @Inject
    private RestaurantTypeService restaurantTypeService;

    /**
     * Traite les requêtes GET /
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestaurantTypeDto> listAll() {
        List<RestaurantTypeDto> allRestaurants = this.restaurantTypeService.findAll();
        return allRestaurants;
    }

    /**
     * Traite les requêtes GET /{id}
     * @return
     */
    /*
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantTypeDto getById(@PathParam("id") Long id) {
        return this.restaurantTypeService.findById(id);
    }
     */
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        RestaurantTypeDto dto = this.restaurantTypeService.findById(id);
        if(dto == null) {
            Response maReponse = Response
                    .status(Response.Status.NOT_FOUND)
                 //   .header("Mon-Entete", "maValeur")
                    .entity("perdu, aucun type de restaurant ne correspond à votre requête")
                    .build();
            return maReponse;
        }
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    /**
     * Traite les requêtes DELETE /{id}
     * @return
     */
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteById(@PathParam("id") Long id) {
        this.restaurantTypeService.deleteById(id);
    }

    /**
     * Traite les requêtes POST /
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantTypeDto save(@Valid RestaurantTypeDto restaurantTypeDto) {
        return this.restaurantTypeService.save(restaurantTypeDto);
    }

}