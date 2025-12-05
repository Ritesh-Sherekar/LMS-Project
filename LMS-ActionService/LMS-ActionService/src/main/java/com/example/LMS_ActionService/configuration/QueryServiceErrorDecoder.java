package com.example.LMS_ActionService.configuration;

import com.example.LMS_ActionService.exception.QueryServiceBadRequestException;
import com.example.LMS_ActionService.exception.QueryServiceNotFoundException;
import com.example.LMS_ActionService.exception.ServiceUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class QueryServiceErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()){

            case 404: throw new QueryServiceNotFoundException("Query Service Not Found The Expected result!");

            case 400: throw new QueryServiceBadRequestException("Query Service Get Bad Request!");

            case 503: throw new ServiceUnavailableException("Query Service Not Running!");

            default: throw new RuntimeException("RuntimeException Occurred!");
        }
    }
}
