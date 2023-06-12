package com.example.intermediate.entity.member;

import com.example.intermediate.audit.Period;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "TBL_USER")
public abstract class User extends Period { //부모 클래스를 단독으로 사용하지 않는다면, abstrct로 만든다.
    @Id
    @GeneratedValue
    private String id;
    @Column(unique = true)
    @NotNull
    private String userId;
    @NotNull
    private Integer password;
    @NotNull
    private String name;
    private String address;
//    아이디
//    비밀번호
//    이름
//    주소
}
