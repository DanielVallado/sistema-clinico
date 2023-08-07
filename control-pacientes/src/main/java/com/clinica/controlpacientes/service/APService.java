package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.dto.AP_DTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.APMapper;
import com.clinica.controlpacientes.model.Paciente;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class APService {

    private PacienteService pacienteService;

    @Autowired
    private void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public List<AP_DTO> getAllAP() throws Exception {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }

        List<AP_DTO> listDto = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            listDto.add(APMapper.mapToDTO(paciente));
        }

        return listDto;
    }

    public AP_DTO getAPById(Long id) throws Exception {
        return APMapper.mapToDTO(pacienteService.getPacienteById(id));
    }

}
