package com.clinica.controlpacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "antecedentes_gineco_obstetricos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentesGinecoObstetricos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date menarca;
    private String ritmoMenstrual;
    private int ipsa;
    private int partos;
    private Date fup;
    private int abortos;
    private int cesareas;

}
