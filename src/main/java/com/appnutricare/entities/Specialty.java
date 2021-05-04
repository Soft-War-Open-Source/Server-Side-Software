package com.appnutricare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "specialty")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Specialty.findByInstitutionname",query = "select c from Specialty c where c.institutionname = ?1")
public class Specialty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="name", nullable = false, length = 50)
    private String name;
    @Column(name ="institution_name", nullable = false, length = 50)
    private String institution_name;
}
