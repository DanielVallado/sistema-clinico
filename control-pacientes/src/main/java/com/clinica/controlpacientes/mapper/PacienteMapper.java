package com.clinica.controlpacientes.mapper;

import com.clinica.controlpacientes.dto.PacienteDTO;
import com.clinica.controlpacientes.model.Paciente;

public class PacienteMapper {

    public static PacienteDTO mapToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setCreacionExpediente(paciente.getCreacionExpediente());
        pacienteDTO.setNombre(paciente.getNombre());
        pacienteDTO.setApellidos(paciente.getApellidos());
        pacienteDTO.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDTO.setDireccion(paciente.getDireccion());
        pacienteDTO.setTelefono(paciente.getTelefono());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setEstadoCivil(paciente.getEstadoCivil());
        pacienteDTO.setEscolaridad(paciente.getEscolaridad());
        pacienteDTO.setOcupacion(paciente.getOcupacion());
        return pacienteDTO;
    }

}

