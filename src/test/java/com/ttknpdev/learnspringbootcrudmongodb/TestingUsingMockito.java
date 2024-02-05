package com.ttknpdev.learnspringbootcrudmongodb;

import com.ttknpdev.learnspringbootcrudmongodb.dao.EmployeeDao;
import com.ttknpdev.learnspringbootcrudmongodb.entity.Employee;

import com.ttknpdev.learnspringbootcrudmongodb.repository.EmployeeRepository;
import com.ttknpdev.learnspringbootcrudmongodb.service.LayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestingUsingMockito {
    @Mock
    public EmployeeRepository employeeRepository;
    public LayerService<Employee> employeeLayerService;

    @BeforeEach
    public void initialUseCase () {
        employeeLayerService = new EmployeeDao(employeeRepository);
    }
    @Test
    public void tryToGetEmployeeMethodReads() {
        // keep concept mocking
        /*
        So, we have to tell Mockito to return something when userRepository.findAll() is called.
        We do this with the static when method.
        */
        Mockito.when(employeeRepository.findAll()).thenReturn(employees());
        /*
        Assume !
        Now users will call employeeLayerService.reads() it means. employeeRepository.findAll() method was called
        */
        List<Employee> employees = employeeLayerService.reads();

        Assertions.assertEquals("peter parker",employees.get(0).getFullname());
        Mockito.verify(employeeRepository,Mockito.times(1)).findAll(); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
    }

    @Test
    public void tryToGetEmployeeMethodCreate() {
        /*
        This will make employeeRepository.save(any(Employee.class)) then return the same object that is passed into the method.
        --
        Mockito.when(employeeRepository.save(Mockito.any( Employee.class) )).then( AdditionalAnswers.returnsFirstArg() ) ;
        Employee resultEmployee = employeeLayerService.createForTesting(employee());
        Assertions.assertEquals(1,resultEmployee.getId());
        Mockito.verify(employeeRepository,Mockito.times(0)).save(employee());
        --
        */
        Mockito.when(employeeRepository.save(Mockito.any( Employee.class) )).then( AdditionalAnswers.returnsFirstArg() ) ;
        Boolean resultEmployee = employeeLayerService.create(employee());
        Assertions.assertEquals(true,resultEmployee);
        Mockito.verify(employeeRepository,Mockito.times(0)).save(employee()); // use 0 for to be arg in times() method. it will be good (invocations (n. การวิงวอน))
    }

    @Test
    public void tryToGetEmployeeMethodUpdate() {
        // assume. First we search by id
        // if employee is alive
        // we can delete
        Employee oldEm = employee();
        Employee newEm = new Employee("kevin parker",(short)31,25000F,"male");
        newEm.setId(oldEm.getId());
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(oldEm)) ;

        Employee oldEmployee = employeeLayerService.read(1L);
        Mockito.verify(employeeRepository,Mockito.times(1)).findById(1L); // use 0 for to be arg in times() method. it will be good (invocations (n. การวิงวอน))

        Mockito.when(employeeRepository.save(Mockito.any( Employee.class) )).then( AdditionalAnswers.returnsFirstArg() ) ;
        Boolean resultEmployee = employeeLayerService.create(newEm);
        Assertions.assertEquals(true,resultEmployee);

        Mockito.verify(employeeRepository,Mockito.times(0)).save(employee()); // use 0 for to be arg in times() method. it will be good (invocations (n. การวิงวอน))

    }

    @Test
    public void tryToGetEmployeeMethodDelete() {
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee())) ;
        Boolean resultEmployee = employeeLayerService.delete(1L);
        Mockito.verify(employeeRepository,Mockito.times(0)).deleteById(1L); // use 0 for to be arg in times() method. it will be good (invocations (n. การวิงวอน))
        Assertions.assertEquals(true,resultEmployee);

    }

    // for mocking
    public List<Employee> employees(){
        Employee e1 = new Employee("peter parker",(short)30,25000F,"male");
        Employee e2 = new Employee("alex ryder",(short)25,32000F,"female");
        e1.setId(1L);
        e2.setId(2L);
        return List.of(e1,e2);
    }
    public Employee employee() {
        Employee e1 = new Employee("peter parker",(short)30,25000F,"male");
        e1.setId(1L);
        return e1;
    }
}
