package com.clinica.controlcitas.client;

import com.clinica.controlcitas.dto.client.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", path = "/email-service/email")
public interface IEmailClient {

    @PostMapping
    ResponseEntity<?> sendEmail(@RequestBody EmailDto email);

}
