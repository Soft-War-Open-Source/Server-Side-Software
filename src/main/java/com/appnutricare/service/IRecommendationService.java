package com.appnutricare.service;


import com.appnutricare.entities.Recommendation;

import java.util.List;

public interface IRecommendationService extends CrudService<Recommendation>{

    public List<Recommendation> findByName(String name) throws Exception;

    public List<Recommendation> findByIdRange(int recommendationId1, int recommendation) throws Exception;

}
