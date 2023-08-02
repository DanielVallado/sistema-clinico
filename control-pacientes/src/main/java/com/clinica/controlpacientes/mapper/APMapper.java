package com.clinica.controlpacientes.mapper;

import com.clinica.controlpacientes.dto.AP_DTO;
import com.clinica.controlpacientes.model.AntecedentesPerinatales;
import com.clinica.controlpacientes.model.Paciente;

public class APMapper {

    public static AP_DTO mapToDTO(Paciente paciente) {
        AP_DTO antecedentesDTO = new AP_DTO();
        AntecedentesPerinatales antecedentes = paciente.getAntecedentesPerinatales();

        antecedentesDTO.setNombre(paciente.getNombre());
        antecedentesDTO.setApellidos(paciente.getApellidos());
        antecedentesDTO.setFechaNacimiento(antecedentes.getFechaNacimiento());
        antecedentesDTO.setSdg(antecedentes.getSdg());
        antecedentesDTO.setApgar(antecedentes.getApgar());
        antecedentesDTO.setPeso(antecedentes.getPeso());
        antecedentesDTO.setTalla(antecedentes.getTalla());
        antecedentesDTO.setNumeroEmbarazo(antecedentes.getNumeroEmbarazo());
        antecedentesDTO.setProblemasDuranteParto(antecedentes.getProblemasDuranteParto());

        return antecedentesDTO;
    }


}
