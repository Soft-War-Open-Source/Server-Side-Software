package com.appnutricare.controller;


import com.appnutricare.entities.ProfessionalProfile;
import com.appnutricare.entities.Specialty;
import com.appnutricare.service.impl.ProfessionalProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProfessionalProfileController.class)
@ActiveProfiles("test")
public class ProfessionalProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProfessionalProfileServiceImpl professionalProfileService;

    private List<ProfessionalProfile> profileList;

    @BeforeEach
    void setUp(){
        profileList = new ArrayList<>();
        profileList.add(new ProfessionalProfile(6,"Adolescentes con problemas alimenticios"));
        profileList.add(new ProfessionalProfile(7,"Adolescentes con problemas alimenticios"));
        profileList.add(new ProfessionalProfile(8,"Adolescentes con problemas alimenticios"));
        profileList.add(new ProfessionalProfile(9,"Adolescentes con problemas alimenticios"));
    }

    @Test
    void findAllProfessionalProfiles() throws Exception{
        given(professionalProfileService.getAll()).willReturn(profileList);
        mockMvc.perform(get("/api/professional_profile")).andExpect(status().isOk());
    }

    @Test
    void findSpecialtyId() throws Exception{
        Integer ProfessionalId = 6;
        ProfessionalProfile professionalProfile = new ProfessionalProfile(6,"\"Adolescentes con problemas alimenticios\"");

        given(professionalProfileService.getById(ProfessionalId)).willReturn(Optional.of(professionalProfile));

        mockMvc.perform(get("/api/professional_profile/{id}", professionalProfile.getId())).andExpect(status().isOk());
    }


}
