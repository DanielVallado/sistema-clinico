package com.clinica.controlsistemas.repository;

import com.clinica.controlsistemas.model.Aparato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AparatoRepository extends JpaRepository<Aparato, Long> {

    void deleteAllBySistemaId(Long id);

    List<Aparato> findAllBySistemaId(Long sistemaId);

}
