package com.appnutricare.controller;

import com.appnutricare.entities.Recommendation;
import com.appnutricare.service.IRecommendationService;
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
@RequestMapping("/api/recommendations")
@Api(tags = "Recommendation", value = "Servicio Web RESTful de Recommendations")


public class RecommendationController {

    @Autowired
    private IRecommendationService recommendationService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Recommendations", notes = "Método para listar todos los recommendations")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recommendations encontrados"),
            @ApiResponse(code = 404, message = "Recommendations no ubicados")
    })
    public ResponseEntity<List<Recommendation>> findAll() {
        try {
            List<Recommendation> recommendations = new ArrayList<>();
            recommendations = recommendationService.getAll();
            if (recommendations.isEmpty())
                return new ResponseEntity<List<Recommendation>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Recommendation>>(recommendations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Recommendation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Recommendation por Id", notes = "Método para encontrar un Recommendation por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recommendation encontrado"),
            @ApiResponse(code = 404, message = "Recommendation no ubicado")
    })
    public ResponseEntity<Recommendation> findById(@PathVariable("id") Integer id)
    {
        try{
            Optional<Recommendation> recommendation = recommendationService.getById(id);
            if(!recommendation.isPresent())
                return new ResponseEntity<Recommendation>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Recommendation>(recommendation.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Recommendation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Recommendations por name", notes = "Método para encontrar un Recommendations por name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recommendations encontrado"),
            @ApiResponse(code = 404, message = "Recommendations no ubicado")
    })
    public ResponseEntity<List<Recommendation>> findByName(@PathVariable("name") String name)
    {
        try{
            List<Recommendation> recommendations = recommendationService.findByName(name);
            if(recommendations == null)
                return new ResponseEntity<List<Recommendation>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Recommendation>>(recommendations, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Recommendation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    };

    @GetMapping(value = "/searchByDate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Recommendation por date", notes = "Método para encontrar un Recommendation por date")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recommendations encontrado"),
            @ApiResponse(code = 404, message = "Recommendations no ubicado")
    })
    public ResponseEntity<List<Recommendation>> findByDate(@PathVariable("date")Date date)
    {
        try{
            List<Recommendation> recommendations = recommendationService.findByDate(date);
            if(recommendations == null)
                return new ResponseEntity<List<Recommendation>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Recommendation>>(recommendations, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Recommendation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    };
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Recommendations", notes = "Método para aniadir un Recommendation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Recommendation aniadida"),
            @ApiResponse(code = 404, message = "Recommendation no fue aniadida")
    })
    public ResponseEntity<Recommendation> insertRecommendation(@Valid @RequestBody Recommendation recommendation)
    {
        try{
            Recommendation newRecommendation = recommendationService.save(recommendation);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRecommendation);
        }catch (Exception e){
            return new ResponseEntity<Recommendation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Recommendation", notes = "Método que updatea los datos de un Recommendation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Recommendation actualizada"),
            @ApiResponse(code = 404, message = "Recommendation no ubicada")
    })
    public ResponseEntity<Recommendation> updateRecommendation(
            @PathVariable("id") Integer id, @Valid @RequestBody Recommendation recommendation){
        try {
            Optional<Recommendation> finderRecommendation = recommendationService.getById(id);
            if(!finderRecommendation.isPresent())
                return new ResponseEntity<Recommendation>(HttpStatus.NOT_FOUND);
            recommendation.setId(id);
            recommendationService.save(recommendation);
            return new ResponseEntity<Recommendation>(recommendation, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Recommendation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un Recommendation", notes = "Método para eliminar un Recommendation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Recommendation borrado"),
            @ApiResponse(code = 404, message = "Recommendation no ubicado")
    })
    public ResponseEntity<Recommendation> deleteCustomer(@PathVariable("id") Integer id)
    {
        try{
            Optional<Recommendation> deletedRecommendation = recommendationService.getById(id);
            if(!deletedRecommendation.isPresent())
                return new ResponseEntity<Recommendation>(HttpStatus.NOT_FOUND);
            recommendationService.delete(id);
            return new ResponseEntity<Recommendation>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Recommendation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}