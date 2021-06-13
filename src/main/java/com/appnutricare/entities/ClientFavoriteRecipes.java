package com.appnutricare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="client_favorite_recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientFavoriteRecipes implements Serializable {

    @EmbeddedId
    private ClientFavoriteRecipesFK id;

    @ManyToOne
    @MapsId("client_id")
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}