package com.appnutricare.service;

import com.appnutricare.entities.Specialty;

import java.util.List;


public interface ISpecialtyService extends CrudService<Specialty>{

    public List<Specialty> findByinstitution_name(String institution_name) throws Exception;
}
