package com.example.basic.repository;

import com.example.basic.domain.entity.SuperCar;
import com.example.basic.type.SuperCarType;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperCarRepository extends JpaRepository<SuperCar, Long> {
    public List<SuperCar> findByName(String name);
    public List<SuperCar> findByNameContaining(String name);
    public List<SuperCar> findByNameStartingWith(String name);
    public List<SuperCar> findByNameEndingWith(String name);
    public List<SuperCar> findTop2ByPriceGreaterThanEqualOrderBynamOrderByName(int price);
    public Boolean existsByColor(String color);
    public int countAllByType(SuperCarType superCarType);
    public void deleteByPriceGreaterThanEqual(int price);
}
