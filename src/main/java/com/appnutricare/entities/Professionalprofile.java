package com.appnutricare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "professional_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professionalprofile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="professional_experience_description", nullable = false, length = 500)
    private String professionalexperiencedescription;
}
