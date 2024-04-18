package com.bootcamp.loteriaprimitiva.controllers;

import com.bootcamp.loteriaprimitiva.models.Usuario;
import com.bootcamp.loteriaprimitiva.services.IServicioUsuarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class ControladorUsuario {

    private final IServicioUsuarios servicioUsuarios;

    private static final Logger logger = LoggerFactory.getLogger(ControladorUsuario.class);

    public ControladorUsuario(IServicioUsuarios servicioUsuarios){

        this.servicioUsuarios = servicioUsuarios;
    }


    @PostMapping("/{id}")
    public ResponseEntity<String> registrarUsuario(@PathVariable String id, @RequestBody Usuario usuario){

        switch (this.servicioUsuarios.agregarUsuario(usuario, id)){
            case 0 -> {
                return ResponseEntity.status(CREATED).body("Usuario agregado");
            }
            case 1 -> {
                logger.error("El usuario ya existe en la base de datos");
                return ResponseEntity.status(CONFLICT).body("El usuario ya existe en la base de datos");
            }
            case 2 -> {
                logger.error("Alguna de las apuestas del usuario tiene un tamaño desigual a 6.");
                return ResponseEntity.status(BAD_REQUEST).body("El tamaño de la apuesta es desigual a 6.");
            }
            case 3 -> {
                logger.error("Algún número de alguna apuesta es mayor que 49 o menor que 1.");
                return ResponseEntity.status(BAD_REQUEST).body("Algún número es mayor que 49 o menor que 1.");
            }
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.status(OK).body(this.servicioUsuarios.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String id){
        if(this.servicioUsuarios.obtenerUsuario(id) == null) return ResponseEntity.status(NOT_FOUND).body(null);
        return ResponseEntity.status(OK).body(this.servicioUsuarios.obtenerUsuario(id));
    }

    @PostMapping("/{id}/bets")
    public ResponseEntity<String> registrarApuesta(@PathVariable String id, @RequestBody Set<Integer> apuesta){
        switch (this.servicioUsuarios.registrarApuesta(id, apuesta)){
            case 0 -> {
                return ResponseEntity.status(CREATED).body("Apuesta agregada");
            }
            case 1 -> {
                logger.error("La apuesta porque tiene números mayor a 49.");
                return ResponseEntity.status(BAD_REQUEST).body("La apuesta tiene números mayor a 49 o inferiores a 1.");
            }
            case 2 -> {
                logger.error("El usuario no ha sido encontrado.");
                return ResponseEntity.status(NOT_FOUND).body("El usuario no ha sido encontrado.");
            }
            case 3 -> {
                logger.error("El tamaño de la apuesta debe ser 6.");
                return ResponseEntity.status(BAD_REQUEST).body("El tamaño de la apuesta debe ser 6.");
            }
            case 4 -> {
                logger.warn("El usuario ya había apostado esos números.");
            }
        }
        return null;
    }

}
