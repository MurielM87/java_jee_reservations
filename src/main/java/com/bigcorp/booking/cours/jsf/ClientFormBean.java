package com.bigcorp.booking.cours.jsf;

import jakarta.inject.Named;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ClientFormBean {
    private String message = "Bienvenue à vous";

    @NotNull
    @Min(0) //pour pas nombre negatif
    private int id;

    @NotNull
    @Size(min = 1, max = 30)
    private String nom;

    @NotNull
    @Size(min = 2, max = 30)
    private String prenom;

    @NotNull
    @Named
    @Email
    private String email;

    @Positive
    private int nombre;

    private boolean check = false;

    private LocalDateTime dateCourante = LocalDateTime.now();

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public @NotNull @Named @Size(min = 2, max = 40) String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NotNull @Named @Size(min = 2, max = 40) String prenom) {
        this.prenom = prenom;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public int getNombre() {
        return nombre;
    }
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public boolean isCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }

    public LocalDateTime getDateCourante() {
        return dateCourante;
    }
    public void setDateCourante(LocalDateTime dateCourante) {
        this.dateCourante = dateCourante;
    }
}
