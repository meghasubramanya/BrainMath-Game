package com.example.brainmathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    TextView tvSum,tvResult,tvTimer,tvScore;
    GridLayout gridLayout;
    Button button0,button1,button2,button3,btnPlayAgain;
    ArrayList<Integer> answers=new ArrayList<>();
    int locationOfCorrectAnswer;
    int score=0;
    int noOfQuestions=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvSum=findViewById(R.id.tvSum);
        tvScore=findViewById(R.id.tvScore);
        tvResult=findViewById(R.id.tvResult);
        tvTimer=findViewById(R.id.tvTimer);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        btnPlayAgain=findViewById(R.id.btnPlayAgain);

        btnPlayAgain.setVisibility(View.GONE);

       playAgain(findViewById(R.id.button3));
    }

    public void newQuestion()
    {

        Random random=new Random();
        int a=random.nextInt(49);
        int b=random.nextInt(49);

        tvSum.setText(Integer.toString(a) + "+" + Integer.toString(b));

        //to fill the options.
        locationOfCorrectAnswer=random.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(98);
                //if generated random number and ans is same check first.
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(98);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void answer(View view)
    {

        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
        {
            tvResult.setText("Correct!");
            score++;
        }
        else
        {
            tvResult.setText("Wrong :(");
        }
        noOfQuestions++;
        tvScore.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));

        newQuestion();
    }

    public void playAgain(View view)
    {
        score=0;
        noOfQuestions=0;
        newQuestion();
        tvScore.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        tvTimer.setText("30s");
        tvResult.setText("");
        btnPlayAgain.setVisibility(View.GONE);

        CountDownTimer countDownTimer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {

                tvTimer.setText(String.valueOf(l/1000)+ "s");
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0s");
                tvResult.setText("Done!");
                btnPlayAgain.setVisibility(View.VISIBLE);

                button0.setText("");
                button1.setText("");
                button2.setText("");
                button3.setText("");

                tvScore.setText("0/0");
                tvSum.setText("");
                tvResult.setText("");
            }
        }.start();
    }
}