package com.clinica.controlcitas.dto.client;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String[] toUser;
    private String subject;
    private String message;

}