package com.appnutricare.service.impl;

import com.appnutricare.entities.Professionalprofile;
import com.appnutricare.repository.IProfessionalprofileRepository;
import com.appnutricare.service.IProfessionalprofileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProfessionalprofileServiceImpl implements IProfessionalprofileService {

    @Autowired
    private IProfessionalprofileRepository professionalprofileRepository;

    @Override
    @Transactional
    public Professionalprofile save(Professionalprofile professionalprofile) throws Exception {
        return professionalprofileRepository.save(professionalprofile);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        professionalprofileRepository.deleteById(id);

    }

    @Override
    public List<Professionalprofile> getAll() throws Exception {
        return professionalprofileRepository.findAll();
    }

    @Override
    public Optional<Professionalprofile> getById(Integer id) throws Exception {
        return professionalprofileRepository.findById(id);
    }
}
