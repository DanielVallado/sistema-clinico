package com.clinica.emailservice.service.controller;

import com.clinica.emailservice.service.dto.EmailDto;
import com.clinica.emailservice.service.error.ESException;
import com.clinica.emailservice.service.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@Log4j2
public class EmailController {

    private EmailService service;

    @Autowired
    private void setService(EmailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDto) {
        try {
            log.info("Envío de email.");
            service.enqueueEmail(emailDto);
            return new ResponseEntity<>("Email enviado.", HttpStatus.OK);
        } catch (ESException e) {
            log.warn(e.getMessage());
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.warn("Error al enviar el email.");
            log.error(e);
            return new ResponseEntity<>("Error al enviar el email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
