package com.alanlacan.ProyectoFinalAhorcado.controller;


import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;
import com.alanlacan.ProyectoFinalAhorcado.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{codigoUsuario}")
    public Usuario getUsuarioByCodigo(@PathVariable Integer codigoUsuario){
        return usuarioService.getUsuarioByCodigo(codigoUsuario);
    }

    @PostMapping
    public ResponseEntity<Object> createUsario(@RequestBody Usuario usuario){
        try{
            Usuario nuevo = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{codigoUsuario}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer codigoUsuario, @RequestBody Usuario usuario) {
        try{
            Usuario actualizar = usuarioService.updateUsuario(codigoUsuario, usuario);
            return new ResponseEntity<>(actualizar, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer codigoUsuario){
        if (usuarioService.getUsuarioByCodigo(codigoUsuario) != null) {
            usuarioService.deleteUsuario(codigoUsuario);
            return new ResponseEntity<>("El Usuario  con el Codigo " + codigoUsuario + " ha sido eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró un Usuario con el Codigo " + codigoUsuario, HttpStatus.NOT_FOUND);
        }
    }




}
