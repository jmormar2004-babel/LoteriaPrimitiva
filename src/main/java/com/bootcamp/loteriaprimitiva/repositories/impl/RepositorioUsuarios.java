package com.bootcamp.loteriaprimitiva.repositories.impl;

import com.bootcamp.loteriaprimitiva.models.Usuario;
import com.bootcamp.loteriaprimitiva.repositories.IRepositorioUsuarios;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioUsuarios implements IRepositorioUsuarios {

    private List<Usuario> usuarios;

    @Override
    public void init() {
        usuarios = new ArrayList<>();
    }

    @Override
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public List<Usuario> exportarUsuarios() {
        return usuarios;
    }
}
