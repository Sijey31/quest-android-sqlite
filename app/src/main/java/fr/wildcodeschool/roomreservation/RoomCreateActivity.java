package fr.wildcodeschool.roomreservation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RoomCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        Button btCreate = findViewById(R.id.button_create_room);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etName = findViewById(R.id.edit_name);

                String name = etName.getText().toString();

                addRoomToDB(name);

                Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
                RoomCreateActivity.this.startActivity(intent);
            }
        });
    }

    private void addRoomToDB(String name) {
        //Tu initialises l'accès à la base de données :
        DBHelper mDbHelper = new DBHelper(RoomCreateActivity.this);
        //Nous ouvrons un accès en écriture :
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        //Une "carte" vide de notre objet à insérer est créé :
        ContentValues room = new ContentValues();
        //Ici sont associées les valeurs aux colonnes de la table :
        room.put(DBContract.RoomEntry.COLUMN_NAME_ROOM, name);
        //Un identifiant unique est alors retourné :
        long newRoomId = db.insert(DBContract.RoomEntry.TABLE_ROOM, null, room);
        //Le Toast nous permet juste de vérifier que le processus c'est bien déroulé.
        Toast.makeText(RoomCreateActivity.this, String.valueOf(newRoomId), Toast.LENGTH_SHORT).show();

    }
}
