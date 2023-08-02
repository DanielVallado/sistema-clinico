package com.clinica.controlpacientes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APP_DTO {

    private String nombre;
    private String apellidos;
    private String cirugias;
    private String adicciones;
    private String traumatismos;
    private String ets;
    private String alergias;
    private String padecimientosArticulares;

}
