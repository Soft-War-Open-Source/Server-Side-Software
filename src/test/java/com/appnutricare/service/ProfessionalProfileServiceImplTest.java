package com.appnutricare.service;



import com.appnutricare.entities.ProfessionalProfile;
import com.appnutricare.entities.Specialty;
import com.appnutricare.repository.IProfessionalProfileRepository;
import com.appnutricare.service.impl.ProfessionalProfileServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProfessionalProfileServiceImplTest {

    @Mock
    private IProfessionalProfileRepository professionalProfileRepository;
    @InjectMocks
    private ProfessionalProfileServiceImpl professionalProfileService;

    @Test
    public void saveTest(){
        ProfessionalProfile professionalProfile = new ProfessionalProfile(6,"Adolescentes con problemas alimenticios");

        given(professionalProfileRepository.save(professionalProfile)).willReturn(professionalProfile);

        ProfessionalProfile savedProfessionalprofile = null;
        try {
            savedProfessionalprofile = professionalProfileService.save(professionalProfile);
        } catch (Exception e) {
        }

        assertThat(savedProfessionalprofile).isNotNull();
        verify(professionalProfileRepository).save(any(ProfessionalProfile.class));
    }


    @Test
    void findByIdTest() throws  Exception{
        Integer id = 6;
        ProfessionalProfile professionalProfile = new ProfessionalProfile(6,"Adolescentes con problemas alimenticios");

        given(professionalProfileRepository.findById(id)).willReturn(Optional.of(professionalProfile));

        Optional<ProfessionalProfile> expected = professionalProfileService.getById(id);
        assertThat(expected).isNotNull();

    }
    @Test
    void findAllTest() throws  Exception{
        List<ProfessionalProfile> list = new ArrayList<>();
        list.add(new ProfessionalProfile(1,"Adolescentes con problemas alimenticios"));
        list.add(new ProfessionalProfile(2,"Adolescentes con problemas alimenticios"));
        list.add(new ProfessionalProfile(3,"Adolescentes con problemas alimenticios"));
        list.add(new ProfessionalProfile(4,"Adolescentes con problemas alimenticios"));

        given(professionalProfileRepository.findAll()).willReturn(list);
        List<ProfessionalProfile> expected = professionalProfileService.getAll();
        assertEquals(expected, list);

    }
    @Test
    void deleteTest() throws  Exception {

        Integer id = 6;
        professionalProfileService.delete(id);
        professionalProfileService.delete(id);
        verify(professionalProfileRepository, times(2)).deleteById(id);


    }


}
