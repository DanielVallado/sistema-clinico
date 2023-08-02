package com.clinica.controlpacientes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APNP_DTO {

    private String nombre;
    private String apellidos;
    private String habitosPersonales;
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
