package com.appnutricare.service;
import com.appnutricare.entities.ProfessionalProfile;
import com.appnutricare.entities.Recommendation;
import com.appnutricare.entities.Nutritionist;
import com.appnutricare.repository.IRecommendationRepository;
import com.appnutricare.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceImplTest {
    @Mock
    private IRecommendationRepository recommendationRepository;
    @InjectMocks
    private RecommendationServiceImpl recommendationService;
    @Test
    public void saveTest()
    {
        ProfessionalProfile professionalProfile = new ProfessionalProfile(1, "description1");
        Nutritionist nutritionist = new Nutritionist(1, professionalProfile, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28"));
        Recommendation recommendation = new Recommendation(1,nutritionist,"pera","descripcionRandom1",ParseDate("2021-05-05"),ParseDate("2020-05-05"));
        given(recommendationRepository.save(recommendation)).willReturn(recommendation);

        Recommendation savedRecommendation = null;
        try{
            savedRecommendation = recommendationService.save(recommendation);
        }catch (Exception e)
        {

        }

        assertThat(savedRecommendation).isNotNull();
        verify(recommendationRepository).save(any(Recommendation.class));
    }

    @Test
    void findByIdTest() throws Exception
    {
        Integer recommendationId = 1;
        ProfessionalProfile professionalProfile = new ProfessionalProfile(1, "description1");
        Nutritionist nutritionistList = new Nutritionist(1, professionalProfile, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        Recommendation recommendation = new Recommendation(1,nutritionistList,"pera","descripcionRandom1",ParseDate("2021-05-05"),ParseDate("2020-05-05"));
        given(recommendationService.getById(recommendationId)).willReturn(Optional.of(recommendation));

        Optional<Recommendation> expected = null;
        expected =recommendationService.getById(recommendationId);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception
    {
        List<Nutritionist> nutritionistList;
        List<ProfessionalProfile> professionalProfileList;
        List<Recommendation> recommendationList;

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
        recommendationList.add(new Recommendation(2,nutritionistList.get(1),"pera","descripcionRandom2",ParseDate("2021-04-05"),ParseDate("2020-05-05")));
        recommendationList.add(new Recommendation(3,nutritionistList.get(2),"pera","descripcionRandom3",ParseDate("2021-03-05"),ParseDate("2020-05-05")));
        recommendationList.add(new Recommendation(4,nutritionistList.get(3),"pera","descripcionRandom4",ParseDate("2021-02-05"),ParseDate("2020-05-05")));

        given(recommendationRepository.findAll()).willReturn(recommendationList);
        List<Recommendation> expected = recommendationService.getAll();
        assertEquals(expected, recommendationList);
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
    @Test
    void findByNameTest() throws Exception {
        String name = "pera";
        List<Nutritionist> nutritionistList;
        List<ProfessionalProfile> professionalProfileList;
        List<Recommendation> recommendationList;

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
        recommendationList.add(new Recommendation(2,nutritionistList.get(1),"pera","descripcionRandom2",ParseDate("2021-04-05"),ParseDate("2020-05-05")));
        recommendationList.add(new Recommendation(3,nutritionistList.get(2),"pera","descripcionRandom3",ParseDate("2021-03-05"),ParseDate("2020-05-05")));
        recommendationList.add(new Recommendation(4,nutritionistList.get(3),"pera","descripcionRandom4",ParseDate("2021-02-05"),ParseDate("2020-05-05")));


        given(recommendationRepository.findByName(name)).willReturn(recommendationList);
        List<Recommendation> expected = recommendationService.findByName(name);
        assertThat(expected).isNotNull();
    }
    @Test
    void findByDateTest() throws Exception {
        Date date = ParseDate("2021-05-05");
        List<Nutritionist> nutritionistList;
        List<ProfessionalProfile> professionalProfileList;
        List<Recommendation> recommendationList;

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
        recommendationList.add(new Recommendation(2,nutritionistList.get(1),"pera","descripcionRandom2",ParseDate("2021-04-05"),ParseDate("2020-05-05")));
        recommendationList.add(new Recommendation(3,nutritionistList.get(2),"pera","descripcionRandom3",ParseDate("2021-03-05"),ParseDate("2020-05-05")));
        recommendationList.add(new Recommendation(4,nutritionistList.get(3),"pera","descripcionRandom4",ParseDate("2021-02-05"),ParseDate("2020-05-05")));


        given(recommendationRepository.findByDate(date)).willReturn(recommendationList);
        List<Recommendation> expected = recommendationService.findByDate(date);
        assertThat(expected).isNotNull();
    }

}
