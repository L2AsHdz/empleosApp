package com.l2ashdz.empleos.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "Vacantes")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de la vacante es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripcion no puede quedar vacia")
    private String descripcion;

    @NotNull(message = "Debe seleccionar una fecha")
    private LocalDate fecha;

    @Positive(message = "El salario debe ser un numero positivo")
    private Double salario;

    @NotBlank(message = "Debe ingresar los detalles de la vacante")
    private String detalles;

    private Integer destacado;
    private String estatus;
    private String imagen = "no-image.png";

    //@Transient
    @OneToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public void reset() {
        this.imagen = null;
    }

}