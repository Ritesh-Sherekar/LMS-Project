package com.example.LMS_ActionService.repo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "secureApi", url = "https://api.openweathermap.org/data/2.5")
public interface SecureApiClient {
    @GetMapping("/weather")
    String getData(@RequestParam("lat") double latitude,
                   @RequestParam("lon") double longitude,
                   @RequestParam("appid") String apiKey);
}
