package com.clinica.controlpacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "antecedentes_personales_patologicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentesPersonalesPatologicos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cirugias;
    private String adicciones;
    private String traumatismos;
    private String ets;
    private String alergias;
    private String padecimientosArticulares;

}
