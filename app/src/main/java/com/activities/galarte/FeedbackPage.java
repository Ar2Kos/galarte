package com.activities.galarte;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Arrays;

public class FeedbackPage extends AppCompatActivity {
    Button btn_next,btn_retake;
    static String style = "undefined";

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
                Intent mainMenu = new Intent(view.getContext(),MapsActivity.class);
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
        //      that there is another (more important factor involved (kinda implies qs needs improvement)
        // -3 = put on "don't know" option if the choice is fairly obvious if they like that art style
        int cubismScore = 0;
        int abstractScore = 0;
        int surrealismScore = 0;
        int romanticismScore = 0;
        int neoclassicismScore = 0;
        int impressionismScore = 0;
        int realismScore = 0;
        int contemporaryScore = 0;

        switch(answers[0]) { //line detail/density
            case 1:
                surrealismScore += 3;
                romanticismScore += 1;
                neoclassicismScore += 3;
                impressionismScore += 3;
                realismScore += 1;
                break;
            case 2:
                surrealismScore += 1;
                cubismScore += 2; //smooth lines
                abstractScore += 1;
                contemporaryScore += 1;
                break;
            case 3:
                romanticismScore += 3;
                neoclassicismScore += 1;
                impressionismScore += 1;
                realismScore += 3;
                break;
            case -1:
                abstractScore += 3;
                contemporaryScore += 3;
                break;
        }

        switch(answers[1]) { //line smoothness
            case 1:
                abstractScore += 1;
                romanticismScore += 1;
                impressionismScore += 5;
                realismScore += 1;
                break;
            case 2:
                impressionismScore += 2;
                surrealismScore += 1;
                romanticismScore += 3;
                neoclassicismScore += 3;
                realismScore += 3;
                break;
            case 3:
                surrealismScore += 3;
                cubismScore += 3;
                neoclassicismScore += 1;
                contemporaryScore += 3;
                break;
            case -1:
                abstractScore += 3;
                impressionismScore -= 3;
                contemporaryScore += 1;
                break;
        }

        switch(answers[2]) { //line/shape regularness (if the lines can probably be easily defined by maths equations => "regular"
            case 1:
                surrealismScore += 3;
                cubismScore += 2;
                abstractScore += 1;
                neoclassicismScore += 1;
                impressionismScore += 1;
                realismScore += 1;
                contemporaryScore += 3;
                break;
            case 2:
                romanticismScore += 1;
                neoclassicismScore += 3;
                impressionismScore += 3;
                realismScore += 3;
                break;
            case 3:
                surrealismScore += 1;
                cubismScore += 5;
                break;
            case -1:
                romanticismScore += 3;
                abstractScore += 3;
                cubismScore -= 3;
                contemporaryScore += 1;
                break;
        }

        switch(answers[3]) { //geometric vs organic shapes
            case 1:
                cubismScore += 5;
                abstractScore += 3;
                contemporaryScore += 1;
                break;
            case 2:
                surrealismScore += 3;
                cubismScore += 2;
                romanticismScore += 1;
                neoclassicismScore += 1;
                contemporaryScore += 3;
                break;
            case 3:
                surrealismScore += 1;
                romanticismScore += 3;
                neoclassicismScore += 3;
                impressionismScore += 3;
                realismScore += 3;
                break;
            case -1:
                realismScore += 1;
                abstractScore += 1;
                cubismScore -= 3;
                impressionismScore += 1;
                break;
        }

        switch(answers[4]) { //texture roughness
            case 1:
                surrealismScore += 3;
                cubismScore += 1;
                romanticismScore += 3;
                neoclassicismScore += 1;
                realismScore += 5;
                break;
            case 2:
                romanticismScore += 1;
                surrealismScore += 1;
                neoclassicismScore += 3;
                impressionismScore += 1;
                realismScore += 2;
                break;
            case 3:
                abstractScore += 1;
                impressionismScore += 3;
                contemporaryScore += 1;
                break;
            case -1:
                cubismScore += 3;
                abstractScore += 3;
                realismScore -= 3;
                contemporaryScore += 3;
                break;
        }

