package com.clinica.controlcitas.model;

import com.clinica.controlcitas.enums.EstatusCita;
import com.clinica.controlcitas.enums.TipoPaciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long pacienteId;
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private TipoPaciente tipoPaciente;

    @Enumerated(EnumType.STRING)
    private EstatusCita estatusCita;
    private int noSesion;
    private BigDecimal costoTerapia;
    private String email;
    private String numeroTelefonico;

}
