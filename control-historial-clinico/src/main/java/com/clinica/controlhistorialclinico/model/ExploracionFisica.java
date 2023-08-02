package com.clinica.controlhistorialclinico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "exploracion_fisica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExploracionFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long pacienteId;
    private String descripcion;
    private Date fechaExploracion;

}
