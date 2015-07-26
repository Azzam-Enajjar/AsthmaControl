package edu.pdx.oss.asthmacontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UpdateLogMenu extends AppCompatActivity {
    Button UPDATEASTHMATIME_BUTTON, UPDATEASTHMABREATH_BUTTON, BACK_BUTTON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_log_menu);

        UPDATEASTHMATIME_BUTTON = (Button) findViewById(R.id.updateAsthmaTime);
        UPDATEASTHMABREATH_BUTTON = (Button) findViewById(R.id.updateAsthmaBreath);
        BACK_BUTTON = (Button) findViewById(R.id.backButton);

        UPDATEASTHMATIME_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateLogMenu.this, UpdateAsthmaTimeActivity.class);
                startActivity(intent);

            }
        });

        UPDATEASTHMABREATH_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateLogMenu.this, UpdateAsthmaBreathActivity.class);
                startActivity(intent);
            }
        });

        BACK_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
