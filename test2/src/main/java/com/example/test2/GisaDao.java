package com.example.test2;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.test2.student.Student;
import com.example.test2.student.StudentRepository;

import java.io.*;
import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class GisaDao implements CommandLineRunner{

    private final StudentRepository studentRepository ;

    void makeData() throws IOException {
        System.out.println("파일을 읽습니다");
        File file= new File("C:\\sku\\dev\\Spring\\test2\\Abc1115.csv");
        if(file.exists()){
            System.out.println("파일이 존재합니다");
        }else{
            System.out.println("실패했습니다");
        }
        ArrayList<Student> list = null;
        FileReader fr= new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        list = new ArrayList<Student>();
        String line =null;

        while((line = br.readLine())!=null) {

            String[] temp = line.split(",");
            insertData(temp);

        }
        br.close();
        fr.close();

    }
    @Transactional
    void insertData(String[] temp){
        Student s1=new Student();
        s1.setStdNo(temp[0]);
        s1.setEmail(temp[1]);
        s1.setKor(Integer.parseInt(temp[2].trim()));
        s1.setEng(Integer.parseInt(temp[3].trim()));
        s1.setMath(Integer.parseInt(temp[4].trim()));
        s1.setSci(Integer.parseInt(temp[5].trim()));
        s1.setHist(Integer.parseInt(temp[6].trim()));
        s1.setTotal(Integer.parseInt(temp[7].trim()));
        s1.setMgrCode(temp[8]);
        s1.setAccCode(temp[9]);
        s1.setLocCode(temp[10]);

        this.studentRepository.save(s1);
    }

    @Override
    public void run(String... args) throws Exception {
        makeData();
    }
}
