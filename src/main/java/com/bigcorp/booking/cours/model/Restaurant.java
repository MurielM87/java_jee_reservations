package com.bigcorp.booking.cours.model;

import com.bigcorp.booking.model.RestaurantType;
import jakarta.persistence.*;

//annotation de façon à ce que JPA puisse la persister
@Entity
//@Table("MIAM_MIAM") //pour mettre dans une table specifique si differente du nom de la class
public class Restaurant {

    @Id //pour definir la cle primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pour indiquer comment est generer la valeur
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="RESTAURANT_TYPE_ID")
    private RestaurantType restaurantType;

    @Column(name="NAME") //pour dans la colonne NAME
    private String nom;

    private String adresse;

    @Enumerated(EnumType.STRING) //EnumType.ORDINAL : 0, 1, 2 en fonction de la place dans la bdd
    private Prix Prix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public com.bigcorp.booking.cours.model.Prix getPrix() {
        return Prix;
    }

    public void setPrix(com.bigcorp.booking.cours.model.Prix prix) {
        Prix = prix;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }
}
