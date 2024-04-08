package com.patika.garantiservice.service;

import com.patika.garantiservice.converter.ApplicationConverter;
import com.patika.garantiservice.dto.request.ApplicationRequest;
import com.patika.garantiservice.dto.response.ApplicationResponse;
import com.patika.garantiservice.entity.Application;
import com.patika.garantiservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationConverter applicationConverter;

    /*
    public ApplicationService(ApplicationConverter applicationConverter) {
        this.applicationConverter = applicationConverter;
    }*/

    public ApplicationResponse createApplication(ApplicationRequest request) {

        Application application = applicationConverter.toApplication(request);

        return applicationConverter.toResponse(applicationRepository.save(application));
    }


    public List<ApplicationResponse> getAll() {
        List<Application> applications = applicationRepository.findAll();

        return applicationConverter.toResponseList(applications);
    }
}
