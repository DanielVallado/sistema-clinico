package com.clinica.controlhistorialclinico.repository;

import com.clinica.controlhistorialclinico.model.Pronostico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PronosticoRepository extends JpaRepository<Pronostico, Long> {

    List<Pronostico> findAllByPacienteId(Long id);

    void deleteAllByPacienteId(Long id);

}
