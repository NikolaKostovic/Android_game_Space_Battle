package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.Game.lvl;
/*
Klasa koja sadrži mete i omogućava njihovo kretanje pomoću threda
* */

public class Target implements Runnable {
    ArrayList<TargetClass> targets;
    Context context;
    int positionx=100,positiony;
    int width;
    ConstraintLayout screen;
    boolean runThread=true;
    public Target(Context context, ConstraintLayout screen,Integer level)
    {
        positiony = 100;
        this.context=context;
        this.screen=screen;
        //Kreiranje niza za mete
        targets=new ArrayList<TargetClass>();
        for (int i=0;i<lvl*5;i++)
        {
            if(i%5==0)
            {
                positiony+=100;
                positionx=150;
            }

            TargetClass target1=new TargetClass(context);
            target1.setX(positionx);
            target1.setY(positiony);
            target1.setMoveRight(true);
            screen.addView(target1);
            targets.add(target1);
            positionx+=130;
        }


    }

    public int getSize()
    {
        return  targets.size();
    }
    public TargetClass getTarget(int index)
    {
        return targets.get(index);
    }
    public void addTarget(TargetClass target)
    {
        targets.add(target);
    }
    public void deleteTarget(int index)
    {
        targets.remove(index);
    }
    public void setRunThread(boolean runThread) {
        this.runThread = runThread;
    }

    public ArrayList<TargetClass> getTargets() {
        return targets;
    }

    @Override
    public void run() {
        while(runThread)
        {
            if(targets.size()>0)
            {
                if(targets.get(0).getX()<=0)
                {
                    for(int i=0;i<targets.size();i++) {
                        targets.get(i).setMoveRight(true);
                    }
                }
                else if(targets.get(targets.size()-1).getX()>=(this.screen.getWidth()-100))
                {
                    for(int i=0;i<targets.size();i++) {
                        targets.get(i).setMoveRight(false);
                    }
                }
                for(int i=0;i<targets.size();i++)
                {
                    if(targets.get(i).isMoveRight())
                    {
                        targets.get(i).setX(targets.get(i).getX() + 20);
                    }
                    else
                    {
                        targets.get(i).setX(targets.get(i).getX() - 20);
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
    }
}
