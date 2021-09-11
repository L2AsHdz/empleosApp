package com.l2ashdz.empleos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    public DatabaseWebSecurity(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, estatus FROM Usuarios WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT u.username, p.perfil FROM UsuarioPerfil up INNER JOIN Usuarios u " +
                        "ON u.id=up.idUsuario INNER JOIN Perfiles p ON p.id=up.idPerfil WHERE u.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //Los recursos estaticos no requieren autenticacion
                .antMatchers("/bootstrap/**",
                        "/iamages/**",
                        "/titnymce/**",
                        "/logos/**").permitAll()

                //Las vistas publicas no requieren autenticacion
                .antMatchers("/",
                        "/home",
                        "/singup",
                        "/search",
                        "/bcrypt/**",
                        "/vacantes/view/**").permitAll()

                //Asignar permisos a URLs por roles
                .antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")

                //Todas las demas URLs requieren autenticacion
                .anyRequest().authenticated()

                //El formulario del login no requiere autenticacion
                .and().formLogin().loginPage("/login").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
