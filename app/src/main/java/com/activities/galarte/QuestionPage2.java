package com.activities.galarte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class QuestionPage2 extends AppCompatActivity {
    private Button btn_next;
    private RadioGroup[] questions = new RadioGroup[7];
    private int[] answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkMode = prefs.getBoolean("pref_dark_mode", false);
        String defaultLocation = prefs.getString("default_location", "");
        String username = prefs.getString("username", "");

        if (defaultLocation.equals("")) {
            defaultLocation = "Bath";
        }

        if (username.equals("")) {
            username = "Guest";
        }

        if (darkMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_page2);

        answers = getIntent().getIntArrayExtra("Answers");

        addListenerOnButton();
        addListenerOnQuestions();
    }

    protected void addListenerOnButton() {
        btn_next = findViewById(R.id.qs_btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedback = new Intent(view.getContext(),FeedbackPage.class);
                int[] currAnswers = new int[questions.length];
                RadioButton radBtn;

                for (int i =0; i < questions.length; i++) {
                    radBtn = findViewById(questions[i].getCheckedRadioButtonId());

                    switch(radBtn.getText().toString()) {
                        case "1":
                            currAnswers[i] = 1;
                            break;
                        case "2" :
                            currAnswers[i] = 2;
                            break;
                        case "3" :
                            currAnswers[i] = 3;
                            break;
                        case "Don't Know":
                            currAnswers[i] = -1;
                            break;
                        default:
                            //not supposed to happen
                            break;
                    }
                }

                int[] nextAnswers = new int[answers.length+currAnswers.length];

                for(int i = 0; i < answers.length; i++) {
                    nextAnswers[i] = answers[i];
                }

                for(int i = 0; i < currAnswers.length; i++) {
                    nextAnswers[answers.length + i] = currAnswers[i];
                }

                feedback.putExtra("Answers", nextAnswers);
                startActivity(feedback);
            }
        });
    }

    protected void addListenerOnQuestions() {
        questions[0] = findViewById(R.id.q0_rad_group);
        questions[1] = findViewById(R.id.q1_rad_group);
        questions[2] = findViewById(R.id.q2_rad_group);
        questions[3] = findViewById(R.id.q3_rad_group);
        questions[4] = findViewById(R.id.q4_rad_group);
        questions[5] = findViewById(R.id.q5_rad_group);
        questions[6] = findViewById(R.id.q6_rad_group);

        questions[0].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                findViewById(R.id.qs_btn_next).setEnabled(areAllQsAnswered());

            }

        });

        questions[1].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                findViewById(R.id.qs_btn_next).setEnabled(areAllQsAnswered());

            }

        });

        questions[2].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                findViewById(R.id.qs_btn_next).setEnabled(areAllQsAnswered());

            }

        });

        questions[3].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                findViewById(R.id.qs_btn_next).setEnabled(areAllQsAnswered());

                //Something to do with the question
            }

        });

        questions[4].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                findViewById(R.id.qs_btn_next).setEnabled(areAllQsAnswered());

                //Something to do with the question
            }

        });

        questions[5].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                findViewById(R.id.qs_btn_next).setEnabled(areAllQsAnswered());

                //Something to do with the question
            }

        });

        questions[6].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                findViewById(R.id.qs_btn_next).setEnabled(areAllQsAnswered());

                //Something to do with the question
            }

        });

    }

    private boolean areAllQsAnswered() {
        int i = 0;
        while(i < questions.length && (questions[i].getCheckedRadioButtonId()) != -1) {
            i++;
        }

        return i==questions.length;
    }


    public void toSettings(MenuItem item) {
        item.setChecked(true);
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    public void toTakeQuiz(MenuItem item) {
        item.setChecked(true);
        Intent takeQuizIntent = new Intent(this, QuestionPage.class);
        startActivity(takeQuizIntent);
    }

    public void toMap(MenuItem item) {
        item.setChecked(true);
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }


}
