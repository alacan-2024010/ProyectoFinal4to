package com.alanlacan.ProyectoFinalAhorcado.controller;

import com.alanlacan.ProyectoFinalAhorcado.model.Palabra;
import com.alanlacan.ProyectoFinalAhorcado.model.Usuario;
import com.alanlacan.ProyectoFinalAhorcado.service.PalabraService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/palabras")
public class PalabraController {
    private final PalabraService palabraService;

    public PalabraController(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping
    public List<Palabra>getAllPalabras(){
        return palabraService.getAllPalabras();
    }

    @GetMapping("/{codigoPalabra}")
    public Palabra getPalabraByCodigo( @PathVariable Integer codigoPalabra){
        return  palabraService.getPalabraByCodigo(codigoPalabra);
    }

    @PostMapping
    public ResponseEntity<Object> createPalabra( @RequestBody Palabra palabra){
        try{
            Palabra nuevo = palabraService.savePalabra(palabra);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{codigoPalabra}")
    public ResponseEntity<?> updatePalabra(@PathVariable Integer codigoPalabra, @RequestBody Palabra palabra) {
        try{
            Palabra actualizar = palabraService.updatePalabra(codigoPalabra, palabra);
            return new ResponseEntity<>(actualizar, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<String> updatePalabraSinId(@RequestBody Palabra palabra) {
        return new ResponseEntity<>("Se requiere un Código de palabra en la ruta para realizar una actualización", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<String> updatePalabraSin(@RequestBody Palabra palabra) {
        return new ResponseEntity<>("Se requiere un Código de palabra en la ruta para realizar una actualización", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{codigoPalabra}")
    public ResponseEntity<String> deletePalabra(@PathVariable Integer codigoPalabra){
        if (palabraService.getPalabraByCodigo(codigoPalabra) != null) {
            palabraService.deletePalabra(codigoPalabra);
            return new ResponseEntity<>("La palabra  con el Codigo " + codigoPalabra + " ha sido eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró a la Palabra con el Codigo " + codigoPalabra, HttpStatus.NOT_FOUND);
        }
    }
}
