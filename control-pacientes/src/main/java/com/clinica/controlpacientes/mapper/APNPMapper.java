package com.clinica.controlpacientes.mapper;

import com.clinica.controlpacientes.dto.APNP_DTO;
import com.clinica.controlpacientes.model.AntecedentesPersonalesNoPatologicos;
import com.clinica.controlpacientes.model.Paciente;

public class APNPMapper {

    public static APNP_DTO mapToDTO(Paciente paciente) {
        APNP_DTO antecedentesDTO = new APNP_DTO();
        AntecedentesPersonalesNoPatologicos antecedentes = paciente.getAntecedentesPersonalesNoPatologicos();

        antecedentesDTO.setNombre(paciente.getNombre());
        antecedentesDTO.setApellidos(paciente.getApellidos());
        antecedentesDTO.setHabitosPersonales(antecedentes.getHabitosPersonales());
        antecedentesDTO.setBanno(antecedentes.getBanno());
        antecedentesDTO.setHabitacion(antecedentes.getHabitacion());
        antecedentesDTO.setTabaquismo(antecedentes.isTabaquismo());
        antecedentesDTO.setAlcoholismo(antecedentes.isAlcoholismo());
        antecedentesDTO.setVacunas(antecedentes.getVacunas());
        antecedentesDTO.setActividadFisica(antecedentes.getActividadFisica());
        antecedentesDTO.setAlimentacion(antecedentes.getAlimentacion());
        antecedentesDTO.setEstadoCivil(antecedentes.getEstadoCivil());
        antecedentesDTO.setZoonosis(antecedentes.isZoonosis());

        return antecedentesDTO;
    }


}
