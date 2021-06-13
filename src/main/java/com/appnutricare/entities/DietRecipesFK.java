package com.appnutricare.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DietRecipesFK implements Serializable {

    @Column(name = "diet_id")
    private int diet_id;

    @Column(name = "recipe_id")
    private int recipe_id;
}
