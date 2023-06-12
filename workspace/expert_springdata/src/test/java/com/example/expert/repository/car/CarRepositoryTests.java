package com.example.expert.repository.car;

import com.example.expert.entity.registration.Car;
import com.example.expert.entity.registration.CarStatusType;
import com.example.expert.entity.registration.CarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class CarRepositoryTests {

    @Autowired
    private CarRepository carRepository;
//    자동차 100대 등록
    @Test
    public void saveTest(){
        Car car = new Car();
        car.setCarStatus(CarStatusType.ENABLE);
        car.setCarReleaseDate(LocalDateTime.now());
        car.setCarPrice(10000L);
        car.setCarName("부릉이");
        car.setCarBrand(CarType.PORSCHE);

        carRepository.save(car);
    }

//    자동차 전체 정보와 차주 전체 정보 조회(페이징 처리)
//    자동차 정보 전체와 차주 명 수 조회
//    자동차 정보 전체와 차주 명 수 조회 후 차주 1명인 자동차 정보 전체 조회
//    차주가 10명 이상인 자동차 정보 전체 조회
//    차주의 평균 나이가 20대인 자동차 정보 전체 조회
//    자동차 현재 차주의 평균 나이가 90살 이상이면 자동차 폐차
}














