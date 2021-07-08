package com.example.myapplication;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import  static com.example.myapplication.Game.points;

/*
Klasa koja omogucava detektovanje susreta između meta i metaka koje ispaljue napadač
* */
public class AttackOnTarget {

    ArrayList<TargetClass> targets;
    ArrayList<ImageView> bullets;
    ConstraintLayout screen;
    TextView score;
    public AttackOnTarget(ArrayList<TargetClass> targets, ArrayList<ImageView> bullets,ConstraintLayout screen,TextView score)
    {
        this.targets=targets;
        this.bullets=bullets;
        this.screen=screen;
        this.score=score;
    }

    //metoda za detektovanje susreta
    public void AttackDetect(){

            for(int j=0;j<bullets.size();j++)
            {
                for(int i=0;i<targets.size();i++)
                {
                    ImageView targeti=targets.get(i);
                    ImageView bulletj=bullets.get(j);

                    if((bulletj.getX()>=targeti.getX() && bulletj.getX()<=(targeti.getX()+targeti.getWidth()))
                            && (bulletj.getY()<=(targeti.getY()+targeti.getHeight()) && bulletj.getY()>=targeti.getY()))
                    {
                        screen.removeView(targets.get(i));
                        screen.removeView(bulletj);
                        bulletj.setY(0);
                        this.targets.remove(i);
                        points++;
                        this.score.setText("Score: "+points);
                    }
                    else if(bulletj.getY()<150)
                    {
                        screen.removeView(bulletj);

                    }
                }
            }




    }
}
