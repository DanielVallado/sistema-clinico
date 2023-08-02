package com.clinica.controlpacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "antecedentes_perinatales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentesPerinatales {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaNacimiento;
    private int sdg;
    private int apgar;
    private float peso;
    private float talla;
    private int numeroEmbarazo;
    private String problemasDuranteParto;

}
