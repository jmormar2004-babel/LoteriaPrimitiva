package com.bootcamp.loteriaprimitiva.services;

import com.bootcamp.loteriaprimitiva.models.Usuario;

import java.util.List;
import java.util.Set;

public interface IServicioUsuarios {
    byte agregarUsuario(Usuario usuario, String idUsuario);
    List<Usuario> listarUsuarios();
    Usuario obtenerUsuario(String idUsuario);
    byte registrarApuesta(String idUsuario, Set<Integer> apuesta);
}
