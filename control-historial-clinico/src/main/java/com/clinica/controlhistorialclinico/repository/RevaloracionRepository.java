package com.clinica.controlhistorialclinico.repository;

import com.clinica.controlhistorialclinico.model.Revaloracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevaloracionRepository extends JpaRepository<Revaloracion, Long> {

    List<Revaloracion> findAllByPacienteId(Long id);

    void deleteAllByPacienteId(Long id);

}
