package com.appnutricare.repository;


import com.appnutricare.entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface IRecommendationRepository extends JpaRepository<Recommendation, Integer> {


    public List<Recommendation> findByName(String name);

    public List<Recommendation> findByIdRange(int recommendationId1, int recommendationId2);
}
