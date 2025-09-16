package com.alanlacan.ProyectoFinalAhorcado.service;

import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;
import com.alanlacan.ProyectoFinalAhorcado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validacion {

    private static UsuarioRepository usuarioRepository;

    @Autowired
    public Validacion(UsuarioRepository usuarioRepository){
        Validacion.usuarioRepository = usuarioRepository;
    }

    public String validarUsuario(Usuario usuario){
        if (usuarioRepository.existsByContraseña(usuario.getContraseña())){
            return "La contraseña: " + usuario.getContraseña() + " ya esta en uso";
        }

        return null;
    }

    public String deleteUsuario(Integer codigoUsuario){
        if (usuarioRepository.existsById(codigoUsuario)) {
            usuarioRepository.deleteById(codigoUsuario);
            return "El Usuario con el Codigo " + codigoUsuario + " ha sido eliminado correctamente";
        } else {
            return "No se encontró un Usuario con el Codigo " + codigoUsuario;
        }

    }
}
