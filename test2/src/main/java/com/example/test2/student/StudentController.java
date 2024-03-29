package com.example.test2.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/table")
@RequiredArgsConstructor
@Controller
public class  StudentController {

    private final StudentService studentService;


    @GetMapping("/list")
    public String table(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Student> paging = this.studentService.getList(page,kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "student_List";
    }


    @GetMapping(value ="/modify/{StdNo}")
    public String modify(Model model, @PathVariable("StdNo") String StdNo){
        Student student =studentService.getStudent(StdNo);
        model.addAttribute("student",student);
        return "student_detail";
    }

    @GetMapping(value="/create")
    public String create(){
        return "student_create";
    }

    @PostMapping("/create")
    public String create(@RequestParam(value = "StdNo") String StdNo,@RequestParam(value = "email") String email,
                         @RequestParam(value="kor") Integer kor,@RequestParam(value = "eng") Integer eng,
                         @RequestParam(value = "math") Integer math,@RequestParam(value = "hist") Integer hist,
                         @RequestParam(value = "sci") Integer sci,@RequestParam(value = "total") Integer total,
                         @RequestParam(value = "mgrCode") String mgrCode,@RequestParam(value = "accCode") String accCode,
                         @RequestParam(value = "locCode") String locCode){
        this.studentService.create(StdNo,email,kor,eng,math,hist,sci,total,mgrCode,accCode,locCode);
        return "redirect:/table/list";
    }
    @GetMapping("/delete/{StdNo}")
    public String delete(@PathVariable("StdNo") String StdNo){
        Student student=studentService.getStudent(StdNo);
        this.studentService.delete(student);
        return "redirect:/table/list";
    }
}
