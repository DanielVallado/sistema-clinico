package com.clinica.controlcitas.service;

import com.clinica.controlcitas.client.IPacienteClient;
import com.clinica.controlcitas.dto.CitaDTO;
import com.clinica.controlcitas.dto.client.EmailDto;
import com.clinica.controlcitas.dto.client.PacienteDTO;
import com.clinica.controlcitas.enums.EstatusCita;
import com.clinica.controlcitas.error.CCException;
import com.clinica.controlcitas.mapper.CitaMapper;
import com.clinica.controlcitas.model.Cita;
import com.clinica.controlcitas.repository.CitaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CitaService {

    private CitaRepository repository;
    private IPacienteClient pacienteClient;

    @Autowired
    private void setRepository(CitaRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setCitaClient(IPacienteClient pacienteClient) {
        this.pacienteClient = pacienteClient;
    }

    public List<CitaDTO> getAllCitas() throws Exception {
        List<Cita> listCitas = repository.findAll();
        if (listCitas.isEmpty()) {
            throw new CCException("No se encontraron datos.");
        }

        return listCitas.stream()
                .map(cita -> CitaMapper.mapToDTO(getPacienteById(cita.getPacienteId()), cita))
                .collect(Collectors.toList());
    }

    public List<CitaDTO> getCitasByPacienteId(Long pacienteId) throws Exception {
        PacienteDTO paciente = getPacienteById(pacienteId);

        List<Cita> listCitas = repository.findAllByPacienteId(pacienteId);
        if (listCitas.isEmpty()) {
            throw new CCException("No se encontraron datos.");
        }

        return listCitas.stream()
                .map(cita -> CitaMapper.mapToDTO(paciente, cita))
                .collect(Collectors.toList());
    }

    public Cita createCita(Cita cita) {
        pacienteClient.findPacienteById(cita.getPacienteId());
        cita.setEstatusCita(EstatusCita.AGENDADA);  // Estado default
        // Generar el token único
        String token = RandomStringUtils.randomAlphanumeric(32);

        // Enviar el correo de confirmación
        EmailDto email = new EmailDto();
        email.setToUser(new String[]{cita.getEmail()});
        email.setSubject("Confirmación de Cita");
        email.setMessage("¡Gracias por agendar tu cita! Haz clic en el siguiente enlace para confirmar:\n"
                + "http://localhost:8072/control-citas/citas/confirmar-cita?id=" + cita.getId());
        emailService.sendConfirmationEmail(cita.getEmail(), token);
        return repository.save(cita);
    }

    public void deleteCita(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteCitaByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

    private PacienteDTO getPacienteById(Long pacienteId) {
        ResponseEntity<PacienteDTO> response = pacienteClient.findPacienteById(pacienteId);
        return response.getBody();
    }

}
