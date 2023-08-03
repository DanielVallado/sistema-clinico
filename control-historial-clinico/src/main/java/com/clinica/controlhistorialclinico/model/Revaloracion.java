package com.clinica.controlhistorialclinico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "revaloracion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revaloracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long pacienteId;

    @NonNull
    private Long sistemaId;
    private String descripcion;
    private Date fechaRevaloracion;

}
