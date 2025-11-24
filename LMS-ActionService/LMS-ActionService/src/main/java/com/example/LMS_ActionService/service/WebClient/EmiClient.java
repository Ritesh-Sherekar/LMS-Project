package com.example.LMS_ActionService.service.WebClient;

import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmiClient {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Response<EMI> getEmiByLoanID(int LoanID) {

        return webClientBuilder.build()
                .get()
                .uri("http://LMS-QueryService/queryEmi/getEmiByLoanID?LoanID=" + LoanID)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<EMI>>() {})
                .block();
    }
}
