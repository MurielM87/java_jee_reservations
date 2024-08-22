package com.bigcorp.booking.rest;

/**
 * DTO : Data Transfer Object : POJO
 * pas très malin qui sert juste à transférer des données
 * qui proviennent de requêtes HTTP ou vont vers des réponses HTTP.
 */
public class RestaurantTypeDto {

    private Long id;

    private String name;

    private boolean nameCommenceParMiam;

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

    public boolean isNameCommenceParMiam() {
        return nameCommenceParMiam;
    }

    public void setNameCommenceParMiam(boolean nameCommenceParMiam) {
        this.nameCommenceParMiam = nameCommenceParMiam;
    }
}