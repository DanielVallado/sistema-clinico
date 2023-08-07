package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.dto.AGO_DTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.AGOMapper;
import com.clinica.controlpacientes.model.Paciente;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class AGOService {

    private PacienteService pacienteService;

    @Autowired
    private void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public List<AGO_DTO> getAllAGO() throws Exception {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }

        List<AGO_DTO> listDto = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            listDto.add(AGOMapper.mapToDTO(paciente));
        }

        return listDto;
    }

    public AGO_DTO getAGOById(Long id) throws Exception {
        return AGOMapper.mapToDTO(pacienteService.getPacienteById(id));
    }

}
