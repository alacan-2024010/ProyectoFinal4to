package com.alanlacan.ProyectoFinalAhorcado.repository;

import com.alanlacan.ProyectoFinalAhorcado.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {
    boolean existsByPalabra(String palabra);
}
