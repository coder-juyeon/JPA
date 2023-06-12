package com.example.advanced.entity.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBL_FILE")
public class File {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileUuid;
    private Long fillSize;
}
