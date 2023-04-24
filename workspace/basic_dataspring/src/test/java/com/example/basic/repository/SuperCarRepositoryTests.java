package com.example.basic.repository;

import com.example.basic.domain.entity.SuperCar;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback(false)
@SpringBootTest
@Slf4j
public class SuperCarRepositoryTests {
    @Autowired
    SuperCarRepository superCarRepository;

    @Test
    public void saveTest(){
    }

    @Test
    public void findByNameTest(){
        superCarRepository.findByName("");
    }



}
