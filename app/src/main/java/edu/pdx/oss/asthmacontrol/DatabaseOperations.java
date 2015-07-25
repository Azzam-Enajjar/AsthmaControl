package edu.pdx.oss.asthmacontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public void insertDateForAll(DatabaseOperations dop, String logDate1, String logDate2, String logDate3, String logDate4, Integer checkBox1Flag, Integer checkBox2Flag, Integer checkBox3Flag, Integer checkBox4Flag ){
        SQLiteDatabase SQ = dop.getWritableDatabase();

        if (checkBox1Flag == 1){
            ContentValues  CV1 = new ContentValues();
            CV1.put(TableData.TableInfo.ASTHMA_TIME_DATE, logDate1);
            long k1 = SQ.insert(TableData.TableInfo.ASTHMA_TIME_TABLE, null, CV1);
            Log.d("Database operations", "One row inserted to Asthma Time Table");
        }

        if (checkBox2Flag == 1){
            ContentValues  CV2 = new ContentValues();
            CV2.put(TableData.TableInfo.ASTHMA_BREATH_DATE, logDate2);
            long k2 = SQ.insert(TableData.TableInfo.ASTHMA_BREATH_TABLE, null, CV2);
            Log.d("Database operations", "One row inserted to Asthma Breath Table");
        }

        if (checkBox3Flag == 1){
            ContentValues  CV3 = new ContentValues();
            CV3.put(TableData.TableInfo.ASTHMA_SYMPTOMS_DATE, logDate3);
            long k3 = SQ.insert(TableData.TableInfo.ASTHMA_SYMPTOMS_TABLE, null, CV3);
            Log.d("Database operations", "One row inserted to Asthma Symptoms Table");
        }

        if (checkBox4Flag == 1){
            ContentValues  CV4 = new ContentValues();
            CV4.put(TableData.TableInfo.ASTHMA_MEDICATION_DATE, logDate4);
            long k4 = SQ.insert(TableData.TableInfo.ASTHMA_MEDICATION_TABLE, null, CV4);
            Log.d("Database operations", "One row inserted to Asthma Medication Table");
        }
    }

    public Cursor getAllDatesFromAsthmaTimeTable(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.ASTHMA_TIME_DATE};
        Cursor CR = SQ.query(TableData.TableInfo.ASTHMA_TIME_TABLE, columns, null, null, null, null, null);
        return CR;
    }

    public Cursor getAllDatesFromAsthmaBreathTable(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.ASTHMA_BREATH_DATE};
        Cursor CR = SQ.query(TableData.TableInfo.ASTHMA_BREATH_TABLE, columns, null, null, null, null, null);
        return CR;
    }

    public Cursor getAllDatesFromAsthmaSymptomsTable(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.ASTHMA_SYMPTOMS_DATE};
        Cursor CR = SQ.query(TableData.TableInfo.ASTHMA_SYMPTOMS_TABLE, columns, null, null, null, null, null);
        return CR;
    }

    public Cursor getAllDatesFromAsthmaMedicationTable(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.ASTHMA_MEDICATION_DATE};
        Cursor CR = SQ.query(TableData.TableInfo.ASTHMA_MEDICATION_TABLE, columns, null, null, null, null, null);
        return CR;
    }

    public void insertDateForAsthmaTime(DatabaseOperations dop, String logDate){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues  CV = new ContentValues();
        CV.put(TableData.TableInfo.ASTHMA_TIME_DATE, logDate);
        long k1 = SQ.insert(TableData.TableInfo.ASTHMA_TIME_TABLE, null, CV);
        Log.d("Database operations", "One row inserted to Asthma Time Table");
        }

    public void insertDateForAsthmaBreath(DatabaseOperations dop, String logDate){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues  CV = new ContentValues();
        CV.put(TableData.TableInfo.ASTHMA_BREATH_DATE, logDate);
        long k1 = SQ.insert(TableData.TableInfo.ASTHMA_BREATH_TABLE, null, CV);
        Log.d("Database operations", "One row inserted to Asthma Breath Table");
    }

    public void deleteDateFromAsthmaTime(DatabaseOperations dop, String logDate){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String selection = TableData.TableInfo.ASTHMA_TIME_DATE + " = ?";
        String args[] = {logDate};
        SQ.delete(TableData.TableInfo.ASTHMA_TIME_TABLE, selection, args);
    }

    public void deleteDateFromAsthmaBreath(DatabaseOperations dop, String logDate){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String selection = TableData.TableInfo.ASTHMA_BREATH_DATE + " = ?";
        String args[] = {logDate};
        SQ.delete(TableData.TableInfo.ASTHMA_BREATH_TABLE, selection, args);
    }

    public Cursor getPastFourWeeksFromAsthmaTime(DatabaseOperations dop){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -28);
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        String startDate = sdf.format(cal.getTime());
        String endDate = sdf.format(new Date());

        SQLiteDatabase SQ = dop.getReadableDatabase();
        String columns[] = {TableData.TableInfo.ASTHMA_TIME_DATE};
        String selection = TableData.TableInfo.ASTHMA_TIME_DATE + " BETWEEN ? AND ?";
        String args[] = {startDate, endDate};
        Cursor CR = SQ.query(TableData.TableInfo.ASTHMA_TIME_TABLE, columns, selection, args, null, null, null);
        return CR;
    }

    public Cursor getPastFourWeeksFromAsthmaBreath(DatabaseOperations dop){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -28);
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        String startDate = sdf.format(cal.getTime());
        String endDate = sdf.format(new Date());

        SQLiteDatabase SQ = dop.getReadableDatabase();
        String columns[] = {TableData.TableInfo.ASTHMA_BREATH_DATE};
        String selection = TableData.TableInfo.ASTHMA_BREATH_DATE + " BETWEEN ? AND ?";
        String args[] = {startDate, endDate};
        Cursor CR = SQ.query(TableData.TableInfo.ASTHMA_BREATH_TABLE, columns, selection, args, null, null, null);
        return CR;
    }

    public void deleteAllFromAsthmaTime(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        SQ.delete(TableData.TableInfo.ASTHMA_TIME_TABLE, null, null);
    }

}
