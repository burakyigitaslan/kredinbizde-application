package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.dto.request.AkbankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.AkbankApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "akbank-service", url = "localhost:8081")
public interface AkbankServiceClient {

    @PostMapping("api/akbank/v1/applications")
    AkbankApplicationResponse createApplication(@RequestBody AkbankApplicationRequest request);
}
