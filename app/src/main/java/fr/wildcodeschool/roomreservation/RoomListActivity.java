package fr.wildcodeschool.roomreservation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        ArrayList<RoomModel> roomModels = loadRoomsFromDB();

        RoomAdapter adapter = new RoomAdapter(this, 0, roomModels);
        ListView lvListRoom = findViewById(R.id.list_room);
        lvListRoom.setAdapter(adapter);
    }

    private ArrayList<RoomModel> loadRoomsFromDB() {
        ArrayList<RoomModel> roomModels = new ArrayList<>();

        // TODO : load rooms from database
        //load persons from database
        //Tu initialises l'accès à la base de données :
        DBHelper mDbHelper = new DBHelper(RoomListActivity.this);
        //tu ouvres un accès en lecture :
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //Tu délimites dans une "projection" les colonnes de la table que tu vas récupérer :
        String[] projection = {
                DBContract.RoomEntry._ID,
                DBContract.RoomEntry.COLUMN_NAME_ROOM,

        };
        //Les résultats sont stockés dans un Cursor :
        Cursor cursor = db.query(
                DBContract.RoomEntry.TABLE_ROOM,
                projection,
                null, null, null, null, null
        );
        //Ici tu parcours chaque ligne de la table stockée dans le Cursor,
        // et pour chacunes d'entres-elles tu récupères les valeurs des colonnes dont tu as besoin.
        // Enfin, tu créés une instance de la classe PersonModel, pour ensuite l'ajouter à la liste qui sera plus tard envoyée à l'Adapter :
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.RoomEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.RoomEntry.COLUMN_NAME_ROOM));


            RoomModel roomModel = new RoomModel(id, name);
            roomModels.add(roomModel);
        }
        //une fois le Cursor parcouru, on le ferme :
        cursor.close();

        return roomModels;
    }
}
