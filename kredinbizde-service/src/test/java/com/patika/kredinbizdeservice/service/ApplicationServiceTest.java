package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.client.AkbankServiceClient;
import com.patika.kredinbizdeservice.client.GarantiServiceClient;
import com.patika.kredinbizdeservice.client.dto.response.AkbankApplicationResponse;
import com.patika.kredinbizdeservice.client.dto.response.GarantiApplicationResponse;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicationConverter applicationConverter;

    @Mock
    private UserService userService;

    @Mock
    private AkbankServiceClient akbankServiceClient;

    @Mock
    private GarantiServiceClient garantiServiceClient;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createApplicationForAkbank_Success() {
        ApplicationRequest request = new ApplicationRequest();
        request.setEmail("test@example.com");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        Application application = new Application();

        when(userService.getUserByEmail(request.getEmail())).thenReturn(user);
        when(applicationConverter.toApplication(request, user)).thenReturn(application);
        when(applicationRepository.save(application)).thenReturn(application);
        when(akbankServiceClient.createApplication(any())).thenReturn(new AkbankApplicationResponse());

        Application createdApplication = applicationService.createApplicationForAkbank(request);

        assertNotNull(createdApplication);
        assertEquals(application, createdApplication);

        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    void createApplicationForGaranti_Success() {
        ApplicationRequest request = new ApplicationRequest();
        request.setEmail("test@example.com");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        Application application = new Application();

        when(userService.getUserByEmail(request.getEmail())).thenReturn(user);
        when(applicationConverter.toApplication(request, user)).thenReturn(application);
        when(applicationRepository.save(application)).thenReturn(application);
        when(garantiServiceClient.createApplication(any())).thenReturn(new GarantiApplicationResponse());

        Application createdApplication = applicationService.createApplicationForGaranti(request);

        assertNotNull(createdApplication);
        assertEquals(application, createdApplication);

        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    void getApplicationsByEmail_Success() {
        String email = "test@example.com";

        User user = new User();
        user.setId(1L);

        Application application1 = new Application();
        application1.setId(1L);

        Application application2 = new Application();
        application2.setId(2L);

        List<Application> applications = Arrays.asList(application1, application2);

        when(userService.getUserByEmail(email)).thenReturn(user);
        when(applicationRepository.findByUserId(user.getId())).thenReturn(Optional.of(applications));

        List<Application> foundApplications = applicationService.getApplicationsByEmail(email);

        assertNotNull(foundApplications);
        assertEquals(2, foundApplications.size());

        verify(applicationRepository, times(1)).findByUserId(user.getId());
    }

    @Test
    void createApplication_Success() {
        Application application = new Application();
        when(applicationRepository.save(application)).thenReturn(application);

        Application savedApplication = applicationService.createApplication(application);

        assertEquals(application, savedApplication);
        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    void getAllApplications_Success() {
        Application application1 = new Application();
        Application application2 = new Application();
        List<Application> applications = new ArrayList<>();
        applications.add(application1);
        applications.add(application2);
        when(applicationRepository.findAll()).thenReturn(applications);

        List<Application> retrievedApplications = applicationService.getAllApplications();

        assertEquals(2, retrievedApplications.size());
        verify(applicationRepository, times(1)).findAll();
    }

    @Test
    void getApplicationById_Success() {
        Long id = 1L;
        Application application = new Application();
        when(applicationRepository.findById(id)).thenReturn(Optional.of(application));

        Optional<Application> retrievedApplication = applicationService.getApplicationById(id);

        assertEquals(application, retrievedApplication.orElse(null));
        verify(applicationRepository, times(1)).findById(id);
    }

    @Test
    void updateApplication_Success() {
        Application application = new Application();
        when(applicationRepository.save(application)).thenReturn(application);

        Application updatedApplication = applicationService.updateApplication(application);

        assertEquals(application, updatedApplication);
        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    void deleteApplication_Success() {
        Long id = 1L;
        doNothing().when(applicationRepository).deleteById(id);

        applicationService.deleteApplication(id);

        verify(applicationRepository, times(1)).deleteById(id);
    }
}
