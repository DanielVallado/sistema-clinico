package com.clinica.controlpacientes.service;

import com.clinica.controlpacientes.dto.APP_DTO;
import com.clinica.controlpacientes.error.CPException;
import com.clinica.controlpacientes.mapper.APPMapper;
import com.clinica.controlpacientes.model.Paciente;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class APPService {

    @Autowired
    private PacienteService pacienteService;

    public List<APP_DTO> getAllAPP() throws Exception {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        if (pacientes.isEmpty()) {
            throw new CPException("No se encontraron datos.");
        }

        List<APP_DTO> listDto = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            listDto.add(APPMapper.mapToDTO(paciente));
        }

        return listDto;
    }

    public APP_DTO getAPPById(Long id) throws CPException {
        return APPMapper.mapToDTO(pacienteService.getPacienteById(id));
    }

}
