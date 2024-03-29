package com.example.test2.student;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Student {
    @Id
    private String StdNo;
    @Column
    private String email;
    @Column
    private int kor;
    @Column
    private int eng;
    @Column
    private int math;
    @Column
    private int sci;
    @Column
    private int hist;
    @Column
    private int total;
    @Column
    private String mgrCode;
    @Column
    private String accCode;
    @Column
    private String locCode;

}

