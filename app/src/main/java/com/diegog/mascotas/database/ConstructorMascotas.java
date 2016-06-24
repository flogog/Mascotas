package com.diegog.mascotas.database;

import android.content.ContentValues;
import android.content.Context;
import android.provider.ContactsContract;
import android.widget.Button;

import com.diegog.mascotas.R;
import com.diegog.mascotas.pojo.Mascota;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

/**
 * Created by flogog on 6/23/16.
 */
public class ConstructorMascotas {

    Context context;
    DataBase db;
    private static final int LIKE=1;
    public ConstructorMascotas(Context context) {
        this.db = new DataBase(context);
        this.context = context;
    }

    public ArrayList<Mascota> getMascotas(){
        insertMascotas(db);
        return db.queryAllMascotas();
    }

    public ArrayList<Mascota> getMascotasFav(){
        insertFavMascotas(db);
        return db.queryAllMascotasFav();
    }

    public void insertMascotas(DataBase db){
        ContentValues cv = new ContentValues();
        cv.put(DBConstants.TABLE_MASCOTA_FOTO,R.drawable.rsz_1mnky);
        cv.put(DBConstants.TABLE_MASCOTA_NOMBRE,"Chipmpy");

        db.insertInTable(cv,DBConstants.TABLE_MASCOTA);

        cv = new ContentValues();
        cv.put(DBConstants.TABLE_MASCOTA_FOTO,R.drawable.rsz_1bull);
        cv.put(DBConstants.TABLE_MASCOTA_NOMBRE,"Dogoggy");

        db.insertInTable(cv,DBConstants.TABLE_MASCOTA);

        cv = new ContentValues();
        cv.put(DBConstants.TABLE_MASCOTA_FOTO,R.drawable.rsz_1cat);
        cv.put(DBConstants.TABLE_MASCOTA_NOMBRE,"Catito");

        db.insertInTable(cv,DBConstants.TABLE_MASCOTA);

        cv = new ContentValues();
        cv.put(DBConstants.TABLE_MASCOTA_FOTO,R.drawable.rsz_1lion);
        cv.put(DBConstants.TABLE_MASCOTA_NOMBRE,"Simba");

        db.insertInTable(cv,DBConstants.TABLE_MASCOTA);

        cv = new ContentValues();
        cv.put(DBConstants.TABLE_MASCOTA_FOTO,R.drawable.rsz_3panda);
        cv.put(DBConstants.TABLE_MASCOTA_NOMBRE,"Panpandy");

        db.insertInTable(cv,DBConstants.TABLE_MASCOTA);
    }

    public void likeMascota(Mascota mascota){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA,mascota.getId());
        contentValues.put(DBConstants.TABLE_MASCOTA_LIKES_FAV,LIKE);
        db.insertInTable(contentValues,DBConstants.TABLE_MASCOTA_LIKES);
    }

    public int getLikeMascota(Mascota mascota){
        return db.countLikes(mascota);
    }

    public void insertFavMascotas(DataBase db){
        ArrayList<Mascota> favoritas = db.queryFavMascotas();
        for(Mascota favorito : favoritas){
            ContentValues contentValues = new ContentValues();

            contentValues.put(DBConstants.TABLE_MASCOTA_ID,favorito.getId());
            contentValues.put(DBConstants.TABLE_MASCOTA_FOTO,favorito.getFoto());
            contentValues.put(DBConstants.TABLE_MASCOTA_NOMBRE,favorito.getNombre());
            db.insertInTable(contentValues,DBConstants.TABLE_MASCOTA_FAVORITOS);
        }
    }

}
