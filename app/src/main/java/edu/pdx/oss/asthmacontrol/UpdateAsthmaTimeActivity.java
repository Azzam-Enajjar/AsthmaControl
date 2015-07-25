package edu.pdx.oss.asthmacontrol;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UpdateAsthmaTimeActivity extends AppCompatActivity {
    EditText DATE_TEXT;
    ImageButton IMAGE_BUTTON;
    DatePicker DATE_PICKER;
    Button ADD_DATE_BUTTON, DELETE_BUTTON, BACK_BUTTON, DELETE_ALL_BUTTON, SHOW_ALL_BUTTON, SHOW_FOUR_WEEKS_BUTTON;
    GridView GRIDVIEW;
    Calendar calendar = Calendar.getInstance();
    Context ctx = this;
    List<String> li;
    ArrayAdapter<String> dataAdapter;
    Integer pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_asthma_time);

        DATE_TEXT = (EditText) findViewById(R.id.dateText);
        IMAGE_BUTTON = (ImageButton) findViewById(R.id.imageButton);
        DATE_PICKER = (DatePicker) findViewById(R.id.datePicker);
        ADD_DATE_BUTTON = (Button) findViewById(R.id.addDateButton);
        DELETE_BUTTON = (Button) findViewById(R.id.deleteDateButton);
        SHOW_ALL_BUTTON =(Button) findViewById(R.id.showAllButton);
        SHOW_FOUR_WEEKS_BUTTON = (Button) findViewById(R.id.showFourWeeksButton);
        BACK_BUTTON = (Button) findViewById(R.id.backButton);
        GRIDVIEW = (GridView) findViewById(R.id.gridView);
        DELETE_ALL_BUTTON = (Button) findViewById(R.id.deleteAllButton);

        DATE_PICKER.setVisibility(View.INVISIBLE);

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        DATE_TEXT.setText(sdf.format(new Date()));

        li = new ArrayList<String>();

        dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, li);
        dataAdapter.setDropDownViewResource(R.layout.activity_update_asthma_time);

        displayDataOnGridView();

        IMAGE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateAsthmaTimeActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ADD_DATE_BUTTON.setOnClickListener(new View.OnClickListener() {
            String logDate;
            Date dateObject;

            @Override
            public void onClick(View v) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    String log_Date = DATE_TEXT.getText().toString();
                    dateObject = formatter.parse(log_Date);
                    logDate = new SimpleDateFormat("yyyy-MM-dd").format(dateObject);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "Error... Please enter a valid date", Toast.LENGTH_LONG).show();
                }

                DatabaseOperations dop1 = new DatabaseOperations(ctx);
                Cursor CR = dop1.getAllDatesFromAsthmaTimeTable(dop1);
                if(CR.getCount()>0){
                    CR.moveToFirst();
                    boolean dateFound = false;
                    do{
                        if(logDate.equals(CR.getString(0))){
                            dateFound = true;
                        }
                    }while(CR.moveToNext());
                    if (dateFound) {
                        Toast.makeText(getBaseContext(), "Error... The date already exists", Toast.LENGTH_LONG).show();
                        DATE_TEXT.requestFocus();
                        return;
                    }
                }
                DatabaseOperations dop2 = new DatabaseOperations(ctx);
                dop2.insertDateForAsthmaTime(dop2, logDate);
                li.add(logDate);
                GRIDVIEW.setAdapter(dataAdapter);
                Toast.makeText(getBaseContext(), "The date added successfully", Toast.LENGTH_LONG).show();
            }
        });

        GRIDVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
        });

        DELETE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos != null) {
                    DatabaseOperations dop = new DatabaseOperations(ctx);
                    String logDate = li.get(pos);
                    dop.deleteDateFromAsthmaTime(dop, logDate);
                    li.remove(pos);
                    displayDataOnGridView();
                    Toast.makeText(getBaseContext(), "Selected date has been removed successfully..", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Error... Select a date first", Toast.LENGTH_LONG).show();
                }
            }
        });

        SHOW_ALL_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDataOnGridView();
            }
        });

        SHOW_FOUR_WEEKS_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperations dop = new DatabaseOperations(ctx);
                try {
                    Cursor CR = dop.getPastFourWeeksFromAsthmaTime(dop);
                    if(CR.getCount()>0){
                        li.clear();
                        if (CR.moveToFirst()){
                            do {
                                String logDate = CR.getString(CR.getColumnIndex(TableData.TableInfo.ASTHMA_TIME_DATE));
                                li.add(logDate);
                                GRIDVIEW.setAdapter(dataAdapter);
                            } while(CR.moveToNext());
                        }
                        else{
                            Toast.makeText(getBaseContext(), "There is no data...", Toast.LENGTH_LONG).show();
                        }
                    }
                    CR.close();
                }catch (Exception e){
                    Toast.makeText(getBaseContext(),"Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        BACK_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DELETE_ALL_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperations dop = new DatabaseOperations(ctx);
                dop.deleteAllFromAsthmaTime(dop);
            }
        });
    } // End of create activity procedure

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            DATE_TEXT.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    public void displayDataOnGridView(){
        try {
            SQLiteDatabase SQ = openOrCreateDatabase(TableData.TableInfo.DATABASE_NAME, Context.MODE_PRIVATE, null);
            Cursor CR = SQ.rawQuery("SELECT * FROM " + TableData.TableInfo.ASTHMA_TIME_TABLE, null);
            if(CR.getCount()>0){
                li.clear();
                if (CR.moveToFirst()){
                    do {
                        String logDate = CR.getString(CR.getColumnIndex(TableData.TableInfo.ASTHMA_TIME_DATE));
                        li.add(logDate);
                        GRIDVIEW.setAdapter(dataAdapter);
                    } while(CR.moveToNext());
                }
                else{
                    Toast.makeText(getBaseContext(), "There is no data...", Toast.LENGTH_LONG).show();
                }
            }
            CR.close();
            SQ.close();
        }catch (Exception e){
            Toast.makeText(getBaseContext(),"Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
