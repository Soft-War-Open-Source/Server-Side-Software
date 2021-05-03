package com.appnutricare.service;

import com.appnutricare.entities.Specialty;

import java.util.List;


public interface ISpecialtyService extends CrudService<Specialty>{

    public List<Specialty> findByInstitutionname(String institutionname) throws Exception;
}
