package edu.pdx.oss.asthmacontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button NEW_LOG_BUTTON, UPDATE_LOG_BUTTON, REPORT_SCORE_BUTTON, EXIT_BUTTON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NEW_LOG_BUTTON = (Button) findViewById(R.id.newLogButton);
        EXIT_BUTTON = (Button) findViewById(R.id.exitButton);

        NEW_LOG_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewLogActivity.class);
                startActivity(intent);
            }
        });

        EXIT_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
