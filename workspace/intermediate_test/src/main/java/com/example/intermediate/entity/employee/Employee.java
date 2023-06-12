package com.example.intermediate.entity.employee;

import com.example.intermediate.audit.Period;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TBL_EMPLOYEE")
@Getter
@Setter
@ToString
@Inheritance // 상속관계 시 부모엔티티에 작성하며, 기본 전략은 SINGLE_TABLE이다.
@DiscriminatorColumn(name = "DEPARTMENT") // SINGLE_TABLE전략 시 구분(Discrimination)컬럼이 추가되며, 기본 컬럼명은 DTYPE이다.
public abstract class Employee extends Period {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    private LocalDate birth;
    @NotNull
    private Integer career;
}

























