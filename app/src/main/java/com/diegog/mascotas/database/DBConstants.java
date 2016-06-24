package com.diegog.mascotas.database;

/**
 * Created by flogog on 6/23/16.
 */
public final class DBConstants {

    public static final String DATABASE_NAME        = "mascotas";
    public static final int    DATABASE_VERSION     =   1;

    public static final String TABLE_MASCOTA        = "mascota";
    public static final String TABLE_MASCOTA_ID     = "id";
    public static final String TABLE_MASCOTA_FOTO   = "foto";
    public static final String TABLE_MASCOTA_NOMBRE = "nombre";

    public static final String TABLE_MASCOTA_LIKES              = "mascota_likes";
    public static final String TABLE_MASCOTA_LIKES_ID           = "id";
    public static final String TABLE_MASCOTA_LIKES_ID_MASCOTA   = "id_mascota";
    public static final String TABLE_MASCOTA_LIKES_FAV          = "fav";

}
