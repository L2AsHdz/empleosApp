package com.l2ashdz.empleos.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El username no puede quedar vacio")
    private String username;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El password no quedar en blanco")
    private String password;
    private Integer estatus;
    private LocalDate fechaRegistro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioPerfil",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil"))
    private List<Perfil> perfiles;

    public void addPerfil(Perfil perfil) {
        if (perfiles == null) {
            this.perfiles = new ArrayList();
        }
        perfiles.add(perfil);
    }

}
