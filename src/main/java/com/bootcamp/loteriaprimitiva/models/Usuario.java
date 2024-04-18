package com.bootcamp.loteriaprimitiva.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class Usuario {
    private String id;
    private String nombre;
    private List<Set<Integer>> apuestas;
}
