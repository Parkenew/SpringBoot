package com.example.test2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import com.example.test2.student.Student;
import com.example.test2.student.StudentRepository;

import java.io.*;
import java.util.ArrayList;

@SpringBootTest
@Transactional
@Rollback(value = false)
class Test2ApplicationTests {
	@Autowired
	private StudentRepository studentRepository;
	@Test
	@Transactional
	void testJpa() throws IOException {
		Student s1=new Student();
		File file= new File("C:\\sku\\dev\\Spring\\test2\\Abc1115.csv");
		ArrayList<Student> list=null;
		FileReader fr= new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		list = new ArrayList<Student>();
		String line =null;
		line = br.readLine();
		String[] temp=line.split(",");
		s1.setStdNo(Integer.parseInt(temp[0]));
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

}
