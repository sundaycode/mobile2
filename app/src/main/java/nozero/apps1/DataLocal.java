package nozero.apps1;

import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AutoCompleteTextView;

/**
 * Created by F on 5/10/2015.
 */
public class DataLocal extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user";
    public DataLocal(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    public void createTablePengguna(SQLiteDatabase db){
        db.execSQL("create table PENGGUNA (ID INTEGER PRIMARY KEY ,USER  VARCHAR , PASS VARCHAR);");
        insertUser(db, "name", "pass");
    }

    public void dropTablePengguna(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS PENGGUNA");

    }

    public void insertUser(SQLiteDatabase db, String name, String pass){
        db.execSQL("INSERT INTO PENGGUNA values ('1' , '"+name+"', '"+pass+"')");
    }
    public void updateUser(SQLiteDatabase db, String name, String pass){
        db.execSQL("UPDATE PENGGUNA SET USER ='"+name+"', PASS ='"+pass+"' where ID = '1' ");
    }
    public boolean cekUser(SQLiteDatabase db, Cursor kamusCursor){
        boolean hasil=false;
        kamusCursor = db.rawQuery("SELECT USER, PASS FROM PENGGUNA WHERE ID = '1' ", null);
        while (kamusCursor.moveToNext()) {
            String user = kamusCursor.getString(0);
            String pass = kamusCursor.getString(1);
            if (kamusCursor.getString(0).equals("name") && kamusCursor.getString(1).equals("pass")){
                hasil=  true;
            }

        }
        return hasil;
    }

    public String getUser(SQLiteDatabase db, Cursor kamusCursor){
        String hasil=null;
        kamusCursor = db.rawQuery("SELECT USER FROM PENGGUNA WHERE ID = '1' ", null);
        while (kamusCursor.moveToNext()) {
            hasil =kamusCursor.getString(0);
        }
        return hasil;
    }
    public String getPass(SQLiteDatabase db, Cursor kamusCursor){
        String hasil=null;
        kamusCursor = db.rawQuery("SELECT PASS FROM PENGGUNA WHERE ID = '1' ", null);
        while (kamusCursor.moveToNext()) {
            hasil =kamusCursor.getString(0);
        }
        return hasil;
    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO Auto-generated method stub

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        // TODO Auto-generated method stub

    }

}
