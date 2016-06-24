package com.diegog.mascotas.pojo;

import java.io.Serializable;

/**
 * Created by flogog on 6/12/16.
 */
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int foto;
    private String nombre;
    private boolean favorito;
    private int fav;

    public Mascota() {

    }

    public Mascota(int foto, String nombre, int fav) {
        this.foto   = foto;
        this.nombre = nombre;
        this.fav    = fav;
    }

    public Mascota(int foto, String nombre, boolean favorito) {
        this.foto       = foto;
        this.nombre     = nombre;
        this.favorito   = favorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public int getFav() { return fav;   }

    public void setFav(int fav) {  this.fav = fav;    }
}
