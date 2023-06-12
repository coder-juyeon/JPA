package com.example.intermediate.entity.member;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBL_MEMBER")
public class Member extends User {
    @NotNull
    private String socialSecurityMember;
//    주민번호
}
