package com.example.LMS_ActionService.ser;

import com.example.LMS_ActionService.repo.SecureApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    @Autowired
    private SecureApiClient client;

    public String callSecureApi(double lat, double lon, String apiKey) {
        return client.getData(lat, lon, apiKey);
    }
}
