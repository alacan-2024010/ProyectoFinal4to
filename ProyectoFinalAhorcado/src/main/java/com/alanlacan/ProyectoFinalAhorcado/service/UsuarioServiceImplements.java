package com.alanlacan.ProyectoFinalAhorcado.service;

import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;
import com.alanlacan.ProyectoFinalAhorcado.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioServiceImplements implements  UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final Validacion validacion;

    public UsuarioServiceImplements(UsuarioRepository usuarioRepository, Validacion validacion) {
        this.usuarioRepository = usuarioRepository;
        this.validacion = validacion;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioByCodigo(Integer codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario).orElse(null);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String mensaje = validacion.validarUsuario(usuario);
        if (mensaje != null){
            throw new DataIntegrityViolationException(mensaje);
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer codigoUsuario, Usuario usuario) {
        Usuario actualizar = usuarioRepository.findById(codigoUsuario).orElse(null);

        if (actualizar != null){
            actualizar.setNombre(usuario.getNombre());
            actualizar.setContraseña(usuario.getContraseña());

            String mensaje = validacion.validarUsuario(actualizar);
            if (mensaje !=null){
                throw new DataIntegrityViolationException(mensaje);
            }
            return usuarioRepository.save(actualizar);
        }
        return  null;
    }

    @Override
    public void deleteUsuario(Integer codigoUsuario) {
        usuarioRepository.deleteById(codigoUsuario);

    }
}