        switch(answers[5]) { //texture roughness (again)
            case 1:
                surrealismScore += 3;
                cubismScore += 3;
                abstractScore += 1;
                neoclassicismScore += 3;
                realismScore += 3;
                break;
            case 2:
                romanticismScore += 1;
                neoclassicismScore += 1;
                contemporaryScore += 3;
                break;
            case 3:
                cubismScore += 1;
                surrealismScore += 1;
                romanticismScore += 3;
                impressionismScore += 3;
                realismScore += 1;
                break;
            case -1:
                abstractScore += 3;
                impressionismScore += 1;
                contemporaryScore += 1;
                break;
        }

        switch(answers[6]) { //space distance
            case 1:
                surrealismScore += 3;
                romanticismScore += 3;
                impressionismScore += 1;
                break;
            case 2:
                cubismScore += 1;
                abstractScore += 3;
                romanticismScore += 1;
                neoclassicismScore += 1;
                realismScore += 1;
                contemporaryScore += 3;
                break;
            case 3:
                cubismScore += 3;
                surrealismScore += 1;
                abstractScore += 1;
                neoclassicismScore += 3;
                realismScore += 3;
                contemporaryScore += 1;
                break;
            case -1:
                impressionismScore += 3;
                break;
        }

        switch(answers[7]) { // colours primaries -> mixtures
            case 1:
                abstractScore += 2;
                impressionismScore += 5;
                break;
            case 2:
                abstractScore += 5;
                surrealismScore += 1;
                romanticismScore += 1;
                neoclassicismScore += 1;
                impressionismScore += 2;
                realismScore += 1;
                break;
            case 3:
                surrealismScore += 3;
                cubismScore += 1;
                romanticismScore += 3;
                neoclassicismScore += 3;
                realismScore += 3;
                contemporaryScore += 1;
                break;
            case -1:
                abstractScore -= 3;
                cubismScore += 1;
                impressionismScore -= 3;
                contemporaryScore += 3;
                break;
        }

        switch(answers[8]) { // colour mutedness
            case 1:
                surrealismScore += 5;
                abstractScore += 5;
                neoclassicismScore += 2;
                impressionismScore += 1;
                contemporaryScore += 3;
                break;
            case 2:
                neoclassicismScore += 5;
                surrealismScore += 2;
                abstractScore += 2;
                romanticismScore += 1;
                impressionismScore += 3;
                realismScore += 2;
                contemporaryScore += 1;
                break;
            case 3:
                cubismScore += 3;
                abstractScore -= 3;
                realismScore += 5;
                break;
            case -1:
                cubismScore += 1;
                surrealismScore -= 3;
                romanticismScore += 3;
                neoclassicismScore -= 3;
                realismScore -= 3;
                break;
        }

        switch(answers[9]) { //value contrast
            case 1:
                abstractScore += 3;
                romanticismScore += 5;
                neoclassicismScore += 3;
                impressionismScore += 3;
                realismScore += 3;
                contemporaryScore += 3;
                break;
            case 2:
                neoclassicismScore += 3;
                cubismScore += 1;
                surrealismScore += 3;
                abstractScore += 1;
                romanticismScore += 2;
                neoclassicismScore += 1;
                impressionismScore += 1;
                realismScore += 1;
                contemporaryScore += 1;
                break;
            case 3:
                surrealismScore += 1;
                cubismScore += 3;
                break;
            case -1:
                romanticismScore -= 3;
                break;
        }

        switch(answers[10]) { //space crowdedness
            case 1:
                cubismScore += 1;
                romanticismScore += 3;
                neoclassicismScore += 2;
                realismScore += 3;
                contemporaryScore += 5;
                break;
            case 2:
                surrealismScore += 2;
                romanticismScore += 1;
                neoclassicismScore += 5;
                impressionismScore += 1;
                realismScore += 1;
                break;
            case 3:
                surrealismScore += 5;
                abstractScore += 1;
                impressionismScore += 3;
                contemporaryScore -= 3;
                break;
            case -1:
                abstractScore += 3;
                surrealismScore -= 3;
                cubismScore += 3;
                neoclassicismScore -= 3;
                contemporaryScore += 2;
                break;
        }

