package com.example.test2.student;

import com.example.test2.DataNotFoundException;
import com.example.test2.record.SiteUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getList(){
        return this.studentRepository.findAll();
    }
    public Page<Student> getList(int page, String kw) {

        Pageable pageable = PageRequest.of(page, 10);
        Specification<Student> spec = search(kw);
        System.out.println("찾습니다");
        return this.studentRepository.findAll(spec,pageable);
    }

    public Student getStudent(String StdNo){
        Optional<Student> student=this.studentRepository.findById(StdNo);
        if (student.isPresent()){
            return student.get();
        } else {
            throw new DataNotFoundException("student not found");
        }
    }

    @Transactional
    public void create(String StdNo,String email,Integer kor,Integer eng,Integer math,
                       Integer hist,Integer sci,Integer total, String mgrCode,String accCode,String locCode){
        System.out.println("db를 저장합니다");
        Student s=new Student();
        s.setStdNo(StdNo);
        s.setEmail(email);
        s.setKor(kor);
        s.setEng(eng);
        s.setMath(math);
        s.setHist(hist);
        s.setSci(sci);
        s.setMgrCode(mgrCode);
        s.setTotal(total);
        s.setAccCode(accCode);
        s.setLocCode(locCode);
        this.studentRepository.save(s);

    }

    public void delete(Student student){
        this.studentRepository.delete(student);
    }
    @SuppressWarnings("unused")
    private Specification<Student> search(String kw){

        return new Specification<>(){
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Student> s, CriteriaQuery<?> query, CriteriaBuilder cb){
                query.distinct(true);
                return cb.like(s.get("StdNo"), "%" + kw + "%");

            }
        };

    }
}
