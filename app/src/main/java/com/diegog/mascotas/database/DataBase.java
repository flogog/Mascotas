package com.diegog.mascotas.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by flogog on 6/23/16.
 */
public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
        this.context    =   context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTableMascotas = "CREATE TABLE " +
                DBConstants.TABLE_MASCOTA+" ("
                +DBConstants.TABLE_MASCOTA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +DBConstants.TABLE_MASCOTA_FOTO+" INTEGER ,"
                +DBConstants.TABLE_MASCOTA_NOMBRE+" TEXT "+
                ")";

        String queryCreateTableMascotasFav = "CREATE TABLE " +
                DBConstants.TABLE_MASCOTA_FAVORITOS+" ("
                +DBConstants.TABLE_MASCOTA_ID +" INTEGER PRIMARY KEY ,"
                +DBConstants.TABLE_MASCOTA_FOTO+" INTEGER ,"
                +DBConstants.TABLE_MASCOTA_NOMBRE+" TEXT "+
                ")";

        String queryCreateTableLikeMascota = "CREATE TABLE " +
                DBConstants.TABLE_MASCOTA_LIKES+" ("
                +DBConstants.TABLE_MASCOTA_LIKES_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA+" INTEGER ,"
                +DBConstants.TABLE_MASCOTA_LIKES_FAV+" INTEGER ,"
                +"FOREIGN KEY ("+DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA+") REFERENCES "+DBConstants.TABLE_MASCOTA+"("+DBConstants.TABLE_MASCOTA_ID+")"
                +")";



        db.execSQL(queryCreateTableMascotas);
        db.execSQL(queryCreateTableMascotasFav);
        db.execSQL(queryCreateTableLikeMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+DBConstants.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST "+DBConstants.TABLE_MASCOTA_LIKES);
        db.execSQL("DROP TABLE IF EXIST "+DBConstants.TABLE_MASCOTA_FAVORITOS);
        onCreate(db);

    }

    public ArrayList<Mascota> queryAllMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String queryAllMascotas = "SELECT * FROM "+DBConstants.TABLE_MASCOTA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rows = db.rawQuery(queryAllMascotas,null);

        while(rows.moveToNext()){
            Mascota nuevaMascota = new Mascota();
            nuevaMascota.setId(rows.getInt(0));
            nuevaMascota.setFoto(rows.getInt(1));
            nuevaMascota.setNombre(rows.getString(2));

            String queryLikes = "SELECT COUNT("+DBConstants.TABLE_MASCOTA_LIKES_ID+") FROM "+DBConstants.TABLE_MASCOTA_LIKES+" WHERE "+DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA+"="+nuevaMascota.getId();
            Cursor row = db.rawQuery(queryLikes,null);
            if(row.moveToNext()){
                nuevaMascota.setFav(row.getInt(0));
            }else{
                nuevaMascota.setFav(0);
            }
            mascotas.add(nuevaMascota);
        }
        db.close();

        return mascotas;
    }

    public void insertInTable(ContentValues contentValues, String table){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(table,null,contentValues);
        db.close();
    }

    public int countLikes(Mascota mascota){
        int likes = 0;
        String query = "SELECT COUNT(*) FROM "+DBConstants.TABLE_MASCOTA_LIKES+" WHERE "+DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA+" = "+mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rows = db.rawQuery(query,null);
        if(rows.moveToNext()){
            likes = rows.getInt(0);
        }
        db.close();
        return likes;
    }

    public ArrayList<Mascota> queryAllMascotasFav(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String queryAllMascotas = "SELECT * FROM "+DBConstants.TABLE_MASCOTA_FAVORITOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rows = db.rawQuery(queryAllMascotas,null);

        while(rows.moveToNext()){
            Mascota nuevaMascota = new Mascota();
            nuevaMascota.setId(rows.getInt(0));
            nuevaMascota.setFoto(rows.getInt(1));
            nuevaMascota.setNombre(rows.getString(2));

            String queryLikes = "SELECT COUNT("+DBConstants.TABLE_MASCOTA_LIKES_ID+") FROM "+DBConstants.TABLE_MASCOTA_LIKES+" WHERE "+DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA+"="+nuevaMascota.getId();
            Cursor row = db.rawQuery(queryLikes,null);
            if(row.moveToNext()){
                nuevaMascota.setFav(row.getInt(0));
            }else{
                nuevaMascota.setFav(0);
            }
            mascotas.add(nuevaMascota);
        }
        db.close();

        return mascotas;
    }

    public ArrayList<Mascota> queryFavMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        String queryAllMascotas = "" +
                "SELECT masc.* FROM "+DBConstants.TABLE_MASCOTA+" masc INNER JOIN\n" +
                "(SELECT SUM(fav) as suma,id_mascota FROM "+DBConstants.TABLE_MASCOTA_LIKES+
                " GROUP BY "+DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA+
                " ORDER BY suma DESC LIMIT 5) as top5 ON top5."+DBConstants.TABLE_MASCOTA_LIKES_ID_MASCOTA+" = masc."+DBConstants.TABLE_MASCOTA_ID+"";
        System.out.print(queryAllMascotas);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DBConstants.TABLE_MASCOTA_FAVORITOS);
        Cursor rows = db.rawQuery(queryAllMascotas,null);

        while(rows.moveToNext()){
            Mascota nuevaMascota = new Mascota();
            nuevaMascota.setId(rows.getInt(0));
            nuevaMascota.setFoto(rows.getInt(1));
            nuevaMascota.setNombre(rows.getString(2));

            mascotas.add(nuevaMascota);
        }
        db.close();

        return mascotas;
    }

}
