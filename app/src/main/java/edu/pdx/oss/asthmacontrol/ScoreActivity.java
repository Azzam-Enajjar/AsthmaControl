/*
Copyright (c) 2015 Azzam Enajjar
This project is licensed under the terms of the MIT license. Please see LICENSE.md for full license terms.
*/

package edu.pdx.oss.asthmacontrol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {
    EditText DAYS_ASTHMA_TIME_TEXT, SCORE_ASTHMA_TIME_TEXT, DAYS_ASTHMA_BREATH_TEXT, SCORE_ASTHMA_BREATH_TEXT, DAYS_ASTHMA_SYMPTOMS_TEXT, SCORE_ASTHMA_SYMPTOMS_TEXT,  DAYS_ASTHMA_MEDICATION_TEXT, SCORE_ASTHMA_MEDICATION_TEXT, RATE_ASTHMA_TEXT, TOTAL_SCORE_TEXT;
    TextView UNDER_CONTROL_TEXT, NOT_UNDER_CONTROL_TEXT;
    ImageView HAPPY_IMAGEVIEW, SAD_IMAGEVIEW;
    Button TOTAL_SCORE_BUTTON;
    Context ctx = this;
    Integer numberOfDays;
    Integer score1, score2, score3, score4;

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
        RATE_ASTHMA_TEXT = (EditText) findViewById(R.id.scoreText5);
        TOTAL_SCORE_TEXT = (EditText) findViewById(R.id.totalScoreText);
        UNDER_CONTROL_TEXT = (TextView) findViewById(R.id.underControlText);
        NOT_UNDER_CONTROL_TEXT = (TextView) findViewById(R.id.notUnderControlText);
        HAPPY_IMAGEVIEW = (ImageView) findViewById(R.id.happyImageView);
        SAD_IMAGEVIEW = (ImageView) findViewById(R.id.sadImageView);
        TOTAL_SCORE_BUTTON = (Button) findViewById(R.id.totalScoreButton);

        numberOfDays = dop.getNumberOfDaysFromAsthmaTime(dop);
        DAYS_ASTHMA_TIME_TEXT.setText(numberOfDays.toString());
        score1 = getScoreFromAsthmaTime();
        SCORE_ASTHMA_TIME_TEXT.setText(score1.toString());

        numberOfDays = dop.getNumberOfDaysFromAsthmaBreath(dop);
        DAYS_ASTHMA_BREATH_TEXT.setText(numberOfDays.toString());
        score2 = getScoreFromAsthmaBreath();
        SCORE_ASTHMA_BREATH_TEXT.setText(score2.toString());

        numberOfDays = dop.getNumberOfDaysFromAsthmaSymptoms(dop);
        DAYS_ASTHMA_SYMPTOMS_TEXT.setText(numberOfDays.toString());
        score3 = getScoreFromAsthmaSymptoms();
        SCORE_ASTHMA_SYMPTOMS_TEXT.setText(score3.toString());

        numberOfDays = dop.getNumberOfDaysFromAsthmaMedication(dop);
        DAYS_ASTHMA_MEDICATION_TEXT.setText(numberOfDays.toString());
        score4 = getScoreFromAsthmaMedication();
        SCORE_ASTHMA_MEDICATION_TEXT.setText(score4.toString());

        TOTAL_SCORE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RATE_ASTHMA_TEXT.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Error... Enter the rate score first", Toast.LENGTH_LONG).show();
                    RATE_ASTHMA_TEXT.requestFocus();
                    return;
                }

                if (score4 == 2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScoreActivity.this);

                    builder.setTitle("Confirm");
                    builder.setMessage("You have used your medication everyday.. Have you used your rescue inhaler or nebulizer medication 3 or more times per day (such as albuterol)?");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            score4 = 1;
                            SCORE_ASTHMA_MEDICATION_TEXT.setText("1");
                            Integer totalScore = score1 + score2 + score3 + score4 + Integer.parseInt(RATE_ASTHMA_TEXT.getText().toString());
                            TOTAL_SCORE_TEXT.setText(String.valueOf(totalScore));

                            if (totalScore > 19) {
                                UNDER_CONTROL_TEXT.setVisibility(View.VISIBLE);
                                HAPPY_IMAGEVIEW.setVisibility(View.VISIBLE);
                                NOT_UNDER_CONTROL_TEXT.setVisibility(View.INVISIBLE);
                                SAD_IMAGEVIEW.setVisibility(View.INVISIBLE);
                            } else {
                                NOT_UNDER_CONTROL_TEXT.setVisibility(View.VISIBLE);
                                SAD_IMAGEVIEW.setVisibility(View.VISIBLE);
                                UNDER_CONTROL_TEXT.setVisibility(View.INVISIBLE);
                                HAPPY_IMAGEVIEW.setVisibility(View.INVISIBLE);
                            }

                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }

                if (score2 == 2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScoreActivity.this);

                    builder.setTitle("Confirm");
                    builder.setMessage("You had shortness of breath everyday.. Have you had shortness of breath more than once a day?");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            score2 = 1;
                            SCORE_ASTHMA_BREATH_TEXT.setText("1");
                            Integer totalScore = score1 + score2 + score3 + score4 + Integer.parseInt(RATE_ASTHMA_TEXT.getText().toString());
                            TOTAL_SCORE_TEXT.setText(String.valueOf(totalScore));

                            if (totalScore > 19) {
                                UNDER_CONTROL_TEXT.setVisibility(View.VISIBLE);
                                HAPPY_IMAGEVIEW.setVisibility(View.VISIBLE);
                                NOT_UNDER_CONTROL_TEXT.setVisibility(View.INVISIBLE);
                                SAD_IMAGEVIEW.setVisibility(View.INVISIBLE);
                            } else {
                                NOT_UNDER_CONTROL_TEXT.setVisibility(View.VISIBLE);
                                SAD_IMAGEVIEW.setVisibility(View.VISIBLE);
                                UNDER_CONTROL_TEXT.setVisibility(View.INVISIBLE);
                                HAPPY_IMAGEVIEW.setVisibility(View.INVISIBLE);
                            }

                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }

                Integer totalScore = score1 + score2 + score3 + score4 + Integer.parseInt(RATE_ASTHMA_TEXT.getText().toString());
                TOTAL_SCORE_TEXT.setText(String.valueOf(totalScore));

                TOTAL_SCORE_BUTTON.setVisibility(View.INVISIBLE);

                if (totalScore > 19) {
                    UNDER_CONTROL_TEXT.setVisibility(View.VISIBLE);
                    HAPPY_IMAGEVIEW.setVisibility(View.VISIBLE);
                    NOT_UNDER_CONTROL_TEXT.setVisibility(View.INVISIBLE);
                    SAD_IMAGEVIEW.setVisibility(View.INVISIBLE);
                } else {
                    NOT_UNDER_CONTROL_TEXT.setVisibility(View.VISIBLE);
                    SAD_IMAGEVIEW.setVisibility(View.VISIBLE);
                    UNDER_CONTROL_TEXT.setVisibility(View.INVISIBLE);
                    HAPPY_IMAGEVIEW.setVisibility(View.INVISIBLE);
                }
            }
        });

       SCORE_ASTHMA_TIME_TEXT.addTextChangedListener(new TextWatcher() {
           @Override
           public void afterTextChanged(Editable s) {
               if ((s.toString().equals("1")) || (s.toString().equals("2")) || (s.toString().equals("3")) || (s.toString().equals("4")) || (s.toString().equals("5")) || (s.toString().equals("")))
                   return;
               else {
                   Toast.makeText(getBaseContext(), "Error... Enter a valid score number between 1 and 5", Toast.LENGTH_SHORT).show();
                   s.clear();
                   SCORE_ASTHMA_TIME_TEXT.requestFocus();
               }
           }
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
           }
           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
           }
       });

        SCORE_ASTHMA_BREATH_TEXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if ((s.toString().equals("1")) || (s.toString().equals("2")) || (s.toString().equals("3")) || (s.toString().equals("4")) || (s.toString().equals("5")) || (s.toString().equals("")))
                    return;
                else{
                    Toast.makeText(getBaseContext(), "Error... Enter a valid score number between 1 and 5", Toast.LENGTH_SHORT).show();
                    s.clear();
                    SCORE_ASTHMA_BREATH_TEXT.requestFocus();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        SCORE_ASTHMA_SYMPTOMS_TEXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if ((s.toString().equals("1")) || (s.toString().equals("2")) || (s.toString().equals("3")) || (s.toString().equals("4")) || (s.toString().equals("5")) || (s.toString().equals("")))
                    return;
                else{
                    Toast.makeText(getBaseContext(), "Error... Enter a valid score number between 1 and 5", Toast.LENGTH_SHORT).show();
                    s.clear();
                    SCORE_ASTHMA_SYMPTOMS_TEXT.requestFocus();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        SCORE_ASTHMA_MEDICATION_TEXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if ((s.toString().equals("1")) || (s.toString().equals("2")) || (s.toString().equals("3")) || (s.toString().equals("4")) || (s.toString().equals("5")) || (s.toString().equals("")))
                    return;
                else {
                    Toast.makeText(getBaseContext(), "Error... Enter a valid score number between 1 and 5", Toast.LENGTH_SHORT).show();
                    s.clear();
                    SCORE_ASTHMA_MEDICATION_TEXT.requestFocus();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        RATE_ASTHMA_TEXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if ((s.toString().equals("1")) || (s.toString().equals("2")) || (s.toString().equals("3")) || (s.toString().equals("4")) || (s.toString().equals("5")) || (s.toString().equals("")))
                    return;
                else{
                    Toast.makeText(getBaseContext(), "Error... Enter a valid score number between 1 and 5", Toast.LENGTH_SHORT).show();
                    s.clear();
                    RATE_ASTHMA_TEXT.requestFocus();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    } // End of onCreate procedure

    public Integer getScoreFromAsthmaTime(){
        Integer score;
        if (numberOfDays == 28)
            score = 1;
        else if ((numberOfDays >=18) && (numberOfDays<=27))
            score = 2;
        else if ((numberOfDays >=7) && (numberOfDays<=17))
            score = 3;
        else if ((numberOfDays >=1) && (numberOfDays<=6))
            score = 4;
        else
            score = 5;

        return score;
    }

    public Integer getScoreFromAsthmaBreath(){
        Integer score;

        if (numberOfDays == 28)
            score = 2;
        else if ((numberOfDays >=11) && (numberOfDays<=27))
            score = 3;
        else if ((numberOfDays >=1) && (numberOfDays<=10))
            score = 4;
        else
            score = 5;

        return score;
    }

    public Integer getScoreFromAsthmaSymptoms (){
        Integer score;

        if ((numberOfDays >=15) && (numberOfDays<=28))
            score = 1;
        else if ((numberOfDays >=7) && (numberOfDays<=14))
            score = 2;
        else if ((numberOfDays >=3) && (numberOfDays<=6))
            score = 3;
        else if ((numberOfDays >=1) && (numberOfDays<=2))
            score = 4;
        else
            score = 5;

        return score;
    }

    public Integer getScoreFromAsthmaMedication (){
        Integer score;

        if ((numberOfDays >=21) && (numberOfDays<=28))
            score = 2;
        else if ((numberOfDays >=8) && (numberOfDays<=20))
            score = 3;
        else if ((numberOfDays >=1) && (numberOfDays<=7))
            score = 4;
        else
            score = 5;

        return score;
    }
}
