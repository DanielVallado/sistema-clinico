package com.clinica.controlpacientes.mapper;

import com.clinica.controlpacientes.dto.AGO_DTO;
import com.clinica.controlpacientes.model.AntecedentesGinecoObstetricos;
import com.clinica.controlpacientes.model.Paciente;

public class AGOMapper {


    public static AGO_DTO mapToDTO(Paciente paciente) {
        AGO_DTO antecedentesDTO = new AGO_DTO();
        AntecedentesGinecoObstetricos antecedentes = paciente.getAntecedentesGinecoObstetricos();

        antecedentesDTO.setNombre(paciente.getNombre());
        antecedentesDTO.setApellidos(paciente.getApellidos());
        antecedentesDTO.setMenarca(antecedentes.getMenarca());
        antecedentesDTO.setRitmoMenstrual(antecedentes.getRitmoMenstrual());
        antecedentesDTO.setIpsa(antecedentes.getIpsa());
        antecedentesDTO.setPartos(antecedentes.getPartos());
        antecedentesDTO.setFup(antecedentes.getFup());
        antecedentesDTO.setAbortos(antecedentes.getAbortos());
        antecedentesDTO.setCesareas(antecedentes.getCesareas());

        return antecedentesDTO;
    }

}
