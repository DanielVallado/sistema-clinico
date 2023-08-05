package com.clinica.controlcitas.repository;

import com.clinica.controlcitas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findAllByPacienteId(Long id);

    void deleteAllByPacienteId(Long id);

}
