package com.appnutricare.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ClientFavoriteRecipesFK implements Serializable {

    @Column(name = "client_id")
    private int client_id;

    @Column(name = "recipe_id")
    private int recipe_id;
}