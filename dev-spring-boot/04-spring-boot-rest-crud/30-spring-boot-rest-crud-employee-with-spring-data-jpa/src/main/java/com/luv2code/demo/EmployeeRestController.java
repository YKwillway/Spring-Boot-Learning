package com.luv2code.demo;

import com.luv2code.demo.entity.Employee;
import com.luv2code.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{theId}")
    public Employee findById(@PathVariable int theId) {
        var theEmployee = employeeService.findById(theId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + theId);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // @PostMappingでも削除はできるが、@DeleteMappingを使う
    @DeleteMapping("employees/{theId}")
    public void deleteById(@PathVariable int theId) {
        var theEmployee = employeeService.findById(theId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + theId);
        }
        employeeService.deleteById(theId);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
}
