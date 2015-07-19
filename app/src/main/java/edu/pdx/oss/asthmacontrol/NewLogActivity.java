package edu.pdx.oss.asthmacontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewLogActivity extends AppCompatActivity {
    DatePicker DATE_PICKER;
    EditText DATE1_TEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        DATE_PICKER = (DatePicker) findViewById(R.id.datePicker);
        DATE1_TEXT = (EditText) findViewById(R.id.editText1);

        DATE_PICKER.setVisibility(View.INVISIBLE);

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        DATE1_TEXT.setText(sdf.format(new Date()));
    }

  }
