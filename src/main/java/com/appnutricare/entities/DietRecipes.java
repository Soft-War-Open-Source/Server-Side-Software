package com.appnutricare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="diet_recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietRecipes implements Serializable {

    @EmbeddedId
    private DietRecipesFK id;

    @ManyToOne
    @MapsId("diet_id")
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @ManyToOne
    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}