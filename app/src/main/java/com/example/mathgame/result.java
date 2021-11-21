package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {
    TextView result;
    Button playagain,quit;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=findViewById(R.id.textViewresult);
        playagain=findViewById(R.id.buttonagain);
        quit=findViewById(R.id.buttonquit);
        Intent intent=getIntent();
        score=intent.getIntExtra("Score",0);
        String userscore=String.valueOf(score);
        result.setText("Your Score "+userscore);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(result.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}