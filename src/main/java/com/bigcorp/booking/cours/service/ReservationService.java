package com.bigcorp.booking.cours.service;

import com.bigcorp.booking.cours.DAO.ReservationDao;
import com.bigcorp.booking.cours.model.Reservation;
import com.bigcorp.booking.cours.rest.ReservationDto;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReservationService {
    @Inject
    private ReservationDao reservationDao;

    //find by id - GET
    public ReservationDto findReservationById(Integer id) {
        return toDto(this.reservationDao.findReservationById(id));
    }

    //create - POST
    @TransactionAttribute
    public ReservationDto save(ReservationDto reservationDto) {
        Reservation reservationSaved = this.reservationDao.save(toEntity(reservationDto));
        return toDto(reservationSaved);
    }

    //delete by Id - DELETE
    public void deleteReservationById(Integer id) {
        this.reservationDao.deleteReservationById(id);
    }

    //find All - GET ALL
    public List<ReservationDto> findAllReservations() {
        List<Reservation> reservationsSaved = this.reservationDao.findAllReservations();
        return toDtos(reservationsSaved);
    }

    //find by clientId
    public List<ReservationDto> findReservationWithClientId(Integer clientId) {
        List<Reservation> clientIdSaved = this.reservationDao.findReservationWithClientId(clientId);
        return toDtos(clientIdSaved);
    }

    //transform an entity to a Dto
    private ReservationDto toDto(Reservation entity) {
        if(entity == null) {
            return null;
        }
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(entity.getId());
        return reservationDto;
    }

    //transform a Dto to an entity
    private Reservation toEntity(ReservationDto dto) {
        if(dto == null) {
            return null;
        }
        Reservation entity = new Reservation();
        entity.setId(dto.getId());
        return entity;
    }

    //transform a list of entities to a list of Dtos
    private List<ReservationDto> toDtos(List<Reservation> entities) {
        List<ReservationDto> dtos = new ArrayList<>();
        for(Reservation entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
