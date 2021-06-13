package com.appnutricare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "professional_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalProfile implements Serializable {

    public ProfessionalProfile(Integer id, String professional_experience_description) {
        this.id = id;
        this.professional_experience_description = professional_experience_description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="professional_experience_description", nullable = false, length = 500)
    private String professional_experience_description;

    @OneToMany(mappedBy = "professionalProfile")
    private List<ProfessionalSpecialties> specialtyAssoc;
}
