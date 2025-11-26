package com.example.LMS_ActionService.service.WebClient;

import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
public class EmiClient {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Response<EMI> getEmiByLoanID(int LoanID) {

//        UriComponents loanID = UriComponentsBuilder.fromPath("lb://LMS-QUERYSERVICE/queryEmi/getEmiByLoanID").queryParam("LoanID", LoanID).build();
//        System.out.println("Loan ID "+ loanID);

//        String uri = UriComponentsBuilder
//                .fromUriString("lb://LMS-QUERYSERVICE/queryEmi/getEmiByLoanID")
//                .queryParam("LoanID", LoanID)
//                .build()
//                .toUriString();
//
//        System.out.println("uri "+uri);

//        Response<EMI> block = webClientBuilder.build()
//                .get()
//                .uri(uri)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Response<EMI>>() {
//                })
//                .block();
//
//        System.out.println("Block :- "+block);


//        Response<EMI> block = webClientBuilder.build()
//                .get()
//                .uri(uriBuilder ->
//                        uriBuilder.scheme("lb")
//                                .host("LMS-QueryService")
//                                .path("queryEmi/getEmiByLoanID")
//                                .queryParam("LoanID", LoanID)
//                                .build())
//                .header("Authorization",TokenPropagation.getToken())
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Response<EMI>>() {
//                }).block();

     //   System.out.println("Response "+ block);

        System.out.println(TokenPropagation.getToken());
        Response<EMI> block = webClientBuilder.build()
                .get()
                .uri("localhost:5558/queryEmi/getEmiByLoanID?LoanID=1")
                .header("Authorization", TokenPropagation.getToken())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<EMI>>() {
                }).block();
        System.out.println("Response "+ block);
        return block;
    }
}
