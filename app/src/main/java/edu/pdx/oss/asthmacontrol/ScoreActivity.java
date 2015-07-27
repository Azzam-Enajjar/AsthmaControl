package edu.pdx.oss.asthmacontrol;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ScoreActivity extends AppCompatActivity {
    EditText DAYS_ASTHMA_TIME_TEXT;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        DatabaseOperations dop = new DatabaseOperations(ctx);

        DAYS_ASTHMA_TIME_TEXT = (EditText) findViewById(R.id.daysText1);

        DAYS_ASTHMA_TIME_TEXT.setText(dop.getNumberOfDaysFromAsthmaTime(dop).toString());


    }


}
