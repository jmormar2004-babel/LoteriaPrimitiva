package com.bootcamp.loteriaprimitiva.repositories;

import com.bootcamp.loteriaprimitiva.models.Usuario;

import java.util.List;

public interface IRepositorioUsuarios {
    void init();
    void setUsuarios(List<Usuario> usuarios);
    List<Usuario> exportarUsuarios();
}
