package com.bootcamp.loteriaprimitiva.controllers;

import com.bootcamp.loteriaprimitiva.models.Usuario;
import com.bootcamp.loteriaprimitiva.services.IServicioUsuarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class ControladorUsuario {

    private final IServicioUsuarios servicioUsuarios;

    public ControladorUsuario(IServicioUsuarios servicioUsuarios){
        this.servicioUsuarios = servicioUsuarios;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> registrarUsuario(@PathVariable String id, @RequestBody Usuario usuario){
        if(this.servicioUsuarios.agregarUsuario(usuario, id)){
            return ResponseEntity.status(CREATED).body("Usuario Registrado");
        }
        return ResponseEntity.status(CONFLICT).body("El usuario ya existe en la base de datos");
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
                return ResponseEntity.status(BAD_REQUEST).body("La apuesta porque tiene números mayor a 49.");
            }
            case 2 -> {
                return ResponseEntity.status(NOT_FOUND).body("El usuario no ha sido encontrado.");
            }
            case 3 -> {
                return ResponseEntity.status(BAD_REQUEST).body("El tamaño de la apuesta debe ser 6.");
            }
        }
        return null;
    }

}
