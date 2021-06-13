package com.appnutricare.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProfessionalSpecialtiesFK implements Serializable {

    @Column(name = "professional_profile_id")
    private int professional_profile_id;

    @Column(name = "specialty_id")
    private int specialty_id;
}
