package com.clinica.controlpacientes.repository;

import com.clinica.controlpacientes.model.PadecimientoActual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PARepository extends JpaRepository<PadecimientoActual, Long> {

    

}
