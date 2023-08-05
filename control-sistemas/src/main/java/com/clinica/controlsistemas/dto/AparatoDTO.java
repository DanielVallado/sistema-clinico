package com.clinica.controlsistemas.dto;

import com.clinica.controlsistemas.model.Sistema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AparatoDTO {

    private Long id;
    private String sistema;
    private String nombre;
    private String descripcion;

}
