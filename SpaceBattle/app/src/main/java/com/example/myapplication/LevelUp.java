package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import  static com.example.myapplication.Game.lvl;
import static com.example.myapplication.Game.points;
import static com.example.myapplication.Game.timeLeft;

public class LevelUp extends AppCompatActivity {

    Button nextlvl;
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        lvl++;

        nextlvl=findViewById(R.id.btnLevelup);
        number=findViewById(R.id.scoreNumber);
        number.setText("Score: "+(points+timeLeft));
        points=points+timeLeft;

        nextlvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Game.class);
                startActivity(intent);

            }
        });
    }
}