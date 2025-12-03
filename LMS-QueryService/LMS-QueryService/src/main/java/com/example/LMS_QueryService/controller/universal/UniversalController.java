package com.example.LMS_QueryService.controller.universal;

import com.example.LMS_QueryService.dto.UniversalDTO;
import com.example.LMS_QueryService.response.Response;
import com.example.LMS_QueryService.service.universal.UniversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("queryUniversal")
public class UniversalController {
    @Autowired
    private UniversalService universalService;

    // Get All Details Of Customer
    @GetMapping("/getUniversal")
    public ResponseEntity<Response<UniversalDTO>> getAllDetailsOfCustomer(@RequestParam int loanID){
        UniversalDTO allDetailsOfCustomer = universalService.getAllDetailsOfCustomer(loanID);
        Response<UniversalDTO> response = new Response<>("Get All Details For Customer", LocalDateTime.now(), allDetailsOfCustomer, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
