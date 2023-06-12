package com.example.advanced.entity.hospital;

import com.example.advanced.repository.hospital.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class PetRepositoryTests {
    @Autowired
    private PetRepository petRepository;

    @Test
    public void saveTest(){

    }
}
