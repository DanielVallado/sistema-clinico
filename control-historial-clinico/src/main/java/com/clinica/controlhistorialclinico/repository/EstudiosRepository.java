package com.clinica.controlhistorialclinico.repository;

import com.clinica.controlhistorialclinico.model.Estudios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudiosRepository extends JpaRepository<Estudios, Long> {

    List<Estudios> findAllByPacienteId(Long id);

    void deleteAllByPacienteId(Long id);

}
