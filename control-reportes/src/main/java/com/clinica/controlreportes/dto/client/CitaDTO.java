package com.clinica.controlreportes.dto.client;

import com.clinica.controlreportes.enums.EstatusCita;
import com.clinica.controlreportes.enums.TipoPaciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CitaDTO {

    private Long id;
    private Long pacienteId;
    private String nombre;
    private String apellidos;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime fechaHora;
    private TipoPaciente tipoPaciente;
    private EstatusCita estatusCita;
    private int noSesion;
    private BigDecimal costoTerapia;
    private String email;
    private String numeroTelefonico;

}
