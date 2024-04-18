package com.bootcamp.loteriaprimitiva.services.impl;

import com.bootcamp.loteriaprimitiva.controllers.ControladorUsuario;
import com.bootcamp.loteriaprimitiva.models.Usuario;
import com.bootcamp.loteriaprimitiva.repositories.IRepositorioUsuarios;
import com.bootcamp.loteriaprimitiva.services.IServicioUsuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ServicioUsuarios implements IServicioUsuarios {

    private IRepositorioUsuarios repositorioUsuarios;
    private static final Logger logger = LoggerFactory.getLogger(ServicioUsuarios.class);

    public ServicioUsuarios(IRepositorioUsuarios repositorioUsuarios){
        this.repositorioUsuarios = repositorioUsuarios;
        repositorioUsuarios.init();
    }

    @Override
    public byte agregarUsuario(Usuario usuario, String idUsuario) {
        logServicio();
        List<Usuario> usuarios = this.repositorioUsuarios.exportarUsuarios();
        for (Usuario usuarioEnLista : usuarios) {
            if(usuarioEnLista.getId().equals(idUsuario)) return 1;
        }
        usuario.setId(idUsuario);
        if(usuario.getApuestas().size() != 6) return 2;

        for (Set<Integer> apuesta : usuario.getApuestas()) {
            for (Integer i : apuesta) {
                if(i<1 || i>49) return 3;
            }
        }

        usuarios.add(usuario);
        this.repositorioUsuarios.setUsuarios(usuarios);
        return 0;
    }

    private static void logServicio() {
        logger.info("Service called");
    }

    @Override
    public List<Usuario> listarUsuarios() {
        logServicio();
        List<Usuario> usuarios = this.repositorioUsuarios.exportarUsuarios();
        for (Usuario usuario : usuarios) {
            logger.debug(usuario.toString());
        }
        return this.repositorioUsuarios.exportarUsuarios();
    }

    @Override
    public Usuario obtenerUsuario(String idUsuario) {
        logServicio();
        List<Usuario> usuarios = this.repositorioUsuarios.exportarUsuarios();
        for (Usuario usuario : usuarios) {
            if(usuario.getId().equals(idUsuario)) return usuario;
        }
        return null;
    }

    @Override
    public byte registrarApuesta(String idUsuario, Set<Integer> apuesta) {
        logServicio();

        if(apuesta.size() != 6) return 3;

        if(apuesta.stream().anyMatch(e -> e<1 || e>49)) return 1;

        List<Usuario> usuarios = this.repositorioUsuarios.exportarUsuarios();
        Usuario usuario = null;

        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getId().equals(idUsuario)){
                if (usuarios.get(i).getApuestas().contains(apuesta))
                    return 4;
                usuario = usuarios.remove(i);
                break;
            }
        }

        if(usuario == null) return 2;

        List<Set<Integer>> apuestasDeUsuario = usuario.getApuestas();

        if(apuestasDeUsuario == null) apuestasDeUsuario = new ArrayList<>();

        apuestasDeUsuario.add(apuesta);

        usuario.setApuestas(apuestasDeUsuario);

        usuarios.add(usuario);

        this.repositorioUsuarios.setUsuarios(usuarios);

        return 0;

    }
}
