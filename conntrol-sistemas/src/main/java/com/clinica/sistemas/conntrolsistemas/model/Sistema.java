package com.clinica.sistemas.conntrolsistemas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sistemas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sistema {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sistema")
    List<Componente> componentes;
    private  String descripcion;


}
