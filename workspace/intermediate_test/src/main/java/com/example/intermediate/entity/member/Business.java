package com.example.intermediate.entity.member;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBL_BUSINESS")
public class Business extends User {
    @NotNull
    private String businessNumber;
//    사업자 등록 번호
}
