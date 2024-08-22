package com.bigcorp.booking.cours.service;

import com.bigcorp.booking.cours.DAO.ClientDao;
import com.bigcorp.booking.cours.model.Client;
import com.bigcorp.booking.cours.rest.ClientDto;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;


@Stateless
public class ClientService {

    private static final Logger LOGGER = Logger.getLogger(ClientService.class);

    @Inject
    private ClientDao clientDao;

    //methode find ClientDto by Id
    public ClientDto findClientById(Integer id) {
        return toDto(this.clientDao.findClientById(id));
    }

    //methode pour save new client
    @TransactionAttribute
    public ClientDto save(ClientDto clientDto) {
        LOGGER.info("sauvegarde : " + clientDto);
        if(clientDto.getLastName() == null ) {
            throw new IllegalArgumentException("impossible de sauvegarder un client sans nom de famille");
        }
        Client clientSaved = this.clientDao.save(toEntity(clientDto));
        return toDto(clientSaved);
    }

    //methode pour supprimer client par id
    public ClientService deleteClientById(Integer id) {
        this.clientDao.deleteClientById(id);
        return null;
    }

    //methode pour trouver tous les clients
    public List<ClientDto> findAllClients() {
        List<Client> clientsRegistred = this.clientDao.findAllClients();
        return toDtos(clientsRegistred);
    }

    //methode pour trouver client par nom
    public List<ClientDto> findClientByLastName(String clientLastName) {
        List<Client> clientRegistred = this.clientDao.findClientByName(clientLastName);
        return toDtos(clientRegistred);
    }

    //methode pour trouver client par nom Like
    public List<ClientDto> findClientLikeLastName(String clientFilter) {
        List<Client> clientRegistred = this.clientDao.findClientByNameLike(clientFilter);
        return toDtos(clientRegistred);
    }


    //create function toDto (transformer une entity en DTO
    private ClientDto toDto(Client entity) {
        if(entity == null) {
            return null;
        }
        ClientDto clientDto = new ClientDto();
        clientDto.setId(entity.getId());
        return clientDto;
    }

    //create function toEntity (transforme un DTO en entity)
    private Client toEntity(ClientDto dto) {
        if(dto == null) {
            return null;
        }
        Client entity = new Client();
        entity.setId(dto.getId());
        return entity;
    }

    //function toDtos (transforme une liste d'entities en liste de DTOs)
    private List<ClientDto> toDtos(List<Client> entities) {
        List<ClientDto> dtos = new ArrayList<>();
        for(Client entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }


}
