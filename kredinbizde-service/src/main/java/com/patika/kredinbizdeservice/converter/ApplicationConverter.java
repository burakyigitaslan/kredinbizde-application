package com.patika.kredinbizdeservice.converter;

import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.Audit;
import com.patika.kredinbizdeservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ApplicationConverter {

    public Application toApplication(ApplicationRequest request, User user) {
        Application application = new Application();
        application.setUser(user);
        application.setApplicationStatus(ApplicationStatus.INITIAL);

        application.setCreatedDate(LocalDate.now());
        application.setUpdatedDate(LocalDate.now());
        return application;
    }
}
