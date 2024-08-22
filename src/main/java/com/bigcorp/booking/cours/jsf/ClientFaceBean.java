package com.bigcorp.booking.cours.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

@Named
@RequestScoped
public class ClientFaceBean {

    @NotNull
    @Min(0)
    private Integer id;

    private ClientFormBean clientFormBean = new ClientFormBean();

    @Inject
    private ClienteleService clientService;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public ClientFormBean getclientFormBean() {
        return clientFormBean;
    }

    public void setClientFormBean(ClientFormBean clientFormBean) {
        this.clientFormBean = clientFormBean;
    }

    public void onLoad(){
        System.out.println("l'id : " + id);
        this.clientFormBean =  this.clientService.load(id);
    }

    public String save(){
        System.out.println("Sauvegarde du client dans le FaceBean");
        System.out.println("L'id : " + this.clientFormBean.getId());
        System.out.println("Le nom : " + this.clientFormBean.getNom());
        System.out.println("case cochée : " + this.clientFormBean.isCheck());
        this.clientService.save(this.clientFormBean);
        return "page-client?faces-redirect=true";
    }

    public String cancel() {
        return "welcome?faces-redirect=true";
    }

    public Collection<ClientFormBean> getClients(){
        return this.clientService.findAll();
    }

}
