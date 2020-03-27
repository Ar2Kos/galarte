package com.activities.galarte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class FeedbackPage extends AppCompatActivity {
    Button btn_next,btn_retake;
    TextView fb_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_feedback);

        addListenerOnButtons();

        int[] answers = getIntent().getIntArrayExtra("Answers");
        calculateStyle(answers);
    }

    protected void addListenerOnButtons() {
        btn_next = findViewById(R.id.fb_btn_next);
        btn_retake = findViewById(R.id.fb_btn_retake);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(view.getContext(),MainPage.class);
                startActivityForResult(mainMenu,0);
            }
        });

        btn_retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent questionnaire = new Intent(view.getContext(), QuestionPage.class);
                startActivityForResult(questionnaire,0);
            }
        });
    }

    protected void calculateStyle(int[] answers){

        //question scores are somewhat subjective but here is my rough guide
        // 5 = strong connection, 3 = medium connection, 1 = small connection
        // 2 = some connection but either there are better option for that style or
        //      that there is another (more important factor involved
        // -3 = put on "don't know" option if the choice is fairly obvious if they like that art style
        int cubismScore = 0;
        int contemporaryScore = 0;
        int abstractScore = 0;
        int surrealismScore = 0;
        int romanticismScore = 0;
        int neoclassicismScore = 0;
        int impressionismScore = 0;
        int realismScore = 0;

        switch(answers[0]) { //line detail/density
            case 1:
                break;
            case 2:
                cubismScore += 2; //smooth lines
                break;
            case 3:
                break;
            case -1:
                break;
        }

        switch(answers[1]) { //line smoothness
            case 1:
                break;
            case 2:
                break;
            case 3:
                cubismScore += 3;
                break;
            case -1:
                break;
        }

        switch(answers[2]) { //line/shape regularness (if the lines can probably be easily defined by maths equations => "regular"
            case 1:
                cubismScore += 2;
                break;
            case 2:
                break;
            case 3:
                cubismScore += 5;
                break;
            case -1:
                cubismScore -= 3;
                break;
        }

        switch(answers[3]) { //geometric vs organic shapes
            case 1:
                cubismScore += 5;
                break;
            case 2:
                cubismScore += 2;
                break;
            case 3:
                break;
            case -1:
                cubismScore -= 3;
                break;
        }

        switch(answers[4]) { //texture roughness
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case -1:
                cubismScore += 3;
                break;
        }

        switch(answers[5]) { //texture roughness (again)
            case 1:
                cubismScore += 3;
                break;
            case 2:
                break;
            case 3:
                break;
            case -1:
                break;
        }

        switch(answers[6]) { //space distance
            case 1:
                break;
            case 2:
                cubismScore += 1;
                break;
            case 3:
                cubismScore += 3;
                break;
            case -1:
                break;
        }

        switch(answers[7]) { // colours primaries -> mixtures
            case 1:
                break;
            case 2:
                break;
            case 3:
                cubismScore += 1;
                break;
            case -1:
                cubismScore += 1;
                break;
        }

        switch(answers[8]) { // colour mutedness
            case 1:
                break;
            case 2:
                break;
            case 3:
                cubismScore += 3;
                break;
            case -1:
                cubismScore += 1;
                break;
        }

        switch(answers[9]) { //value contrast
            case 1:
                break;
            case 2:
                cubismScore += 1;
                break;
            case 3:
                cubismScore += 3;
                break;
            case -1:
                break;
        }

        switch(answers[10]) { //space crowdedness
            case 1:
                cubismScore += 1;
                break;
            case 2:
                break;
            case 3:
                break;
            case -1:
                cubismScore += 3;
                break;
        }

        switch(answers[11]) { //texture density
            case 1:
                break;
            case 2:
                break;
            case 3:
                cubismScore += 3;
                break;
            case -1:
                break;
        }

        switch(answers[12]) { //value brightness
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case -1:
                cubismScore += 3;
                break;
        }

        switch(answers[13]) { //form 3dness
            case 1:
                cubismScore += 3;
                break;
            case 2:
                break;
            case 3:
                break;
            case -1:
                break;
        }




        fb_text = findViewById(R.id.fb_text);
        fb_text.setText(Arrays.toString(answers).toCharArray(), 0, answers.length*3);

    }

}
