package com.appnutricare.controller;

import com.appnutricare.entities.Diet;
import com.appnutricare.service.IDietService;
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
@RequestMapping("/api/diets")
@Api(tags = "Diet", value = "Servicio Web RESTful de Diets")
public class DietController {

    @Autowired
    private IDietService dietService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar todos los Diets", notes = "Método para encontrar todos los Diets")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Diets encontrados"),
            @ApiResponse(code = 404, message = "Diets no encontrados")
    })
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
    @ApiOperation(value = "Buscar Diets por Id", notes = "Método para encontrar Diets por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Diets encontrados"),
            @ApiResponse(code = 404, message = "Diets no encontrados")
    })
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
    @ApiOperation(value = "Registro de Diets", notes = "Método para agregar un Diet")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Diet agregado"),
            @ApiResponse(code = 404, message = "Diet no fue agregado")
    })
    public ResponseEntity<Diet> insertDiet(@Valid @RequestBody Diet diet){
        try {
            Diet dietNew = dietService.save(diet);
            return ResponseEntity.status(HttpStatus.CREATED).body(dietNew);
        }catch (Exception e){
            return new ResponseEntity<Diet>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualizacion de un Diet", notes = "Método para actualizar un Diet")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Diet actualizado"),
            @ApiResponse(code = 404, message = "Diet no encontrado")
    })
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
    @ApiOperation(value = "Eliminación de un Diet", notes = "Método para eliminar un Diet")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Diet eliminado"),
            @ApiResponse(code = 404, message = "Diet no encontrado")
    })
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
