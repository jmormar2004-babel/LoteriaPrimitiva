package com.bootcamp.loteriaprimitiva.services;

import com.bootcamp.loteriaprimitiva.models.Usuario;

import java.util.List;
import java.util.Set;

public interface IServicioUsuarios {
    void agregarUsuario(Usuario usuario, String idUsuario);
    List<Usuario> listarUsuarios();
    Usuario obtenerUsuario(String idUsuario);
    void registrarApuesta(String idUsuario, Set<Integer> apuesta);
}
