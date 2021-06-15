package com.appnutricare.repository;

import com.appnutricare.entities.ProfessionalSpecialties;
import com.appnutricare.entities.ProfessionalSpecialtiesFK;
import com.appnutricare.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfessionalSpecialtiesRepository
        extends JpaRepository<ProfessionalSpecialties, ProfessionalSpecialtiesFK>{

    @Query("Select b.specialty from ProfessionalSpecialties b where b.professionalProfile.nutritionist.id = :nutritionist_id")
    public List<Specialty> findByNutritionist(@Param("nutritionist_id") Integer nutritionist_id);
}
