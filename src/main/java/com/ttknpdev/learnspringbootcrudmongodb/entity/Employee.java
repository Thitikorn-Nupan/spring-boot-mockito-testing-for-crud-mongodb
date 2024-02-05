package com.ttknpdev.learnspringbootcrudmongodb.entity;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
// ** warning!! collection is not collation
// any validation annotations it will work after you mark @Validate with @RequestBody ??
@Document(collection = "employees") // this is way it will be mapped to a Document in the MongoDB database.
public class Employee {
    @Id
    // it will map _id on collection auto
    // again it is not auto increment
    private Long id;
    @NotBlank
    @NotNull
    @Size(max = 60)
    private String fullname;
    @Min(18)
    @Max(60)
    @NotNull
    private Short age;
    @Min(15000)
    @Max(250000)
    @NotNull
    private Float salary;
    @Pattern(regexp = "female|male") // no space
    @NotBlank
    @NotNull
    private String gender;

    public Employee(String fullname, Short age, Float salary, String gender) {
        this.fullname = fullname;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                '}';
    }
}
