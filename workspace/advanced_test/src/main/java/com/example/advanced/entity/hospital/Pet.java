package com.example.advanced.entity.hospital;

import com.example.advanced.audit.Period;
import com.example.advanced.type.GenderType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBL_PET")
public class Pet extends Period {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String petName;
    @NotNull
    private String petDisease;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderType petGender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;
}
