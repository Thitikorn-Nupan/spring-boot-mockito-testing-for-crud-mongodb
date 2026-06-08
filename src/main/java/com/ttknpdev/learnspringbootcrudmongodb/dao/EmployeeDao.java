package com.ttknpdev.learnspringbootcrudmongodb.dao;

import com.ttknpdev.learnspringbootcrudmongodb.entity.Employee;
import com.ttknpdev.learnspringbootcrudmongodb.log.LogBack;
import com.ttknpdev.learnspringbootcrudmongodb.repository.EmployeeRepository;
import com.ttknpdev.learnspringbootcrudmongodb.service.CommonService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeDao implements CommonService<Employee> {

    private final LogBack logBack;
    private final EmployeeRepository repository;

    public EmployeeDao(EmployeeRepository repository) {
        this.repository = repository;
        logBack = new LogBack(EmployeeDao.class);
    }

    @Override
    public List<Employee> reads() {
        logBack.log.info("method reads() <inside EmployeeDao class> works.");
        return repository.findAll();
    }

    @Override
    public Boolean create(Employee obj) {
        Employee employee = repository.save(obj);
        logBack.log.info("method create(Employee) <inside EmployeeDao class> works.");
        return employee.getId() != null;
    }

    @Override
    public Employee createForTesting(Employee obj) {
        /*Employee employee = repository.save(obj);
        logBack.log.info("method createForTesting(Employee) <inside EmployeeDao class> works.");
        if (employee.getId() != null) return employee;
        else return null;*/
        return obj;
    }

    @Override
    public Employee read(Long id) {
        logBack.log.info("method read(id) <inside EmployeeDao class> works.");
        return repository.findById(id).orElseThrow(() -> null);
    }

    @Override
    public Boolean update(Employee obj, Long id) {
        logBack.log.info("method update(Employee,id) <inside EmployeeDao class> works.");
        return repository.findById(id).map(employee -> {
            employee.setAge(obj.getAge());
            employee.setFullname(obj.getFullname());
            employee.setSalary(obj.getSalary());
            employee.setGender(obj.getGender());
            repository.save(employee);
            return true;
        }).orElseThrow(() -> null);
    }

    @Override
    public Boolean delete(Long id) {
        logBack.log.info("method delete(id) <inside EmployeeDao class> works.");
        return repository.findById(id).map(employee -> {
            repository.delete(employee);
            return true;
        }).orElseThrow(() -> null);
    }
}
