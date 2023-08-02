package com.clinica.controlpacientes.mapper;

import com.clinica.controlpacientes.dto.APP_DTO;
import com.clinica.controlpacientes.model.AntecedentesPersonalesPatologicos;
import com.clinica.controlpacientes.model.Paciente;

public class APPMapper {

    public static APP_DTO mapToDTO(Paciente paciente) {
        APP_DTO antecedentesDTO = new APP_DTO();
        AntecedentesPersonalesPatologicos antecedentes = paciente.getAntecedentesPersonalesPatologicos();

        antecedentesDTO.setNombre(paciente.getNombre());
        antecedentesDTO.setApellidos(paciente.getApellidos());
        antecedentesDTO.setCirugias(antecedentes.getCirugias());
        antecedentesDTO.setAdicciones(antecedentes.getAdicciones());
        antecedentesDTO.setTraumatismos(antecedentes.getTraumatismos());
        antecedentesDTO.setEts(antecedentes.getEts());
        antecedentesDTO.setAlergias(antecedentes.getAlergias());
        antecedentesDTO.setPadecimientosArticulares(antecedentes.getPadecimientosArticulares());

        return antecedentesDTO;
    }

}
