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
    Button ADD_DATE_BUTTON, DELETE_BUTTON, BACK_BUTTON;
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
        GRIDVIEW = (GridView) findViewById(R.id.gridView);

        DATE_PICKER.setVisibility(View.INVISIBLE);

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        DATE_TEXT.setText(sdf.format(new Date()));

        li = new ArrayList<String>();

        dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, li);
        dataAdapter.setDropDownViewResource(R.layout.activity_update_asthma_time);

        displayDataOnGridView();
    }

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
