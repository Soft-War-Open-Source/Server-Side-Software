package com.appnutricare.repository;

import com.appnutricare.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISpecialtyRepository extends JpaRepository<Specialty, Integer> {
    public List<Specialty> findByinstitution_name(String institution_name);

}
