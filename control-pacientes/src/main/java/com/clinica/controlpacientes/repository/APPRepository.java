package com.clinica.controlpacientes.repository;

import com.clinica.controlpacientes.model.AntecedentesPersonalesPatologicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APPRepository extends JpaRepository<AntecedentesPersonalesPatologicos, Long> {



}
