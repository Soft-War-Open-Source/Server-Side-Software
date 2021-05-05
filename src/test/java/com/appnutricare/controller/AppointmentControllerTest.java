package com.appnutricare.controller;

import com.appnutricare.entities.*;
import com.appnutricare.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AppointmentController.class)
@ActiveProfiles("test")
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentServiceImpl appointmentService;

    private List<Appointment> appointmentList;
    Client client;
    Client client2;
    Nutritionist nutritionist;
    Nutritionist nutritionist2;
    Diet diet;
    Diet diet2;

    @BeforeEach
    void setUp(){
        appointmentList = new ArrayList<>();
        ProfessionalProfile professionalProfile = new ProfessionalProfile(1, "description 1");
        client = new Client(1, "pepitoasd1", "pepitoasd123", "Joseasd1", "Perezasd1",
                "pepitoasd1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        client2 = new Client(2, "pepitoasd1", "pepitoasd123", "Joseasd1", "Perezasd1",
                "pepitoasd1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
        nutritionist = new Nutritionist(1, professionalProfile, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        nutritionist2 = new Nutritionist(2, professionalProfile, "pepito1", "pepito123",
                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
        diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));
        diet2 = new Diet(2, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));

        appointmentList.add(new Appointment(1, client, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes1"));
        appointmentList.add(new Appointment(2, client2, nutritionist2, diet2, ParseDate("2017-07-21 17:32:28"),
                ParseDate("2017-07-21 17:32:28"), "notes2"));
//        appointmentList.add(new Appointment(3, client, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
//                ParseDate("2017-07-21 17:32:28"), "notes3"));
//        appointmentList.add(new Appointment(4, client, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
//                ParseDate("2017-07-21 17:32:28"), "notes4"));
    }

    @Test
    void findAllAppointments() throws Exception{
        given(appointmentService.getAll()).willReturn(appointmentList);
        mockMvc.perform(get("/api/appointment")).andExpect(status().isOk());
    }

//    @Test
//    void findAppointmentById() throws Exception{
//        Integer AppointmentId = 1;
//        ProfessionalProfile professionalProfile = new ProfessionalProfile(1, "description 1");
//        Client client = new Client(1, "pepito1", "pepito123", "Jose1", "Perez1",
//                "pepito1@upc.edu.pe", ParseDate("2017-07-21 17:32:28")); //.10000
//        Nutritionist nutritionist = new Nutritionist(1, professionalProfile, "pepito1", "pepito123",
//                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
//        Diet diet = new Diet(1, "diet1", "description1", ParseDate("2017-07-21 17:32:28"));
//
//        Appointment appointment = new Appointment(1, client, nutritionist, diet, ParseDate("2017-07-21 17:32:28"),
//                ParseDate("2017-07-21 17:32:28"), "notes1");
//        given(appointmentService.getById(AppointmentId)).willReturn(Optional.of(appointment));
//        mockMvc.perform(get("/api/appointment/{id}", appointment.getId())).andExpect(status().isOk());
//    }

//    @Test
//    void findNutritionistByUserName() throws Exception{
//        String NutritionistUsername = "pepito1";
//        Nutritionist nutritionist = new Nutritionist(1, professionalProfileList.get(0), "pepito1", "pepito123",
//                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
//        given(nutritionistService.findByUsername(NutritionistUsername)).willReturn(nutritionist);
//        mockMvc.perform(get("/api/nutritionist/searchByUsername/{username}", nutritionist.getUsername())).andExpect(status().isOk());
//    }
//
//    @Test
//    void findNutritionistByCpnNumber() throws Exception{
//        Integer NutritionistCpnNumber = 123456;
//        Nutritionist nutritionist = new Nutritionist(1, professionalProfileList.get(0), "pepito1", "pepito123",
//                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
//        given(nutritionistService.findByCnpNumber(NutritionistCpnNumber)).willReturn(nutritionist);
//        mockMvc.perform(get("/api/nutritionist/searchByCnpNumber/{cnp_number}", nutritionist.getCnpNumber())).andExpect(status().isOk());
//    }
//
//    @Test
//    void findNutritionistByFirstName() throws Exception{
//        String NutritionistFirstname = "Jose1";
//        Nutritionist nutritionist = new Nutritionist(1, professionalProfileList.get(0), "pepito1", "pepito123",
//                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
//        given(nutritionistService.findByFirstName(NutritionistFirstname)).willReturn(nutritionistList);
//        mockMvc.perform(get("/api/nutritionist/searchByFirstname/{firstname}", nutritionist.getFirstName())).andExpect(status().isOk());
//    }
//
//    @Test
//    void findNutritionistByLastName() throws Exception{
//        String NutritionistLastname = "Perez1";
//        Nutritionist nutritionist = new Nutritionist(1, professionalProfileList.get(0), "pepito1", "pepito123",
//                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
//        given(nutritionistService.findByLastName(NutritionistLastname)).willReturn(nutritionistList);
//        mockMvc.perform(get("/api/nutritionist/searchByLastname/{lastname}", nutritionist.getLastName())).andExpect(status().isOk());
//    }
//
//    @Test
//    void findNutritionistByFirstAndLastName() throws Exception{
//        String NutritionistFirstname = "Jose1";
//        String NutritionistLastname = "Perez1";
//        Nutritionist nutritionist = new Nutritionist(1, professionalProfileList.get(0), "pepito1", "pepito123",
//                "Jose1", "Perez1", "pepito1@upc.edu.pe", 123456, ParseDate("2017-07-21 17:32:28")); //.10000
//        given(nutritionistService.findByFirstNameAndLastName(NutritionistFirstname, NutritionistLastname)).willReturn(nutritionistList);
//        mockMvc.perform(get("/api/nutritionist/searchByFirstnameAndLastname/{firstname}/{lastname}", nutritionist.getFirstName(), nutritionist.getLastName())).andExpect(status().isOk());
//    }
//
    public static Date ParseDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){
        }
        return result;
    }
}

