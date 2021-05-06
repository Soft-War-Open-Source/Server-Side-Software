package com.appnutricare.service;


import com.appnutricare.entities.Recommendation;

import java.util.List;
import java.util.Date;


public interface IRecommendationService extends CrudService<Recommendation>{

    public List<Recommendation> findByName(String name) throws Exception;

    public List<Recommendation> findByDate(Date date) throws Exception;

}
