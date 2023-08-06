package com.clinica.controlcitas.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "email-service", path = "/email-service/email")
public interface IEmailClient {



}
