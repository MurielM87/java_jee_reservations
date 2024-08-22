package com.bigcorp.booking.cours.rest;

import com.bigcorp.booking.cours.service.ReservationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reservations")
public class ReservationController {

    @Inject
    private ReservationService reservationService;

    //get all reservations
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReservations() {
        List<ReservationDto> allReservations = this.reservationService.findAllReservations();
        if (allReservations == null || allReservations.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("No reservation for the moment")
                    .build();
        }
        return Response.ok().entity(allReservations).build();
    }

    //get Reservation by id
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservationById(@PathParam("id") int id) {
        ReservationDto reservationDto = reservationService.findReservationById(id);
        if(reservationDto.getId() == null ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("pas de réservation avec cet id : " + reservationDto.getId())
                    .build();
        } else {
            return Response.status(Response.Status.OK)
                    .entity("id : " + reservationDto.getClientId())
                    .build();
        }
    }

    //post a new reservation
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ReservationDto reservationDto) {
        reservationService.save(reservationDto);
        return Response.status(Response.Status.OK)
                .entity("la réservation est sauvegardée")
                .build();
    }

    //delete a reservation by id
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteReservationById(@PathParam("id") Integer id) {
        ReservationDto reservationDto = this.reservationService.findReservationById(id);
        if(reservationDto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("l'id " + id + " n'a pas été trouvé")
                    .build();
        }
        this.reservationService.deleteReservationById(id);
        return Response.status(Response.Status.OK)
                .entity("l'id " + id + " a été supprimé")
                .build();
    }

    //get a reservation by clientId
    @GET
    @Path("/client/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservationsByClientId(@PathParam("clientId") Integer clientId) {
        List<ReservationDto> reservations = this.reservationService.findReservationWithClientId(clientId);
        if(reservations.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No reservation found for the client id " + clientId)
                    .build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
