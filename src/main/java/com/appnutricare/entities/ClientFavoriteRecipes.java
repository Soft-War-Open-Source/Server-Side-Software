package com.appnutricare.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="client_favorite_recipes")
@IdClass(ClientFavoriteRecipesFK.class)
public class ClientFavoriteRecipes{

    @Id
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;

    @Column(name = "added_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedAt;
}