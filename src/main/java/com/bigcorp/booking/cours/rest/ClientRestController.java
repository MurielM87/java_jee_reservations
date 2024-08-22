package com.bigcorp.booking.cours.rest;

import com.bigcorp.booking.cours.service.ClientService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clients")
@RequestScoped
public class ClientRestController {

    @Inject
    private ClientService clientservice;

    //requêtes GET
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClientDto> listAll() {
        List<ClientDto> allClients = this.clientservice.findAllClients();
        return allClients;
    }

    //requête GET / {id}
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClientDto getClientById(@PathParam("id") Integer id) {
        return this.clientservice.findClientById(id);
    }

    //requête Get par lastName
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClientDto> findClientByLastName(@QueryParam("lastName") String lastName) {
        if(lastName == null) {
            return null;
        }
        return this.clientservice.findClientByLastName(lastName);
    }

    //requête GET par Like lastName
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClientDto> findClientLikeLastName(@QueryParam("lastName") String lastName) {
        if(lastName == null){
            return null;
        }
        return this.clientservice.findClientLikeLastName(lastName);
    }

    //requête DELETE / {id}
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Integer id) {
        ClientDto dto = this.clientservice.findClientById(id);
        if(dto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("l'id " + id + " n'a pas été trouvé")
                    .build();
        }
        this.clientservice.deleteClientById(id);
        return Response.status(Response.Status.OK)
                .entity("client supprimé avec l'id " + id)
                .build();
    }

    //requete POST
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ClientDto save(@Valid ClientDto clientDto) {
        if(clientDto.getLastName() == null || clientDto.getFirstName() == null || clientDto.getEmail() == null) {
            throw new IllegalArgumentException("All fields (lastName, firstName, email) are required");
        }
        return this.clientservice.save(clientDto);
    }

}
