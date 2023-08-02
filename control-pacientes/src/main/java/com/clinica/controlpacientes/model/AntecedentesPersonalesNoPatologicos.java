package com.clinica.controlpacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "antecedentes_personales_no_patologicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentesPersonalesNoPatologicos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String habitosPersonales;
    @Column(name = "baño")
    private String banno;
    private String habitacion;
    private boolean tabaquismo;
    private boolean alcoholismo;
    private String vacunas;
    private String actividadFisica;
    private String alimentacion;
    private String estadoCivil;
    private boolean zoonosis;

}
