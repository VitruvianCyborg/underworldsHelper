package com.vitruvian.underworldshelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Button buttonContinue = this.findViewById(R.id.button_continue);
        Button buttonNew = this.findViewById(R.id.button_new);

        buttonContinue.setOnClickListener(this);
        buttonNew.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_continue:
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("newCheck", 0);
                startActivity(intent1);
                break;
            case R.id.button_new:
                Intent intent2 = new Intent(this, MainActivity.class);
                intent2.putExtra("newCheck", 1);
                startActivity(intent2);
                break;
        }
    }
}
