package com.diegog.mascotas.pojo;

import java.io.Serializable;

/**
 * Created by flogog on 6/12/16.
 */
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    private String      id;
    private int         foto;
    private String      nombre;
    private String      url;
    private int         fav;
    private String      idLike;
    private String      username;

    public Mascota(){

    }

    public Mascota(String id, String nombre, String url, int fav, String idLike) {
        this.id   = id;
        this.nombre = nombre;
        this.url    = url;
        this.fav    = fav;
        this.idLike = idLike;
    }

    public Mascota(int foto, String nombre, int fav) {
        this.foto   = foto;
        this.nombre = nombre;
        this.fav    = fav;
    }

    public Mascota(int foto, String nombre, String url) {
        this.foto       = foto;
        this.nombre     = nombre;
        this.url        = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFav() { return fav;   }

    public void setFav(int fav) {  this.fav = fav;    }

    public String getIdLike() {
        return idLike;
    }

    public void setIdLike(String idLike) {
        this.idLike = idLike;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
