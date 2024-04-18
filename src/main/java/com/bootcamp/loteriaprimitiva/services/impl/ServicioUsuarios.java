package com.bootcamp.loteriaprimitiva.services.impl;

import com.bootcamp.loteriaprimitiva.models.Usuario;
import com.bootcamp.loteriaprimitiva.repositories.IRepositorioUsuarios;
import com.bootcamp.loteriaprimitiva.services.IServicioUsuarios;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ServicioUsuarios implements IServicioUsuarios {

    private IRepositorioUsuarios repositorioUsuarios;

    public ServicioUsuarios(IRepositorioUsuarios repositorioUsuarios){
        this.repositorioUsuarios = repositorioUsuarios;
        repositorioUsuarios.init();
    }

    @Override
    public void agregarUsuario(Usuario usuario, String idUsuario) {
        List<Usuario> usuarios = this.repositorioUsuarios.exportarUsuarios();
        for (Usuario usuarioEnLista : usuarios) {
            if(usuarioEnLista.getId().equals(idUsuario)) return;
        }
        usuarios.add(usuario);
        this.repositorioUsuarios.setUsuarios(usuarios);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return this.repositorioUsuarios.exportarUsuarios();
    }

    @Override
    public Usuario obtenerUsuario(String idUsuario) {
        List<Usuario> usuarios = this.repositorioUsuarios.exportarUsuarios();
        for (Usuario usuario : usuarios) {
            if(usuario.getId().equals(idUsuario)) return usuario;
        }
        return null;
    }

    @Override
    public void registrarApuesta(String idUsuario, Set<Integer> apuesta) {

        List<Usuario> usuarios = this.repositorioUsuarios.exportarUsuarios();
        Usuario usuario = null;

        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getId().equals(idUsuario)){
                usuario = usuarios.remove(i);
                break;
            }
        }

        if(usuario == null) return;

        List<Set<Integer>> apuestasDeUsuario = usuario.getApuestas();

        if(apuestasDeUsuario == null) apuestasDeUsuario = new ArrayList<>();

        apuestasDeUsuario.add(apuesta);

        usuario.setApuestas(apuestasDeUsuario);

        usuarios.add(usuario);

        this.repositorioUsuarios.setUsuarios(usuarios);

    }
}
