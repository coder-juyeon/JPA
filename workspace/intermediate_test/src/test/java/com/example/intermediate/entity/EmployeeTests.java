package com.example.intermediate.entity;

import com.example.intermediate.entity.employee.Developer;
import com.example.intermediate.entity.employee.Employee;
import com.example.intermediate.repository.EmployeeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
@Rollback(false)
@Transactional
public class EmployeeTests {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    public void saveTest() {
        Employee developer = new Developer();
        developer.setName("한동석");
        developer.setBirth(LocalDate.of(2000, 12, 04));
        developer.setCareer(7);
    }

    @Test
    public void findByIdTest() {
        employeeDAO.findById(1L).map(Employee::toString).ifPresent(log::info);
    }

    @Test
    public void findAll() {
    }
}
