package com.clinica.controlsistemas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aparatos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Aparato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sistema sistema;
    private String nombre;
    private String descripcion;

}
