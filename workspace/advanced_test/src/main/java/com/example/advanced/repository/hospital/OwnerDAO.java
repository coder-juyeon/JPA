package com.example.advanced.repository.hospital;

import com.example.advanced.entity.hospital.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class OwnerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //    추가
    public Owner save(Owner owner) {
        entityManager.persist(owner);
        return owner;
    }

    //    조회
    public Optional<Owner> select(Long id) {
        return Optional.ofNullable(entityManager.find(Owner.class, id));
    }

//    수정
//    삭제
}
