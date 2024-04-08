package com.patika.kredinbizdeservice.service.interfaces;

import com.patika.kredinbizdeservice.client.dto.request.AkbankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.request.GarantiApplicationRequest;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;

import java.util.List;
import java.util.Optional;

public interface IApplicationService {
    Application createApplicationForAkbank(ApplicationRequest request);
    Application createApplicationForGaranti(ApplicationRequest request);
    AkbankApplicationRequest prepareAkbankApplicationRequest(User user);
    GarantiApplicationRequest prepareGarantiApplicationRequest(User user);
    List<Application> getApplicationsByEmail(String email);
    Application createApplication(Application application);
    List<Application> getAllApplications();
    Optional<Application> getApplicationById(Long id);
    Optional<List<Application>> getApplicationsByUserId(Long id);
    Application updateApplication(Application application);
    void deleteApplication(Long id);
}
