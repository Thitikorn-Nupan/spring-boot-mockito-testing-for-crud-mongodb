package com.ttknpdev.learnspringbootcrudmongodb.controller;

import com.ttknpdev.learnspringbootcrudmongodb.entity.Employee;
import com.ttknpdev.learnspringbootcrudmongodb.service.LayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* In my observation @Autowrite annotation it does not need to use on your CDI logic */

@RestController
@RequestMapping(value = "/api")
public class EndPointController {
    private final LayerService<Employee> layerService;

    public EndPointController(LayerService<Employee> layerService) {
        this.layerService = layerService;
    }

    @GetMapping(value = "/em/reads")
    private ResponseEntity<List<Employee>> getMethodReads() {
        return ResponseEntity
                .accepted()
                .body(layerService.reads());
    }

    @GetMapping(value = "/em/read/{id}")
    private ResponseEntity<Employee> getMethodRead(@PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body(layerService.read(id));
    }

    @PostMapping(value = "/em/create") // don't forget mark @Valid for initial validate annotation
    private ResponseEntity<Boolean> getMethodCreate(@Valid @RequestBody Employee employee) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(layerService.create(employee));
    }

    @PutMapping(value = "/em/update/{id}")
    private ResponseEntity<Boolean> getMethodUpdate(@Valid @RequestBody Employee employee , @PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body(layerService.update(employee,id));
    }

    @DeleteMapping(value = "/em/delete/{id}")
    private ResponseEntity<Boolean> getMethodDelete(@PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body(layerService.delete(id));
    }
}
