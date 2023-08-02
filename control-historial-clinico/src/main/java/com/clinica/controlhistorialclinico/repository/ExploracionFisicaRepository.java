package com.clinica.controlhistorialclinico.repository;

import com.clinica.controlhistorialclinico.model.ExploracionFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExploracionFisicaRepository extends JpaRepository<ExploracionFisica, Long> {

    List<ExploracionFisica> findAllByPacienteId(Long id);

    void deleteAllByPacienteId(Long id);

}
