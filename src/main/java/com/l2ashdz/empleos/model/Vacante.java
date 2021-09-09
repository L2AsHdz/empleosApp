package com.l2ashdz.empleos.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String estatus;
    private LocalDate fecha;
    private Double salario;
    private Integer destacado;
    private String imagen = "no-image.png";
    private String detalles;
    //@Transient
    @OneToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public void reset() {
        this.imagen = null;
    }

}