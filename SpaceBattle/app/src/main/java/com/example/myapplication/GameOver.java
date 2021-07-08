package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import  static com.example.myapplication.Game.lvl;
import static com.example.myapplication.Game.points;
import static com.example.myapplication.Game.timeLeft;

public class GameOver extends AppCompatActivity {

    TextView score;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score=findViewById(R.id.yourScore);
        score.setText("Score: "+points);

        btn=findViewById(R.id.buttonGO);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvl=1;
                points=0;
                Intent intent = new Intent(getBaseContext(), Game.class);
                startActivity(intent);

            }
        });
    }
}