package com.example.intermediate.entity.file;

import com.example.intermediate.type.FileType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBL_REVIEW_FILE")
public class ReviewFile extends File {
    //    이미지 검사
    @Enumerated(EnumType.STRING)
    FileType fileType;
}
