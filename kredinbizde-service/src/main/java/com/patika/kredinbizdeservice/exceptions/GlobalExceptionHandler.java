package com.patika.kredinbizdeservice.exceptions;

import com.patika.kredinbizdeservice.configuration.KafkaConfiguration;
import com.patika.kredinbizdeservice.exceptions.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public GlobalExceptionHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @ExceptionHandler(KredinbizdeException.class)
    public ResponseEntity<ExceptionResponse> handleKredinbizdeException(KredinbizdeException exception) {
        log.error("exception occurred. {0}", exception.getCause());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(prepareExceptionResponse(exception, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception exception) {

        String errorMessage = "Exception occurred at: " + LocalDateTime.now() + "\n" + "Exception message: " + exception.getMessage();

        kafkaTemplate.send(KafkaConfiguration.LOG_TOPIC, errorMessage);

        log.error("exception occurred. {0}", exception.getCause());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(prepareExceptionResponse(exception, HttpStatus.BAD_REQUEST));
    }

    private ExceptionResponse prepareExceptionResponse(Exception exception, HttpStatus httpStatus) {
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .httpStatus(httpStatus)
                .build();
    }

}