        switch(answers[11]) { //texture density
            case 1:
                romanticismScore += 5;
                neoclassicismScore += 1;
                impressionismScore += 1;
                realismScore += 3;
                contemporaryScore += 1;
                break;
            case 2:
                surrealismScore += 1;
                romanticismScore += 2;
                neoclassicismScore += 3;
                realismScore += 1;
                break;
            case 3:
                cubismScore += 3;
                abstractScore += 1;
                break;
            case -1:
                abstractScore += 3;
                surrealismScore += 3;
                romanticismScore -= 3;
                impressionismScore += 3;
                contemporaryScore += 3;
                break;
        }

        switch(answers[12]) { //value brightness
            case 1:
                abstractScore += 1;
                romanticismScore += 3;
                break;
            case 2:
                surrealismScore += 1;
                neoclassicismScore += 1;
                impressionismScore += 1;
                realismScore += 1;
                contemporaryScore += 3;
                break;
            case 3:
                surrealismScore += 3;
                romanticismScore += 1; //preference for extremes
                neoclassicismScore += 3;
                impressionismScore += 3;
                realismScore += 3;
                break;
            case -1:
                cubismScore += 3;
                abstractScore += 3;
                contemporaryScore += 1;
                break;
        }

//        switch(answers[13]) { //form 3dness
//            case 1:
//                cubismScore += 3; //screwing with perspective makes them look oddly flat
//                abstractScore += 5;
//                impressionismScore += 1;
//                break;
//            case 2:
//                surrealismScore += 3;
//                cubismScore += 1;
//                abstractScore += 2;
//                romanticismScore += 1;
//                neoclassicismScore += 1;
//                impressionismScore += 3;
//                realismScore += 1;
//                break;
//            case 3:
//                romanticismScore += 3;
//                neoclassicismScore += 3;
//                realismScore += 3;
//                break;
//            case -1:
//                surrealismScore += 1;
//                abstractScore -= 3;
//                break;
//        }

        switch(answers[13]) { //concept vs aesthetics
            case 1: //concept
                abstractScore += 3;
                contemporaryScore += 5;
                break;
            case 2: //both
                cubismScore += 3;
                surrealismScore += 3;
                abstractScore += 1;
                romanticismScore += 1;
                neoclassicismScore += 1;
                realismScore += 1;
                contemporaryScore += 2;
                break;
            case 3: //aesthetics
                cubismScore += 1;
                surrealismScore += 1;
                romanticismScore += 3;
                neoclassicismScore += 3;
                impressionismScore += 3;
                realismScore += 3;
                contemporaryScore -= 3;
                break;
            case -1: //no preference
                break;
        }

        double[] scores = new double[8];
        // cubism min = 15,max = 44
        scores[0] = (cubismScore - 15)/30.0;
        // surrealism, abstract min = 16 , max = 46
        scores[1] = (abstractScore - 16)/30.0;
        scores[2] = (surrealismScore - 16)/30.0;
        scores[3] = (romanticismScore - 16)/30.0;
        scores[4] = (neoclassicismScore - 16)/30.0;
        scores[5] = (impressionismScore - 16)/30.0;
        scores[6] = (realismScore - 16)/30.0;
        scores[7] = (contemporaryScore - 16)/30.0;
        System.out.println(Arrays.toString(answers));
        System.out.println(Arrays.toString(scores));




        TextView[] fb_names = new TextView[3];
        TextView[] fb_descs = new TextView[3];
        LinearLayout[] fb_parents = new LinearLayout[3];
        ImageView[] fb_img1s = new ImageView[3];
        ImageView[] fb_img2s = new ImageView[3];

        fb_names[0] = findViewById(R.id.fb_name1);
        fb_names[1] = findViewById(R.id.fb_name2);
        fb_names[2] = findViewById(R.id.fb_name3);

        fb_descs[0] = findViewById(R.id.fb_text1);
        fb_descs[1] = findViewById(R.id.fb_text2);
        fb_descs[2] = findViewById(R.id.fb_text3);

