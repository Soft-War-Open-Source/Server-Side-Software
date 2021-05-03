package com.appnutricare.controller;

import com.appnutricare.entities.Diet;
import com.appnutricare.service.IDietService;
import io.swagger.annotations.Api;
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
@RequestMapping("/api/diets")
@Api(tags = "Diet", value = "Servicio Web RESTful de Clients")
public class DietController {

    @Autowired
    private IDietService dietService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diet>>findAll(){
        try {
            List<Diet> diets = new ArrayList<>();
            diets = dietService.getAll();
            return new ResponseEntity<List<Diet>>(diets, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<List<Diet>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diet> findById(@PathVariable("id") Integer id){
        try {
            Optional<Diet> diet = dietService.getById(id);
            if(!diet.isPresent())
                return new ResponseEntity<Diet>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Diet>(diet.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Diet>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diet> insertDiet(@Valid @RequestBody Diet diet){
        try {
            Diet dietNew = dietService.save(diet);
            return ResponseEntity.status(HttpStatus.CREATED).body(dietNew);
        }catch (Exception e){
            return new ResponseEntity<Diet>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diet> updateDiet(@PathVariable("id") Integer id, @Valid @RequestBody Diet diet){
        try {
            Optional<Diet> dietOptional = dietService.getById(id);
            if(!dietOptional.isPresent())
                return new ResponseEntity<Diet>(HttpStatus.NOT_FOUND);
            diet.setId(id);
            dietService.save(diet);
            return new ResponseEntity<Diet>(diet,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Diet>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diet> deleteDiet(@PathVariable("id") Integer id){
        try {
            Optional<Diet> dietOptional = dietService.getById(id);
            if(!dietOptional.isPresent())
                return new ResponseEntity<Diet>(HttpStatus.NOT_FOUND);
            dietService.delete(id);
            return new ResponseEntity<Diet>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Diet>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
