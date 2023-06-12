package com.example.expert.repository.car;

import com.example.expert.entity.registration.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface CarRepository extends JpaRepository<Car, Long> {

    public Car save(Car car);
}
