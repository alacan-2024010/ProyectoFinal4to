package com.alanlacan.ProyectoFinalAhorcado.service;

import com.alanlacan.ProyectoFinalAhorcado.model.Palabra;
import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;
import com.alanlacan.ProyectoFinalAhorcado.repository.PalabraRepository;
import com.alanlacan.ProyectoFinalAhorcado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validacion {

    private static UsuarioRepository usuarioRepository;
    private static PalabraRepository palabraRepository;

    @Autowired
    public Validacion(UsuarioRepository usuarioRepository, PalabraRepository palabraRepository){
        Validacion.usuarioRepository = usuarioRepository;
        Validacion.palabraRepository=palabraRepository;
    }

    public String validarUsuario(Usuario usuario){
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty() ){
            return "El nombre no puede estar vacío";
        }
        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()){
            return "La contraseña no puede estar vacía";
        }
        if (usuarioRepository.existsByNombre(usuario.getNombre())){
            return "El nombre de Usuario: " + usuario.getNombre() + " ya esta en uso";
        }

        return null;
    }

    public String validarPalabra(Palabra palabra){

        if (palabra.getPalabra() ==null || palabra.getPalabra().isEmpty()){
            return "La Palabra no puede estar vacía";
        }

        if (palabra.getPistaUno() ==null || palabra.getPistaUno().isEmpty() || palabra.getPistaDos()==null||
                palabra.getPistaDos().isEmpty()|| palabra.getPistaTres()==null ||palabra.getPistaTres().isEmpty()){
            return "Las pistas no pueden estar vacías";
        }

        if (palabraRepository.existsByPalabra((palabra.getPalabra()))){
            return "La Palabra: " + palabra.getPalabra() + " ya esta en la base de datos";
        }

        String palabraVerificar = palabra.getPalabra();

        if (palabraVerificar.length()<=8){
            return "La palabra: "+ palabra.getPalabra() +" debe tener minimo 8 caracteres";
        }

        if (!palabraVerificar.matches("^[a-zA-Z]+$")) {
            return "La palabra: "+palabra.getPalabra()+" solo puede contener letras";
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
