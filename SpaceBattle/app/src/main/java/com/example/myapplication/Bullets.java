package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
/* Klasa koja predstavlja niz metaka, implementira thread koji omogućava kretanje metaka
* */
public class Bullets implements  Runnable{
    ArrayList<ImageView> bullets;
    ConstraintLayout layout;
    ImageView attacker;
    Context context;
    boolean runThread=true;

    public Bullets(Context context, ConstraintLayout screen, ImageView attacker)
    {
        this.bullets=new ArrayList<ImageView>();
        this.layout=screen;
        this.attacker=attacker;
        this.context=context;
    }

    public void addBullet(ImageView bullet)
    {
        this.bullets.add(bullet);
    }
    public int getSize()
    {
        return  bullets.size();
    }
    public ImageView getBullet(int index)
    {
        return bullets.get(index);
    }
    public void deleteBullet(int index)
    {
        this.bullets.remove(index);
    }
    public void setRunThread(boolean runThread) {
        this.runThread = runThread;
    }
    public ArrayList<ImageView> getBullets() {
        return bullets;
    }

    @Override
    public void run() {
        while(runThread)
        {
            for (int i=0;i<bullets.size();i++) {
                if(bullets.get(i).getY()>100)
                    bullets.get(i).setY(bullets.get(i).getY() - 20);
                else{
                    bullets.remove(i);
                }
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
    }
}