        fb_parents[0] = findViewById(R.id.style1);
        fb_parents[1] = findViewById(R.id.style2);
        fb_parents[2] = findViewById(R.id.style3);

        fb_img1s[0] = findViewById(R.id.fb_img1_1);
        fb_img1s[1] = findViewById(R.id.fb_img2_1);
        fb_img1s[2] = findViewById(R.id.fb_img3_1);

        fb_img2s[0] = findViewById(R.id.fb_img1_2);
        fb_img2s[1] = findViewById(R.id.fb_img2_2);
        fb_img2s[2] = findViewById(R.id.fb_img3_2);



        int[] styleIndex = {-1,-1,-1};
        int index = 0;
        boolean foundPlace = false;

        for(int i = 0; i < scores.length; i++) {
            if(scores[i] > 0.20) {
                index = 0;
                foundPlace = false;
                for (int j = styleIndex.length-1; 0 <= j && !foundPlace; j--) {
                    if(styleIndex[j] != -1) {
                        if (scores[i] > scores[styleIndex[j]]) {
                            if(j < 2) {
                                styleIndex[j+1] = styleIndex[j];
                            }
                        } else {
                            index = j+1;
                            foundPlace = true;
                        }
                    }
                }
                if (index > -1) {
                    styleIndex[index] = i;
                }
            }
        }


        System.out.print(Arrays.toString(styleIndex));

        for(int i = 0; i < styleIndex.length; i++) {
            switch (styleIndex[i]) {
                case 0:
                    fb_names[i].setText("Cubism".toCharArray(),0, "Cubism".length());
                    fb_descs[i].setText(R.string.cubism_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_cubism_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_cubism_img2);
                    break;
                case 1:
                    fb_names[i].setText("Abstract Art".toCharArray(),0, "Abstract Art".length());
                    fb_descs[i].setText(R.string.abstract_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_abstract_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_abstract_img2);
                    break;
                case 2:
                    fb_names[i].setText("Surrealism".toCharArray(),0, "Surrealism".length());
                    fb_descs[i].setText(R.string.surrealism_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_surrealism_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_surrealism_img2);
                    break;
                case 3:
                    fb_names[i].setText("Romanticism".toCharArray(),0, "Romanticism".length());
                    fb_descs[i].setText(R.string.romanticism_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_romanticism_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_romanticism_img2);
                    break;
                case 4:
                    fb_names[i].setText("Neoclassicism".toCharArray(),0, "Neoclassicism".length());
                    fb_descs[i].setText(R.string.neoclassicism_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_neoclassicism_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_neoclassicism_img2);
                    break;
                case 5:
                    fb_names[i].setText("Impressionism".toCharArray(),0, "Impressionism".length());
                    fb_descs[i].setText(R.string.impressionism_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_impressionism_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_impressionism_img2);
                    break;
                case 6:
                    fb_names[i].setText("Realism".toCharArray(),0, "Realism".length());
                    fb_descs[i].setText(R.string.realism_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_realism_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_realism_img2);
                    break;
                case 7:
                    fb_names[i].setText("Contemporary".toCharArray(),0, "Contemporary".length());
                    fb_descs[i].setText(R.string.contemporary_desc);
                    fb_img1s[i].setImageResource(R.drawable.fb_contemporary_img1);
                    fb_img2s[i].setImageResource(R.drawable.fb_contemporary_img2);
                    break;
                default:
                    fb_parents[i].setVisibility(View.GONE);
                    break;
            }
        }

        if(styleIndex[0] == -1) {
            findViewById(R.id.no_genre_text).setVisibility(View.VISIBLE);
        }


        switch (styleIndex[0]) {
            case 0:
                style = "cubism";
                break;
            case 1:
                style = "abstract";
                break;
            case 2:
                style = "surrealism";
                break;
            case 3:
                style = "romanticism";
                break;
            case 4:
                style = "neoclassicism";
                break;
            case 5:
                style = "impressionism";
                break;
            case 6:
                style = "realism";
                break;
            case 7:
                style = "contemporary";
                break;

            default:
                style = "undefined";
                break;
        }

    }

    public static String getStyle() {
        return style;
    }

}
