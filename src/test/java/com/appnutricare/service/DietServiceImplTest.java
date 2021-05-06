package com.appnutricare.service;

import com.appnutricare.repository.IDietRepository;
import com.appnutricare.service.impl.DietServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DietServiceImplTest {

    @Mock
    private IDietRepository dietRepository;
    @InjectMocks
    private DietServiceImpl dietService;


    @Test
    public void saveTest(){

    }

    @Test
    void findByIdTest() throws  Exception{


    }
    @Test
    void findAllTest() throws  Exception{


    }
    @Test
    void deleteTest() throws  Exception {

    }
}
