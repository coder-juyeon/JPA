package com.example.basic.domain.entity;

import com.example.basic.type.SuperCarType;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_SUPER_CAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuperCar {
    @Id @GeneratedValue
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @NotNull private SuperCarType type;
    @NotNull private String name;
    @NotNull private String color;
    private Double price;
    private LocalDateTime releaseDate;

    @Builder
    public SuperCar(Long id, SuperCarType type, String name, String color) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.color = color;
    }
}












