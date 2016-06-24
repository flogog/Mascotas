package com.diegog.mascotas.database;

import android.content.ContentValues;
import android.content.Context;
import android.provider.ContactsContract;
import android.widget.Button;

import com.diegog.mascotas.R;
import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

/**
 * Created by flogog on 6/23/16.
 */
public class ConstructorMascotas {

    Context context;
    private static final int LIKE=0;
    public ConstructorMascotas(Context context) {
       this.context = context;
    }

    public ArrayList<Mascota> getMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        DataBase db = new DataBase(context);
        insertMascotas(db);
        return db.queryAllMascotas();
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
        DataBase db = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA,mascota.getId());
        contentValues.put(DBConstants.TABLE_MASCOTA_LIKES_FAV,LIKE);
        db.insertInTable(contentValues,DBConstants.TABLE_MASCOTA_LIKES);
    }

    public int getLikeMascota(Mascota mascota){
        DataBase db =  new DataBase(context);
        return db.countLikes(mascota);
    }
}
