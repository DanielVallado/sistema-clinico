package com.clinica.controlcitas.service;

import com.clinica.controlcitas.client.IEmailClient;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CitaService {

    private final CitaRepository repository;
    private final IPacienteClient pacienteClient;
    private final IEmailClient emailClient;

    @Autowired
    public CitaService(CitaRepository repository, IPacienteClient pacienteClient, IEmailClient emailClient) {
        this.repository = repository;
        this.pacienteClient = pacienteClient;
        this.emailClient = emailClient;
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

    public Cita getCitaById(Long id) throws Exception {
        Optional<Cita> cita = repository.findById(id);
        if (cita.isEmpty()) {
            throw new CCException("No se encontraron datos.");
        }

        return cita.get();
    }

    public CitaDTO createCita(Cita cita) throws Exception {
        if (cita.getId() != null) {
            throw new CCException("No puede contener un ID preestablecido.");
        }

        pacienteClient.findPacienteById(cita.getPacienteId());
        cita.setEstatusCita(EstatusCita.AGENDADA);  // Estado default
        cita = repository.save(cita);
        sendEmail(cita);
        return CitaMapper.mapToDTO(getPacienteById(cita.getPacienteId()), cita);
    }

    public CitaDTO updateCita(Long id, Cita newCita) throws Exception {
        this.getCitaById(id);
        newCita.setId(id);
        Cita updatedCita = repository.save(newCita);
        return CitaMapper.mapToDTO(getPacienteById(updatedCita.getPacienteId()), updatedCita);
    }

    public void deleteCita(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteCitaByPacienteId(Long pacienteId) {
        repository.deleteAllByPacienteId(pacienteId);
    }

    public CitaDTO confirmarCita(Long id) throws Exception {
        Cita cita = this.getCitaById(id);
        cita.setEstatusCita(EstatusCita.CONFIRMADA);
        return updateCita(id, cita);
    }

    public CitaDTO cancelarCita(Long id) throws Exception {
        Cita cita = this.getCitaById(id);
        cita.setEstatusCita(EstatusCita.CANCELADA);
        return updateCita(id, cita);
    }

    private PacienteDTO getPacienteById(Long pacienteId) {
        ResponseEntity<PacienteDTO> response = pacienteClient.findPacienteById(pacienteId);
        return response.getBody();
    }

    private void sendEmail(Cita cita) {
        EmailDto email = new EmailDto();
        email.setToUser(new String[]{cita.getEmail()});
        email.setSubject("Confirmación de Cita");
        email.setMessage("¡Gracias por agendar tu cita! Haz click en el siguiente enlace para confirmar: \n"
                + "http://localhost:8072/control-citas/citas/confirmar-cita?id=" + cita.getId());
        emailClient.sendEmail(email);
        log.info("Email de confirmación enviado.");
    }

}
