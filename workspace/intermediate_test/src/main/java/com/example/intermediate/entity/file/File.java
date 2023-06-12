package com.example.intermediate.entity.file;

import com.example.intermediate.audit.Period;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBL_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class File extends Period {
    @Id
    @GeneratedValue
    private Long id;
    //    파일이름
    @NotNull
    private String name;
    //    UUID
    @NotNull
    private String uuid;
    //    파일경로(년/월/일)
    @NotNull
    private String filePath;
    //    파일크기
    private Long fileSize;
}
