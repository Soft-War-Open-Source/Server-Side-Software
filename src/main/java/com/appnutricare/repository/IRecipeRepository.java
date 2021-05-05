package com.appnutricare.repository;

import com.appnutricare.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Integer> {
    public List<Recipe> findAllByNutritionist(Long nutritionist);
    public Optional<Recipe> findRecipeById(Integer id);
}