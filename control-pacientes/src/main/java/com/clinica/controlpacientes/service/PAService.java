package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.dto.PA_DTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.PAMapper;
import com.clinica.controlpacientes.model.Paciente;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PAService {

    @Autowired
    private PacienteService pacienteService;

    public List<PA_DTO> getAllPA() throws Exception {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }

        List<PA_DTO> listDto = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            listDto.add(PAMapper.mapToDTO(paciente));
        }

        return listDto;
    }

    public PA_DTO getPAById(Long id) throws Exception {
        return PAMapper.mapToDTO(pacienteService.getPacienteById(id));
    }

}
