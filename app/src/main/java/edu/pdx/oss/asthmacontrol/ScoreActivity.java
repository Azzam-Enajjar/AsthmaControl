package edu.pdx.oss.asthmacontrol;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ScoreActivity extends AppCompatActivity {
    EditText DAYS_ASTHMA_TIME_TEXT, SCORE_ASTHMA_TIME_TEXT, DAYS_ASTHMA_BREATH_TEXT, SCORE_ASTHMA_BREATH_TEXT, DAYS_ASTHMA_SYMPTOMS_TEXT, SCORE_ASTHMA_SYMPTOMS_TEXT,  DAYS_ASTHMA_MEDICATION_TEXT, SCORE_ASTHMA_MEDICATION_TEXT;
    Context ctx = this;
    Integer numberOfDays;
    Integer score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        DatabaseOperations dop = new DatabaseOperations(ctx);

        DAYS_ASTHMA_TIME_TEXT = (EditText) findViewById(R.id.daysText1);
        SCORE_ASTHMA_TIME_TEXT = (EditText) findViewById(R.id.scoreText1);
        DAYS_ASTHMA_BREATH_TEXT = (EditText) findViewById(R.id.daysText2);
        SCORE_ASTHMA_BREATH_TEXT = (EditText) findViewById(R.id.scoreText2);
        DAYS_ASTHMA_SYMPTOMS_TEXT = (EditText) findViewById(R.id.daysText3);
        SCORE_ASTHMA_SYMPTOMS_TEXT = (EditText) findViewById(R.id.scoreText3);
        DAYS_ASTHMA_MEDICATION_TEXT = (EditText) findViewById(R.id.daysText4);
        SCORE_ASTHMA_MEDICATION_TEXT = (EditText) findViewById(R.id.scoreText4);

        numberOfDays = dop.getNumberOfDaysFromAsthmaTime(dop);
        DAYS_ASTHMA_TIME_TEXT.setText(numberOfDays.toString());
        score = getScoreFromAsthmaTime();
        SCORE_ASTHMA_TIME_TEXT.setText(score.toString());

        numberOfDays = dop.getNumberOfDaysFromAsthmaBreath(dop);
        DAYS_ASTHMA_BREATH_TEXT.setText(numberOfDays.toString());
        score = getScoreFromAsthmaBreath();
        SCORE_ASTHMA_BREATH_TEXT.setText(score.toString());

        numberOfDays = dop.getNumberOfDaysFromAsthmaSymptoms(dop);
        DAYS_ASTHMA_SYMPTOMS_TEXT.setText(numberOfDays.toString());
        score = getScoreFromAsthmaSymptoms();
        SCORE_ASTHMA_SYMPTOMS_TEXT.setText(score.toString());

        numberOfDays = dop.getNumberOfDaysFromAsthmaMedication(dop);
        DAYS_ASTHMA_MEDICATION_TEXT.setText(numberOfDays.toString());

    }

    public Integer getScoreFromAsthmaTime(){
       if (numberOfDays == 28)
           score = 1;
       else if ((numberOfDays >=22) && (numberOfDays<=27))
           score = 2;
       else if ((numberOfDays >=8) && (numberOfDays<=21))
           score = 3;
       else if ((numberOfDays >=1) && (numberOfDays<=20))
           score = 4;
       else
           score = 5;

       return score;
    }

    public Integer getScoreFromAsthmaBreath(){
        if (numberOfDays == 28)
            score = 1;
        else if ((numberOfDays >=9) && (numberOfDays<=27))
            score = 3;
        else if ((numberOfDays >=1) && (numberOfDays<=8))
            score = 4;
        else
            score = 5;

        return score;
    }

    public Integer getScoreFromAsthmaSymptoms (){
        if ((numberOfDays >=16) && (numberOfDays<=28))
            score = 1;
        else if ((numberOfDays >=7) && (numberOfDays<=15))
            score = 2;
        else if ((numberOfDays >=3) && (numberOfDays<=6))
            score = 3;
        else if ((numberOfDays >=1) && (numberOfDays<=2))
            score = 4;
        else
            score = 5;

        return score;
    }



}
