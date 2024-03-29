package com.example.test2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class tableController {



    @GetMapping("/jspSample")
    public ModelAndView jspSample() throws Exception{
        ModelAndView mav= new ModelAndView("jspSample");
        mav.addObject("name","홍길동");

        List<String> jspSample =new ArrayList<String>();
        jspSample.add("국어 : 100점");
        jspSample.add("수학 : 90점");
        mav.addObject("list", jspSample);
        return mav;
    }

}
