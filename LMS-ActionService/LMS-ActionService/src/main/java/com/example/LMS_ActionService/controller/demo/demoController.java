package com.example.LMS_ActionService.controller.demo;

import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.response.Response;
import com.example.LMS_ActionService.service.demo.DemoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class demoController {
    @Autowired
    private DemoClass demoClass;

    @GetMapping("/demoEMI")
    public EMI getByLoanID(@RequestParam Integer loanID){
        Response<EMI> loanByID = demoClass.getLoanByID(loanID);
        return loanByID.getData();
    }
}
