package com.alanlacan.ProyectoFinalAhorcado.service;

import com.alanlacan.ProyectoFinalAhorcado.model.Palabra;
import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;
import com.alanlacan.ProyectoFinalAhorcado.repository.PalabraRepository;
import com.alanlacan.ProyectoFinalAhorcado.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServiceImplements implements PalabraService{

    private final PalabraRepository palabraRepository;
    private final Validacion validacion;

    public PalabraServiceImplements(PalabraRepository palabraRepository, Validacion validacion) {
        this.palabraRepository = palabraRepository;
        this.validacion = validacion;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra getPalabraByCodigo(Integer codigoPalabra) {
        return palabraRepository.findById(codigoPalabra).orElse(null);
    }

    @Override
    public Palabra savePalabra(Palabra palabra) {
        String mensaje = validacion.validarPalabra(palabra);
        if (mensaje != null){
            throw new DataIntegrityViolationException(mensaje);
        }
        return palabraRepository.save(palabra);
    }

    @Override
    public Palabra updatePalabra(Integer codigoPalabra, Palabra palabra) {
        Palabra actualizar = palabraRepository.findById(codigoPalabra).orElse(null);

        if (actualizar == null) {
            throw new DataIntegrityViolationException("Palabra no encontrada con el código: " + codigoPalabra);
        }

        if (actualizar!=null){
            actualizar.setPalabra(palabra.getPalabra());
            actualizar.setPistaUno(palabra.getPistaUno());
            actualizar.setPistaDos(palabra.getPistaDos());
            actualizar.setPistaTres(palabra.getPistaTres());

            String mensaje = validacion.validarPalabra(palabra);
            if (mensaje != null){
                throw new DataIntegrityViolationException(mensaje);
            }
            return palabraRepository.save(palabra);
        }
        return null;
    }

    @Override
    public void deletePalabra(Integer codigoPalabra) {
        palabraRepository.deleteById(codigoPalabra);
    }
}
