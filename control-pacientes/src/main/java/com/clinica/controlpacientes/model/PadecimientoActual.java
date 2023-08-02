package com.clinica.controlpacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "padecimiento_actual")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PadecimientoActual {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date inicio;
    private String descripcion;
    private String evolucion;
    private String estadoActual;

}
