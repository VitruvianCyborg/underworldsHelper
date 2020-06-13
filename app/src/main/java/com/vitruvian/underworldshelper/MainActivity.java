package com.vitruvian.underworldshelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    int gloryEarnedNum;
    int glorySpentNum;
    TextView gloryEarned;
    TextView glorySpent;
    ToggleButton buttonActivation1;
    ToggleButton buttonActivation2;
    ToggleButton buttonActivation3;
    ToggleButton buttonActivation4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonActivation1 = findViewById(R.id.activation1);
        buttonActivation2 = findViewById(R.id.activation2);
        buttonActivation3 = findViewById(R.id.activation3);
        buttonActivation4 = findViewById(R.id.activation4);
        Button buttonGloryEarned = findViewById(R.id.glory_earned_image);
        Button buttonGlorySpent = findViewById(R.id.glory_spent_image);

        Button buttonReset = findViewById(R.id.button_reset);

        buttonActivation1.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        buttonActivation2.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        buttonActivation3.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        buttonActivation4.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        buttonGloryEarned.setOnClickListener((View.OnClickListener) this);
        buttonGlorySpent.setOnClickListener((View.OnClickListener) this);
        buttonReset.setOnClickListener((View.OnClickListener) this);

        gloryEarned = findViewById(R.id.glory_earned_number);
        glorySpent = findViewById(R.id.glory_spent_number);


        Intent intent = getIntent();
        int newCheck = intent.getIntExtra("newCheck", 0);
        if (newCheck < 1) {
            SharedPreferences savedGlory = this.getPreferences(Context.MODE_PRIVATE);
            gloryEarnedNum = savedGlory.getInt("earned", 0);
            glorySpentNum = savedGlory.getInt("spent", 0);
            gloryEarned.setText(Integer.toString(gloryEarnedNum));
            glorySpent.setText(Integer.toString(glorySpentNum));
            boolean act1 = savedGlory.getBoolean("act1", true);
            boolean act2 = savedGlory.getBoolean("act2", true);
            boolean act3 = savedGlory.getBoolean("act3", true);
            boolean act4 = savedGlory.getBoolean("act4", true);
            if (act1 == false){
                buttonActivation1.setChecked(false);
            }
            if (act2 == false){
                buttonActivation2.setChecked(false);
            }
            if (act3 == false){
                buttonActivation3.setChecked(false);
            }
            if (act4 == false){
                buttonActivation4.setChecked(false);
            }
        } else {
            gloryEarnedNum = 0;
            glorySpentNum = 0;
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            buttonView.setBackgroundResource(R.drawable.ic_activation_front);
        } else {
            buttonView.setBackgroundResource(R.drawable.ic_activation_reverse);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.glory_earned_image:
                gloryEarnedNum++;
                gloryEarned.setText(Integer.toString(gloryEarnedNum));
                break;
            case R.id.glory_spent_image:
                glorySpentNum++;
                glorySpent.setText(Integer.toString(glorySpentNum));
                break;
            case R.id.button_reset:
                gloryEarnedNum = 0;
                glorySpentNum = 0;
                gloryEarned.setText(Integer.toString(gloryEarnedNum));
                glorySpent.setText(Integer.toString(glorySpentNum));
                break;
        }
    }

    public void onPause(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt("earned", gloryEarnedNum);
        editor.putInt("spent", glorySpentNum);
        editor.putBoolean("act1", buttonActivation1.isChecked());
        editor.putBoolean("act2", buttonActivation2.isChecked());
        editor.putBoolean("act3", buttonActivation3.isChecked());
        editor.putBoolean("act4", buttonActivation4.isChecked());
        editor.apply();
        super.onPause();
    }
}
