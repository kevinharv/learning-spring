package com.kevharv.java.rest_demo.Employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

@RestController
public class EmployeeController {
    private final EmployeeRepository repo;
    private final EmployeeModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repo = repository;
        this.assembler = assembler;
    }

    // Old version that is not REST
    // @GetMapping("/employees")
    // List<Employee> all() {
    // return repo.findAll();
    // }

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> getAllEmployees() {
        List<EntityModel<Employee>> employees = repo.findAll().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
    }

    // Old version that's not true REST
    // @GetMapping("/employees/{id}")
    // Employee one(@PathVariable Long id) {
    // return repo.findById(id).orElseThrow(() -> new
    // EmployeeNotFoundException(id));
    // }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee employee) {
        EntityModel<Employee> entityModel = assembler.toModel(repo.save(employee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // @PutMapping("/employees/{id}")
    // Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable
    // Long id) {
    // return repo.findById(id)
    // .map(employee -> {
    // employee.setName(newEmployee.getName());
    // employee.setRole(newEmployee.getRole());
    // return repo.save(employee);
    // })
    // .orElseGet(() -> {
    // return repo.save(newEmployee);
    // });
    // }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee updatedEmployee = repo.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repo.save(employee);
        }).orElseGet(() -> {
            return repo.save(newEmployee);
        });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
