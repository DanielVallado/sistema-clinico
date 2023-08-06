package com.clinica.emailservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto implements Serializable {

    private String[] toUser;
    private String subject;
    private String message;

}