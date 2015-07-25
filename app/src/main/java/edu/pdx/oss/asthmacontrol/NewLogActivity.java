package edu.pdx.oss.asthmacontrol;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewLogActivity extends AppCompatActivity {
    DatePicker DATE_PICKER;
    EditText DATE1_TEXT, DATE2_TEXT, DATE3_TEXT, DATE4_TEXT;
    ImageButton IMAGE1_BUTTON, IMAGE2_BUTTON, IMAGE3_BUTTON, IMAGE4_BUTTON;
    Button SAVE_BUTTON, BACK_BUTTON;
    CheckBox CHECKBOX1,CHECKBOX2,CHECKBOX3, CHECKBOX4;
    Calendar calendar = Calendar.getInstance();
    Context ctx = this;
    String logDate1, logDate2, logDate3, logDate4;
    Integer dataPickerFlag, checkBox1Flag = 0, checkBox2Flag = 0, checkBox3Flag = 0, checkBox4Flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        DATE_PICKER = (DatePicker) findViewById(R.id.datePicker);
        DATE1_TEXT = (EditText) findViewById(R.id.editText1);
        DATE2_TEXT = (EditText) findViewById(R.id.editText2);
        DATE3_TEXT = (EditText) findViewById(R.id.editText3);
        DATE4_TEXT = (EditText) findViewById(R.id.editText4);
        IMAGE1_BUTTON = (ImageButton) findViewById(R.id.imageButton1);
        IMAGE2_BUTTON = (ImageButton) findViewById(R.id.imageButton2);
        IMAGE3_BUTTON = (ImageButton) findViewById(R.id.imageButton3);
        IMAGE4_BUTTON = (ImageButton) findViewById(R.id.imageButton4);
        SAVE_BUTTON = (Button) findViewById(R.id.saveButton);
        BACK_BUTTON = (Button) findViewById(R.id.backButton);
        CHECKBOX1 = (CheckBox) findViewById(R.id.checkBox1);
        CHECKBOX2 = (CheckBox) findViewById(R.id.checkBox2);
        CHECKBOX3 = (CheckBox) findViewById(R.id.checkBox3);
        CHECKBOX4 = (CheckBox) findViewById(R.id.checkBox4);

        DATE_PICKER.setVisibility(View.INVISIBLE);

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        DATE1_TEXT.setText(sdf.format(new Date()));
        DATE2_TEXT.setText(sdf.format(new Date()));
        DATE3_TEXT.setText(sdf.format(new Date()));
        DATE4_TEXT.setText(sdf.format(new Date()));

        IMAGE1_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPickerFlag = 1;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        IMAGE2_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPickerFlag = 2;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        IMAGE3_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPickerFlag = 3;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        IMAGE4_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPickerFlag = 4;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SAVE_BUTTON.setOnClickListener(new View.OnClickListener() {
            Date dateObject;

            @Override
            public void onClick(View v) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                if (CHECKBOX1.isChecked()){
                    try {
                        String log_Date = DATE1_TEXT.getText().toString();
                        dateObject = formatter.parse(log_Date);
                        logDate1 = new SimpleDateFormat("yyyy-MM-dd").format(dateObject);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Error... Please enter a valid date", Toast.LENGTH_LONG).show();
                    }
                    checkBox1Flag = 1;
                    DatabaseOperations dop1 = new DatabaseOperations(ctx);
                    Cursor CR1 = dop1.getAllDatesFromAsthmaTimeTable(dop1);
                    if(CR1.getCount()>0){
                        CR1.moveToFirst();
                        boolean dateFound1 = false;
                        do{
                            if(logDate1.equals(CR1.getString(0))){
                                dateFound1 = true;
                            }
                        }while(CR1.moveToNext());
                        if (dateFound1) {
                            Toast.makeText(getBaseContext(), "Error... The date of when your asthma kept you from getting as much done at work, school or at home already exists", Toast.LENGTH_LONG).show();
                            DATE1_TEXT.requestFocus();
                            return;
                        }
                    }
                }
                else{
                    checkBox1Flag = 0;
                }

                if (CHECKBOX2.isChecked()){
                    try {
                        String log_Date = DATE2_TEXT.getText().toString();
                        dateObject = formatter.parse(log_Date);
                        logDate2 = new SimpleDateFormat("yyyy-MM-dd").format(dateObject);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Error... Please enter a valid date", Toast.LENGTH_LONG).show();
                    }
                    checkBox2Flag = 1;
                    DatabaseOperations dop2 = new DatabaseOperations(ctx);
                    Cursor CR2 = dop2.getAllDatesFromAsthmaBreathTable(dop2);
                    if(CR2.getCount()>0){
                        CR2.moveToFirst();
                        boolean dateFound2 = false;
                        do{
                            if(logDate2.equals(CR2.getString(0))){
                                dateFound2 = true;
                            }
                        }while(CR2.moveToNext());
                        if (dateFound2) {
                            Toast.makeText(getBaseContext(), "Error... The date of when you had shortness of breath already exists", Toast.LENGTH_LONG).show();
                            DATE2_TEXT.requestFocus();
                            return;
                        }
                    }
                }
                else{
                    checkBox2Flag = 0;
                }

                if (CHECKBOX3.isChecked()){
                    try {
                        String log_Date = DATE3_TEXT.getText().toString();
                        dateObject = formatter.parse(log_Date);
                        logDate3 = new SimpleDateFormat("yyyy-MM-dd").format(dateObject);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Error... Please enter a valid date", Toast.LENGTH_LONG).show();
                    }
                    checkBox3Flag = 1;
                    DatabaseOperations dop3 = new DatabaseOperations(ctx);
                    Cursor CR3 = dop3.getAllDatesFromAsthmaSymptomsTable(dop3);
                    if(CR3.getCount()>0){
                        CR3.moveToFirst();
                        boolean dateFound3 = false;
                        do{
                            if(logDate3.equals(CR3.getString(0))){
                                dateFound3 = true;
                            }
                        }while(CR3.moveToNext());
                        if (dateFound3) {
                            Toast.makeText(getBaseContext(), "Error... The date of when your asthma symptoms wake you up at night or earlier than usual in the morning already exists", Toast.LENGTH_LONG).show();
                            DATE3_TEXT.requestFocus();
                            return;
                        }
                    }
                }
                else{
                    checkBox3Flag = 0;
                }

                if (CHECKBOX4.isChecked()){
                    try {
                        String log_Date = DATE4_TEXT.getText().toString();
                        dateObject = formatter.parse(log_Date);
                        logDate4 = new SimpleDateFormat("yyyy-MM-dd").format(dateObject);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Error... Please enter a valid date", Toast.LENGTH_LONG).show();
                    }
                    checkBox4Flag = 1;
                    DatabaseOperations dop4 = new DatabaseOperations(ctx);
                    Cursor CR4 = dop4.getAllDatesFromAsthmaMedicationTable(dop4);
                    if(CR4.getCount()>0){
                        CR4.moveToFirst();
                        boolean dateFound4 = false;
                        do{
                            if(logDate4.equals(CR4.getString(0))){
                                dateFound4 = true;
                            }
                        }while(CR4.moveToNext());
                        if (dateFound4) {
                            Toast.makeText(getBaseContext(), "Error... The date of when you used your rescue inhaler or nebulizer medication already exists", Toast.LENGTH_LONG).show();
                            DATE4_TEXT.requestFocus();
                            return;
                        }
                    }
                }
                else{
                    checkBox4Flag = 0;
                }

                DatabaseOperations dop = new DatabaseOperations(ctx);
                dop.insertDateForAll(dop, logDate1, logDate2, logDate3, logDate4, checkBox1Flag, checkBox2Flag, checkBox3Flag, checkBox4Flag);
                if ((CHECKBOX1.isChecked()) || (CHECKBOX2.isChecked()) || (CHECKBOX3.isChecked()) || (CHECKBOX4.isChecked()))
                    Toast.makeText(getBaseContext(), "Date(s) added successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(), "Error... Check (Yes) before saving", Toast.LENGTH_LONG).show();
            }
        });

        BACK_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    } // End of create activity procedure

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            switch(dataPickerFlag) {
                case 1:
                    DATE1_TEXT.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    break;
                case 2:
                    DATE2_TEXT.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    break;
                case 3:
                    DATE3_TEXT.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    break;
                case 4:
                    DATE4_TEXT.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    break;
            }
        }
    };
}
