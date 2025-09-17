package com.alanlacan.ProyectoFinalAhorcado.repository;

import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByNombre(String nombre);
}
