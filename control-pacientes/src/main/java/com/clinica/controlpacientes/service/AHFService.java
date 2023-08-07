package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.dto.AHF_DTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.AHFMapper;
import com.clinica.controlpacientes.model.Paciente;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class AHFService {

    private PacienteService pacienteService;

    @Autowired
    private void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public List<AHF_DTO> getAllAHF() throws Exception {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }

        List<AHF_DTO> listDto = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            listDto.add(AHFMapper.mapToDTO(paciente));
        }

        return listDto;
    }

    public AHF_DTO getAHFById(Long id) throws Exception{
        return AHFMapper.mapToDTO(pacienteService.getPacienteById(id));
    }

}
