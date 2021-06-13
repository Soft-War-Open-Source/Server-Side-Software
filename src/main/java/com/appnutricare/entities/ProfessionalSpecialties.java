package com.appnutricare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="professional_specialties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalSpecialties implements Serializable {

    @EmbeddedId
    private ProfessionalSpecialtiesFK id;

    @ManyToOne
    @MapsId("professional_profile_id")
    @JoinColumn(name = "professional_profile_id")
    private ProfessionalProfile professionalProfile;

    @ManyToOne
    @MapsId("specialty_id")
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;
}
