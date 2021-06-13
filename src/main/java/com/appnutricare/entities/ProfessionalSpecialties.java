package com.appnutricare.entities;

import javax.persistence.*;

@Entity
@Table(name="professional_specialties")
@IdClass(ProfessionalSpecialtiesFK.class)
public class ProfessionalSpecialties {

    @Id
    @ManyToOne
    @JoinColumn(name = "professional_profile_id", referencedColumnName = "id")
    private ProfessionalProfile professionalProfile;

    @Id
    @ManyToOne
    @JoinColumn(name = "specialty_id", referencedColumnName = "id")
    private Specialty specialty;
}
