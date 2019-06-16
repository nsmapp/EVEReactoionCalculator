package by.nepravsky.sm.data.database;


import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import javax.inject.Inject;

import by.nepravsky.sm.data.database.dao.FormulaDAO;
import by.nepravsky.sm.data.database.dao.MaterialDAO;
import by.nepravsky.sm.data.database.dao.StoryDAO;
import by.nepravsky.sm.data.database.entity.FormulaDBE;
import by.nepravsky.sm.data.database.entity.MaterialDBE;
import by.nepravsky.sm.data.database.entity.StoryDBE;

@Database(entities = {FormulaDBE.class, MaterialDBE.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "reactions.db";

    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context){


        if(appDatabase == null){

            copyDatabase(context);

            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME)
                    .build();
            return appDatabase;
        }

        return appDatabase;
    }

    public abstract MaterialDAO getMaterialDAO();
    public abstract FormulaDAO getFormulaDAO();


    private static Boolean checkDatabase(Context context){

        String dbPath = "data/data/"+ context.getPackageName() + "/databases/";


        SQLiteDatabase database = null;
        try {
            String path = dbPath + DATABASE_NAME;
            database  = SQLiteDatabase.openDatabase(path,null, SQLiteDatabase.OPEN_READONLY);
            if(database !=null){
                database.close();
            }
        }catch (SQLiteException e){
            Log.e("loge", "checkDatabase: " + e.toString());
        }

        return database != null? true:false;
    }

    private static void copyDatabase(Context context){

        String dbPath = "data/data/"+ context.getPackageName() + "/databases/";

        if (checkDatabase(context)){
            return;}
        else {
            try {

                new File("data/data/"+ context.getPackageName() + "/databases").mkdir();

                InputStream inputStream = context.getAssets().open(DATABASE_NAME);
                String path = dbPath + DATABASE_NAME;
                OutputStream outputStream = new FileOutputStream(path);
                byte[] buffer = new byte[8];
                int lenght;
                while ((lenght = inputStream.read(buffer)) > 0){
                    outputStream.write(buffer, 0, lenght);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();

            }catch (IOException e){
                Log.e("loge", "copyDatabase: " + e.toString());
            }
        }
    }




}
