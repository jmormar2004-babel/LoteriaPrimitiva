package com.bootcamp.loteriaprimitiva.controllers;

import com.bootcamp.loteriaprimitiva.models.Usuario;
import com.bootcamp.loteriaprimitiva.services.IServicioUsuarios;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class ControladorUsuario {

    private final IServicioUsuarios servicioUsuarios;

    public ControladorUsuario(IServicioUsuarios servicioUsuarios){
        this.servicioUsuarios = servicioUsuarios;
    }

    @PutMapping("/{id}")
    public void registrarUsuario(@PathVariable String id, @RequestBody Usuario usuario){
        this.servicioUsuarios.agregarUsuario(usuario, id);
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return this.servicioUsuarios.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable String id){
        return this.servicioUsuarios.obtenerUsuario(id);
    }

    @PostMapping("/{id}/bets")
    public void registrarApuesta(@PathVariable String id, @RequestBody Set<Integer> apuesta){
        this.servicioUsuarios.registrarApuesta(id, apuesta);
    }

}
