package com.example.LMS_ActionService.contr;

import com.example.LMS_ActionService.ser.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecureCont {
    @Autowired
    private ApiService apiService;

    @GetMapping("/third")
    public ResponseEntity<String> weatherApi(@RequestParam("lat") double lat,
                                             @RequestParam("lon") double lon,
                                             @RequestParam("appid") String appid){
        String string = apiService.callSecureApi(lat,lon,appid);
        return ResponseEntity.ok(string);
    }
}
