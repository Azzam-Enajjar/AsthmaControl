package edu.pdx.oss.asthmacontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public String CREATE_USER_TABLE = "CREATE TABLE " + TableData.TableInfo.USER_TABLE + "(" + TableData.TableInfo.USER_NAME + " TEXT, " + TableData.TableInfo.USER_PASSWORD + " TEXT);";
    public String CREATE_ASTHMA_TIME_TABLE = "CREATE TABLE " + TableData.TableInfo.ASTHMA_TIME_TABLE + "(" + TableData.TableInfo.ASTHMA_TIME_DATE + " TEXT);";
    public String CREATE_ASTHMA_BREATH_TABLE = "CREATE TABLE " + TableData.TableInfo.ASTHMA_BREATH_TABLE + "(" + TableData.TableInfo.ASTHMA_BREATH_DATE + " TEXT);";
    public String CREATE_ASTHMA_SYMPTOMS_TABLE = "CREATE TABLE " + TableData.TableInfo.ASTHMA_SYMPTOMS_TABLE + "(" + TableData.TableInfo.ASTHMA_SYMPTOMS_DATE + " TEXT);";
    public String CREATE_ASTHMA_MEDICATION_TABLE = "CREATE TABLE " + TableData.TableInfo.ASTHMA_MEDICATION_TABLE + "(" + TableData.TableInfo.ASTHMA_MEDICATION_DATE + " TEXT);";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_USER_TABLE);
        Log.d("Database operations", "User table created..");
        sdb.execSQL(CREATE_ASTHMA_TIME_TABLE);
        Log.d("Database operations", "Asthma time table created..");
        sdb.execSQL(CREATE_ASTHMA_BREATH_TABLE);
        Log.d("Database operations", "Asthma breath table created..");
        sdb.execSQL(CREATE_ASTHMA_SYMPTOMS_TABLE);
        Log.d("Database operations", "Asthma Symptoms table created..");
        sdb.execSQL(CREATE_ASTHMA_MEDICATION_TABLE);
        Log.d("Database operations", "Asthma Medication table created..");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {

    }
}
