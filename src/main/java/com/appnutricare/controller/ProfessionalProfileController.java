package com.appnutricare.controller;


import com.appnutricare.entities.ProfessionalProfile;
import com.appnutricare.service.IProfessionalProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professional_profile")
@Api(tags = "Profesional Profile", value = "Servicio Web RESTful de Profesional_profiles")
public class ProfessionalProfileController {

    @Autowired
    private IProfessionalProfileService professionalProfileService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Profesional Profiles", notes = "Método para encontrar Profesional Profiles ")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Profesional Profiles encontrados"),
            @ApiResponse(code = 404, message = "Profesional Profiles no encontrados")
    })
    public ResponseEntity<List<ProfessionalProfile>>findAll(){
        try {
            List<ProfessionalProfile> professionalProfiles = new ArrayList<>();
            professionalProfiles = professionalProfileService.getAll();
            return new ResponseEntity<List<ProfessionalProfile>>(professionalProfiles, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<List<ProfessionalProfile>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Profesional Profile por Id", notes = "Método para encontrar Profesional Profile por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Profesional Profile encontrado"),
            @ApiResponse(code = 404, message = "Profesional Profile no encontrado")
    })
    public ResponseEntity<ProfessionalProfile> findById(@PathVariable("id") Integer id){
        try {
            Optional<ProfessionalProfile> professionalProfile = professionalProfileService.getById(id);
            if(!professionalProfile.isPresent())
                return new ResponseEntity<ProfessionalProfile>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<ProfessionalProfile>(professionalProfile.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Profesional Profile", notes = "Método para agregar un Profesional Profile")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Profesional Profile agregado"),
            @ApiResponse(code = 404, message = "Profesional Profile no fue agregado")
    })
    public ResponseEntity<ProfessionalProfile> insertProfessionalProfile(@Valid @RequestBody ProfessionalProfile professionalProfile){
        try {
            ProfessionalProfile professionalProfileNew = professionalProfileService.save(professionalProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(professionalProfileNew);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualizar un Profesional Profile", notes = "Método para actualizar un Profesional Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Profesional Profile actualizado"),
            @ApiResponse(code = 404, message = "Profesional Profile no encontrado")
    })
    public ResponseEntity<ProfessionalProfile> updateProfessionalProfile(@PathVariable("id") Integer id, @Valid @RequestBody ProfessionalProfile professionalProfile){
        try {
            Optional<ProfessionalProfile> professionalProfileOptional = professionalProfileService.getById(id);
            if(!professionalProfileOptional.isPresent())
                return new ResponseEntity<ProfessionalProfile>(HttpStatus.NOT_FOUND);
            professionalProfile.setId(id);
            professionalProfileService.save(professionalProfile);
            return new ResponseEntity<ProfessionalProfile>(professionalProfile,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un Profesional Profile", notes = "Método para eliminar un Profesional Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Profesional Profile eliminado"),
            @ApiResponse(code = 404, message = "Profesional Profile no encontrado")
    })
    public ResponseEntity<ProfessionalProfile> deleteDiet(@PathVariable("id") Integer id){
        try {
            Optional<ProfessionalProfile> professionalProfileOptional = professionalProfileService.getById(id);
            if(!professionalProfileOptional.isPresent())
                return new ResponseEntity<ProfessionalProfile>(HttpStatus.NOT_FOUND);
            professionalProfileService.delete(id);
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProfessionalProfile>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
