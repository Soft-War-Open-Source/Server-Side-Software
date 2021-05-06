package com.appnutricare.controller;
import com.appnutricare.entities.Nutritionist;
import com.appnutricare.entities.ProfessionalProfile;
import com.appnutricare.entities.Recommendation;
import com.appnutricare.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers =  RecommendationController.class)
@ActiveProfiles("test")

public class RecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RecommendationServiceImpl recommendationService;
    private List<Recommendation> recommendationList;
    private List<Nutritionist> nutritionistList;
    private List<ProfessionalProfile> professionalProfileList;
    @BeforeEach
    void setUp()
    {
        professionalProfileList = new ArrayList<>();
        professionalProfileList.add(new ProfessionalProfile(1, "description 1"));
        professionalProfileList.add(new ProfessionalProfile(2, "description 2"));
        professionalProfileList.add(new ProfessionalProfile(3, "description 3"));
        professionalProfileList.add(new ProfessionalProfile(4, "description 4"));

        nutritionistList = new ArrayList<>();
        nutritionistList.add(new Nutritionist(1, professionalProfileList.get(0), "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28"))); //.10000
        nutritionistList.add(new Nutritionist(2, professionalProfileList.get(1), "pepito2", "pepito123",
                "Jose2", "Perez2", "pepito2@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28"))); //.10000
        nutritionistList.add(new Nutritionist(3, professionalProfileList.get(2), "pepito3", "pepito123",
                "Jose3", "Perez3", "pepito3@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28"))); //.10000
        nutritionistList.add(new Nutritionist(4, professionalProfileList.get(3), "pepito4", "pepito123",
                "Jose4", "Perez4", "pepito4@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28"))); //.10000

        recommendationList = new ArrayList<>();
        recommendationList.add(new Recommendation(1,nutritionistList.get(0),"pera","descripcionRandom1",ParseDate("2021-05-05"),ParseDate("2020-05-05")));
        recommendationList.add(new Recommendation(2,nutritionistList.get(1),"pera","descripcionRandom2",ParseDate("2021-04-05"),ParseDate("2020-04-05")));
        recommendationList.add(new Recommendation(3,nutritionistList.get(2),"pera","descripcionRandom3",ParseDate("2021-03-05"),ParseDate("2020-03-05")));
        recommendationList.add(new Recommendation(4,nutritionistList.get(3),"pera","descripcionRandom4",ParseDate("2021-02-05"),ParseDate("2020-02-05")));

    }

    @Test
    void findAllRecommendations() throws Exception {
        given(recommendationService.getAll()).willReturn(recommendationList);
        mockMvc.perform(get("/api/recommendations")).andExpect(status().isOk());
    }

    @Test
    void findRecommendationById() throws Exception {
        Integer recommendationId = 1;
        Recommendation recommendations = new Recommendation(1,nutritionistList.get(0),"pera","descripcionRandom1",ParseDate("2021-05-05"),ParseDate("2020-05-05"));
        given(recommendationService.getById(recommendationId)).willReturn(Optional.of(recommendations));

        mockMvc.perform(get("/api/recommendations/{id}", recommendations.getId())).andExpect(status().isOk());
    }

    @Test
    void findByDate() throws Exception
    {
        Date date = ParseDate("2021-05-05");
        Recommendation recommendation = new Recommendation(1,nutritionistList.get(0),"pera","descripcionRandom1",ParseDate("2021-05-05"),ParseDate("2020-05-05"));
        given(recommendationService.findByDate(date)).willReturn(recommendationList);
        mockMvc.perform(get("/api/nutritionist/searchByFirstname/{date}", recommendationService.findByDate(date))).andExpect(status().isOk());
    }

    @Test
    void findByName() throws Exception
    {
        String name = "pera";
        Recommendation recommendation = new Recommendation(1,nutritionistList.get(0),"pera","descripcionRandom1",ParseDate("2021-05-05"),ParseDate("2020-05-05"));
        given(recommendationService.findByName(name)).willReturn(recommendationList);
        mockMvc.perform(get("/api/nutritionist/searchByName/{name}", recommendationService.findByName(name))).andExpect(status().isOk());
    }

    public static Date ParseDate(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy,MM,dd");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){
        }
        return result;
    }
}
