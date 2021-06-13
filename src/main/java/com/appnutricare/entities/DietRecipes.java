package com.appnutricare.entities;

import javax.persistence.*;

@Entity
@Table(name="diet_recipes")
@IdClass(DietRecipesFK.class)
public class DietRecipes {

    @Id
    @ManyToOne
    @JoinColumn(name = "diet_id", referencedColumnName = "id")
    private Diet diet;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;
}