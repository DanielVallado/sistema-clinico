package com.clinica.controlpacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "pacientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @Column(name = "no_expediente")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Date creacionExpediente;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String estadoCivil;
    private String escolaridad;
    private String ocupacion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "antecedentes_heredo_familiares_id")
    private AntecedentesHeredoFamiliares antecedentesHeredoFamiliares;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "antecedentes_personales_no_patologicos_id")
    private AntecedentesPersonalesNoPatologicos antecedentesPersonalesNoPatologicos;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "antecedentes_personales_patologicos_id")
    private AntecedentesPersonalesPatologicos antecedentesPersonalesPatologicos;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "antecedentes_gineco_obstetricos_id")
    private AntecedentesGinecoObstetricos antecedentesGinecoObstetricos;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "antecedentes_perinatales_id")
    private AntecedentesPerinatales antecedentesPerinatales;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "padecimiento_actual_id")
    private PadecimientoActual padecimientoActual;

}
