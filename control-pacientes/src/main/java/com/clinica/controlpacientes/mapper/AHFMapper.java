package com.clinica.controlpacientes.mapper;

import com.clinica.controlpacientes.dto.AHF_DTO;
import com.clinica.controlpacientes.model.AntecedentesHeredoFamiliares;
import com.clinica.controlpacientes.model.Paciente;

public class AHFMapper {

    public static AHF_DTO mapToDTO(Paciente paciente) {
        AHF_DTO antecedentesDTO = new AHF_DTO();
        AntecedentesHeredoFamiliares antecedentes = paciente.getAntecedentesHeredoFamiliares();

        antecedentesDTO.setNombre(paciente.getNombre());
        antecedentesDTO.setApellidos(paciente.getApellidos());
        antecedentesDTO.setCardiopatias(antecedentes.isCardiopatias());
        antecedentesDTO.setAlergias(antecedentes.isAlergias());
        antecedentesDTO.setDiabetes(antecedentes.isDiabetes());
        antecedentesDTO.setNefropatias(antecedentes.isNefropatias());
        antecedentesDTO.setNeurologicas(antecedentes.isNeurologicas());
        antecedentesDTO.setPsiquiatricos(antecedentes.isPsiquiatricos());
        antecedentesDTO.setOtrosSindromes(antecedentes.getOtrosSindromes());

        return antecedentesDTO;
    }

}
