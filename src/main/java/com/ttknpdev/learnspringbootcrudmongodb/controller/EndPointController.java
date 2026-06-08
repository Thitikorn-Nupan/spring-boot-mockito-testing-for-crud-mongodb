package com.ttknpdev.learnspringbootcrudmongodb.controller;

import com.ttknpdev.learnspringbootcrudmongodb.entity.Employee;
import com.ttknpdev.learnspringbootcrudmongodb.service.CommonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EndPointController {

    private final CommonService<Employee> commonService;

    /**
     * In my observation @Autowrite annotation it does not need to use on your CDI logic because it mark it on behind
    */
    public EndPointController(CommonService<Employee> commonService) {
        this.commonService = commonService;
    }

    @GetMapping(value = "/em/reads")
    private ResponseEntity<List<Employee>> getMethodReads() {
        return ResponseEntity
                .accepted()
                .body(commonService.reads());
    }

    @GetMapping(value = "/em/read/{id}")
    private ResponseEntity<Employee> getMethodRead(@PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body(commonService.read(id));
    }

    /** don't forget mark @Valid for initial validate annotation */
    @PostMapping(value = "/em/create")
    private ResponseEntity<Boolean> getMethodCreate(@Valid @RequestBody Employee employee) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonService.create(employee));
    }

    @PutMapping(value = "/em/update/{id}")
    private ResponseEntity<Boolean> getMethodUpdate(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body(commonService.update(employee, id));
    }

    @DeleteMapping(value = "/em/delete/{id}")
    private ResponseEntity<Boolean> getMethodDelete(@PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body(commonService.delete(id));
    }
}
