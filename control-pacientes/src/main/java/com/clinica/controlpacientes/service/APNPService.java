package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.dto.APNP_DTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.APNPMapper;
import com.clinica.controlpacientes.model.Paciente;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class APNPService {

    @Autowired
    private PacienteService pacienteService;

    public List<APNP_DTO> getAllAPNP() throws Exception {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }

        List<APNP_DTO> listDto = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            listDto.add(APNPMapper.mapToDTO(paciente));
        }

        return listDto;
    }

    public APNP_DTO getAPNPById(Long id) throws Exception {
        return APNPMapper.mapToDTO(pacienteService.getPacienteById(id));
    }

}
