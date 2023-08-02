package com.clinica.controlpacientes.repository;

import com.clinica.controlpacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {



}
