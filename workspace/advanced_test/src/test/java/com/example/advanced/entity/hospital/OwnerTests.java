package com.example.advanced.entity.hospital;

import com.example.advanced.repository.hospital.OwnerDAO;
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
public class OwnerTests {
    @Autowired
    OwnerDAO ownerDAO;

    @Test
    public void saveTest() {
        Owner owner1 = new Owner();
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();

        ownerDAO.save(owner1);
    }
}
