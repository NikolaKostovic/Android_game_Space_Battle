package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

/**
 * Klasa koja predstavlja metu da naznaci u kom se smjeru kreće
 */
@SuppressLint("AppCompatCustomView")
public class TargetClass extends ImageView {
    boolean moveRight=false;
    public TargetClass(Context context) {
        super(context);
        setImageResource(R.drawable.target);
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
}
