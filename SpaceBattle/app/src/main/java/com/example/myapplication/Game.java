package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import static com.example.myapplication.MainActivity.playerName;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    TextView name;
    ImageView attackerImage;
    Thread threadBullet,threadTargets,createBulletThread,attackOnTargetThread,timeThread;
    Bullets bulletClass;
    Target targetClass;
    AttackOnTarget attackOnTarget;
    boolean add=false,runThread=true;
    ArrayList<ImageView> bullets;
    ArrayList<TargetClass> targets;
    ConstraintLayout screen;
    TextView score,timeLabel;
    Handler handler;
    int time=30;
    public static int lvl=1,points=0,timeLeft=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Intent intent= getIntent();
        Bundle bundle =intent.getExtras();
        handler = new Handler();

        //Kupljenje unesenog imena
        String value = getIntent().getStringExtra("name");
        name=(TextView)findViewById(R.id.labelUserName);
        if(value==null)
        {
            name.setText(playerName);
        }
        else{
            name.setText(value);
            playerName = value;
        }

        screen=findViewById(R.id.screen);
        attackerImage=findViewById(R.id.attacker);
        score=(TextView)findViewById(R.id.labelScore);
        score.setText("Score: "+points);

        timeLabel=(TextView)findViewById(R.id.time);


        //Krerianje thread za metke
        bulletClass=new Bullets(Game.this,screen,attackerImage);
        threadBullet=new Thread(bulletClass);
        bullets=bulletClass.getBullets();

        //Krerianje klase za mete
        targetClass=new Target(Game.this,screen,lvl);
        threadTargets=new Thread(targetClass);
        targets=targetClass.getTargets();


        //thread za dodavanje metaka
        createBulletThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(runThread)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(add) {
                                //Krerianje metak i dodvanje u niz
                                ImageView bullet = new ImageView(Game.this);
                                bullet.setX(attackerImage.getX() + attackerImage.getWidth()/2);
                                bullet.setY(attackerImage.getY());
                                bullet.setImageResource(R.drawable.bullet);
                                bullets.add(bullet);
                                screen.addView(bullet);
                            }
                        }
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
            }
        });


        //Krerianje klase za detektovanje napada
        attackOnTarget=new AttackOnTarget(targets,bullets,screen,score);
        attackOnTargetThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (runThread)
                {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if(targets.size()>0) {
                            attackOnTarget.AttackDetect();
                        }
                        else {
                            runThread=false;
                            bulletClass.setRunThread(false);
                            targetClass.setRunThread(false);
                            Intent i = new Intent(Game.this, com.example.myapplication.LevelUp.class);
                            startActivity(i);
                        }

                    }
                });
                try {
                    Thread.sleep(50);

                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
            }
        });

        //kreiranje thread za odbrojavanje vremena
        timeThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (runThread)
                {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            time--;
                            timeLabel.setText(""+time);
                            timeLeft=time;
                            if(time==0)
                            {
                                runThread=false;
                                bulletClass.setRunThread(false);
                                targetClass.setRunThread(false);
                                Intent i = new Intent(Game.this, com.example.myapplication.GameOver.class);
                                startActivity(i);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
            }
        });

        //pokreranje svih thredova
        threadBullet.start();
        threadTargets.start();
        attackOnTargetThread.start();
        createBulletThread.start();
        timeThread.start();



        screen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    add=true;
                    if(attackerImage.getX() <= screen.getWidth()-attackerImage.getWidth()) {
                        attackerImage.setX(motionEvent.getX());
                    }
                    else if(motionEvent.getX() <= screen.getWidth()-attackerImage.getWidth())
                    {
                        attackerImage.setX(motionEvent.getX());
                    }
                }
                else {
                    add=false;
                }
                return true;
            }
        });



    }

}
