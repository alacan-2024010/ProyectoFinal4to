package com.alanlacan.ProyectoFinalAhorcado.service;

import com.alanlacan.ProyectoFinalAhorcado.model.Palabra;
import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;

import java.util.List;

public interface PalabraService {

    List<Palabra> getAllPalabras();
    Palabra getPalabraByCodigo(Integer codigoPalabra);
    Palabra savePalabra(Palabra palabra);
    Palabra updatePalabra(Integer codigoPalabra, Palabra palabra);
    void deletePalabra(Integer codigoPalabra);
}
