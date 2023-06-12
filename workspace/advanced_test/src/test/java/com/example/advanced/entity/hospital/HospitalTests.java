package com.example.advanced.entity.hospital;

import com.example.advanced.repository.hospital.PetDAO;
import com.example.advanced.type.GenderType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Transactional
@Rollback(false)
@SpringBootTest
@Slf4j
public class HospitalTests {
    @Autowired
    private PetDAO petDAO;

    @Test
    public void saveTest() {
        String[] arDisease = {"감기", "배탈", "방광염", "설사", "피부병"};

        for (int i = 0; i < 10; i++) {
            Pet pet = new Pet();
            pet.setPetName("뽀삐" + i + 1);
            pet.setPetGender(GenderType.MALE);
            pet.setPetDisease(arDisease[new Random().nextInt(arDisease.length)]);
            petDAO.save(pet);
        }
    }
}
