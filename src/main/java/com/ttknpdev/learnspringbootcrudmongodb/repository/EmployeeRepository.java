package com.ttknpdev.learnspringbootcrudmongodb.repository;

import com.ttknpdev.learnspringbootcrudmongodb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Long> { }
