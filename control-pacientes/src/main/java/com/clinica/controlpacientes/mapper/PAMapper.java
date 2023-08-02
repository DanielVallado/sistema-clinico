package com.clinica.controlpacientes.mapper;

import com.clinica.controlpacientes.dto.PA_DTO;
import com.clinica.controlpacientes.model.Paciente;
import com.clinica.controlpacientes.model.PadecimientoActual;

public class PAMapper {

    public static PA_DTO mapToDTO(Paciente paciente) {
        PA_DTO padecimientoDTO = new PA_DTO();
        PadecimientoActual padecimientoActual = paciente.getPadecimientoActual();

        padecimientoDTO.setNombre(paciente.getNombre());
        padecimientoDTO.setApellidos(paciente.getApellidos());
        padecimientoDTO.setInicio(padecimientoActual.getInicio());
        padecimientoDTO.setDescripcion(padecimientoActual.getDescripcion());
        padecimientoDTO.setEvolucion(padecimientoActual.getEvolucion());
        padecimientoDTO.setEstadoActual(padecimientoActual.getEstadoActual());

        return padecimientoDTO;
    }

}
