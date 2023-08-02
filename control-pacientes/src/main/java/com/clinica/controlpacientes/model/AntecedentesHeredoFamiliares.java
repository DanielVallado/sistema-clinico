package com.clinica.controlpacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "antecedentes_heredo_familiares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentesHeredoFamiliares {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean cardiopatias;
    private boolean alergias;
    private boolean diabetes;
    private boolean nefropatias;
    private boolean psiquiatricos;
    private boolean neurologicas;
    private String otrosSindromes;

}
