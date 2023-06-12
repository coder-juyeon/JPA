package com.example.intermediate.entity.Computer;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class Hardware {
    @NotNull
    private Integer ram;
    @NotNull
    private Integer ssd;
    @NotNull
    private String gpu;
    @NotNull
    private String processor;
}
