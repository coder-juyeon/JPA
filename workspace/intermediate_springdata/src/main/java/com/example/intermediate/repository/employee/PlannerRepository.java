package com.example.intermediate.repository.employee;

import com.example.intermediate.entity.employee.Planner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    public Page<Planner> findAll(Pageable pageable);
}
