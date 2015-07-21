package edu.pdx.oss.asthmacontrol;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewLogActivity extends AppCompatActivity {
    DatePicker DATE_PICKER;
    EditText DATE1_TEXT, DATE2_TEXT, DATE3_TEXT, DATE4_TEXT;
    ImageButton IMAGE1_BUTTON, IMAGE2_BUTTON, IMAGE3_BUTTON, IMAGE4_BUTTON;
    Button SAVE_BUTTON, BACK_BUTTON;
    Calendar calendar = Calendar.getInstance();
    Integer flag;

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

        DATE_PICKER.setVisibility(View.INVISIBLE);

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        DATE1_TEXT.setText(sdf.format(new Date()));

        IMAGE1_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        IMAGE2_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        IMAGE3_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 3;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        IMAGE4_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 4;
                new DatePickerDialog(NewLogActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    } // End of create activity procedure

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            switch(flag) {
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
