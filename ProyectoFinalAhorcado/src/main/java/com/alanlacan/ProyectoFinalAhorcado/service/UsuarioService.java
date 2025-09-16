package com.alanlacan.ProyectoFinalAhorcado.service;

import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> getAllUsuarios();
    Usuario getUsuarioByCodigo(Integer codigoUsuario);
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Integer codigoUsuario, Usuario usuario);
    void deleteUsuario(Integer codigoUsuario);

}
