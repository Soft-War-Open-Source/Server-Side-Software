package com.appnutricare.service;

import com.appnutricare.entities.Recipe;
import com.appnutricare.repository.IRecipeRepository;
import com.appnutricare.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
    @Mock
    private IRecipeRepository recipeRepository;
    @InjectMocks
    private RecipeServiceImpl recipeService;

    /*
    @Test
    public void saveTest(){
        Recipe recipe = new Recipe(1,"Receta numero 1","La receta numero uno esta conpuesta de frutos etc",
                "Paso1: asdasd, Paso2_asdasdas","Pera,mango,uva",1L,"27/01/2016",
                "asdf","1");

        given(recipeRepository.save(recipe)).willReturn(recipe);

        Recipe savedRecipe = null;
        try{
            savedRecipe = recipeService.save(recipe);
        }catch (Exception e){
        }
        assertThat(savedRecipe).isNotNull();
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        recipeService.delete(id);
        verify(customerRepository, times(1)).deleteById(id);
    }
    */

}
