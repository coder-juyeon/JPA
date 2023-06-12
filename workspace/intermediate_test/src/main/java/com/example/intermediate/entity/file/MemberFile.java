package com.example.intermediate.entity.file;

import com.example.intermediate.type.FileType;
import com.example.intermediate.type.RepresentationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TBL_MEMBER_FILE")
public class MemberFile extends File {
    //    대표 이미지 검사
    @Enumerated(EnumType.STRING)
    RepresentationType representationType;
}
