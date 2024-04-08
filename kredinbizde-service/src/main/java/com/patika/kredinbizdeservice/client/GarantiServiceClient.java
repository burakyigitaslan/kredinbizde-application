package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.dto.request.GarantiApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.GarantiApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "garanti-service", url = "localhost:8083")
public interface GarantiServiceClient {

    @PostMapping("api/garanti/v1/applications")
    GarantiApplicationResponse createApplication(@RequestBody GarantiApplicationRequest  request);
}
