package com.bigcorp.booking.cours.jsf;

import jakarta.ejb.Stateless;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class ClienteleService {
    private int compteur = 1;
    private Map<Integer, ClientFormBean> listeClients = new HashMap<>();

    public ClientFormBean load(Integer id){
        return this.listeClients.get(id);
    }

    public ClientFormBean save(ClientFormBean clientFormBean) {
        System.out.println("client sauvegardé");
        if(clientFormBean.getId() <= 0){
            clientFormBean.setId(compteur++);
        }
        this.listeClients.put(clientFormBean.getId(), clientFormBean);
        return clientFormBean;
    }

    public Collection<ClientFormBean> findAll() {
        return this.listeClients.values();
    }
}
