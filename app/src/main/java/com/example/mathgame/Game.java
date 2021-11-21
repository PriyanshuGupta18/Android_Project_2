package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {
    TextView score,life,time,question;
    EditText answer;
    Button ok,next;
    Random random=new Random();
    int num1,num2,useranswer,realanswer,userscore=0,userlife=3;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILLIS=60000;
    boolean timer_running;
    long time_left_in_millis=START_TIMER_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score=findViewById(R.id.textViewscore);
        life=findViewById(R.id.textViewlife);
        time=findViewById(R.id.textViewtime);
        question=findViewById(R.id.textViewquestion);
        answer=findViewById(R.id.editTextanswer);
        ok=findViewById(R.id.button);
        next=findViewById(R.id.buttonnext);
        gamelogic();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useranswer=Integer.valueOf(answer.getText().toString());
                pauseTimer();
                if(useranswer==realanswer)
                {
                    userscore+=10;
                    question.setText("Correct answer!");
                    score.setText(""+userscore);
                }
                else
                {
                    userlife-=1;
                    question.setText("Wrong answer");
                    life.setText(""+userlife);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                gamelogic();
                resetTimer();
                if(userlife<=0)
                {
                    Toast.makeText(getApplicationContext(), "GAME OVER", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Game.this,result.class);
                    intent.putExtra("Score",userscore);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    gamelogic();
                }
            }
        });

    }
    public void gamelogic()
    {
        num1=random.nextInt(1000);
        num2=random.nextInt(1000);
        question.setText(num1+"+"+num2);
        realanswer=num1+num2;
        start_timer();
    }
    public void start_timer()
    {
        timer=new CountDownTimer(time_left_in_millis,1000) {
            @Override
            public void onTick(long l) {
                time_left_in_millis=l;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running=false;
                resetTimer();
                pauseTimer();
                updateText();
                userlife-=1;
                question.setText("Sorry time's up");
            }
        }.start();
        timer_running=true;
    }
    public void updateText()
    {
        int second=(int)(time_left_in_millis/1000)%60;
        String time_left=String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void pauseTimer()
    {
        timer.cancel();
        timer_running=false;
    }
    public void resetTimer()
    {
        time_left_in_millis=START_TIMER_IN_MILLIS;
        updateText();
    }
}