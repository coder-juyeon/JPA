package com.example.basic.repository;

import com.example.basic.domain.entity.SuperCar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SuperCarDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public SuperCar save(SuperCar superCar){
        entityManager.persist(superCar);
        entityManager.flush();
        return superCar;
    }

    public Optional<SuperCar> findById(Long id){
        return Optional.ofNullable(entityManager.find(SuperCar.class, id));
    }

    public void delete(SuperCar superCar){
        entityManager.remove(superCar);
    }

//    전체 조회
    public List<SuperCar> findAll(){
        String query = "select s from SuperCar s";
        TypedQuery<SuperCar> result = entityManager.createQuery(query, SuperCar.class);
        return result.getResultList();
    }

//    전체 조회 페이징
    public List<SuperCar> findAllWithPaging(int offset, int amount){
    String query = "select s from SuperCar s order by s.id desc";
    TypedQuery<SuperCar> result = entityManager.createQuery(query, SuperCar.class);
    result.setFirstResult(offset - 1);
    result.setMaxResults(amount);
    return result.getResultList();
}

    //    특정 출시 날짜 조회
    public List<SuperCar> findAllByReleaseDate(String releaseDate){
        String query = "select s from SuperCar s where function('to_char', s.releaseDate, 'yyyymmdd') = :releaseDate";
        TypedQuery<SuperCar> result = entityManager.createQuery(query, SuperCar.class);
        result.setParameter("releaseDate", releaseDate);
        return result.getResultList();
    }

//    특정 출시 기간 조회
    public List<SuperCar> findAllBetweenReleaseDate(LocalDateTime startDate, LocalDateTime endDate){
        String query = "select s from SuperCar s where s.releaseDate between :startDate and :endDate";
        TypedQuery<SuperCar> result = entityManager.createQuery(query, SuperCar.class);
        result.setParameter("startDate", startDate);
        result.setParameter("endDate", endDate);
        return result.getResultList();
    }

//    특정 이름 가격 조회
    public List<SuperCar> findAllByNameAndPrice(String name, Long price){
        String query = "select s from SuperCar s where s.name = :name and s.price = :price";
        TypedQuery<SuperCar> result = entityManager.createQuery(query, SuperCar.class);
        result.setParameter("name", name);
        result.setParameter("price", price);
        return result.getResultList();
    }

//    4천만원이 넘는 가격대의 자동차 삭제
//    특정 출시일의 자동차 가격을 10% 상승

}





















