package fr.wildcodeschool.roomreservation;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wilder on 09/04/18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String SQL_CREATE_PERSON_ENTRIES =
            "CREATE TABLE " + DBContract.PersonEntry.TABLE_NAME + " (" +
                    DBContract.PersonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.PersonEntry.COLUMN_NAME_LASTNAME + " TEXT," +
                    DBContract.PersonEntry.COLUMN_NAME_FIRSTNAME + " TEXT);";
    public static final String SQL_CREATE_ROOM_ENTRIES =
            "CREATE TABLE " + DBContract.RoomEntry.TABLE_ROOM + " (" +
                    DBContract.RoomEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.RoomEntry.COLUMN_NAME_ROOM + " TEXT);";

    public static final String SQL_DELETE_PERSON_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.PersonEntry.TABLE_NAME;

    public static final String SQL_DELETE_ROOM_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.RoomEntry.TABLE_ROOM;

    //Ici tu vas définir la version de ta base de données et le nom de cette dernière.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "database.db";


    //Voici le contructeur, qui initialise le lien avec la base de données, en spécifiant son nom et sa version.
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Ici tu as défini la méthode appelée à la création de la base de données.
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PERSON_ENTRIES);
        db.execSQL(SQL_CREATE_ROOM_ENTRIES);
    }

    //Ici tu as défini la méthode appelée à la mise à jour de la base de données.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PERSON_ENTRIES);
        db.execSQL(SQL_DELETE_ROOM_ENTRIES);
        onCreate(db);
    }
    //La méthode onDowngrade() est appelée en cas de mise à jour "inversée" de la base, si on veut revenir sur une version antérieure.
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



}
