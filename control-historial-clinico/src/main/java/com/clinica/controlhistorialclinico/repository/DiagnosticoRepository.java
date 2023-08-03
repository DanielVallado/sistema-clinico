package com.clinica.controlhistorialclinico.repository;

import com.clinica.controlhistorialclinico.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

    List<Diagnostico> findAllByPacienteId(Long id);

    void deleteAllByPacienteId(Long id);

}
