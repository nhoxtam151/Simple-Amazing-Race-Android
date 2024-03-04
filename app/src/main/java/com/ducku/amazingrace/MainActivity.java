package com.ducku.amazingrace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;

//Errors: Cannot stop others timer when already have the winner
public class MainActivity extends AppCompatActivity {
    SeekBar seekbarOne;
    SeekBar seekBarTwo;
    SeekBar seekBarThree;
    boolean isFinish = false; //if finished then cancel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbarOne = findViewById(R.id.seekBarOne);
        seekBarTwo = findViewById(R.id.seekBarTwo);
        seekBarThree = findViewById(R.id.seekBarThree);

        Random randomOne = new Random();
        Random randomTwo = new Random();
        Random randomThree = new Random();
        int speed = 20;
        process("One", seekbarOne, randomOne);
        process("Two", seekBarTwo, randomTwo);
        process("Three", seekBarThree, randomThree);
    }

    void process(String name, SeekBar seekBar, Random random) {
        CountDownTimer timer = new CountDownTimer(60_000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                int currentProgress = seekBar.getProgress();
                seekBar.setProgress(currentProgress + (random.nextInt(5)));
                finish(name, seekBar, this);
                if (isFinish) {
                    this.cancel();
                }
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    void finish(String name, SeekBar seekBar, CountDownTimer timer) {
        if (seekBar.getProgress() >= seekBar.getMax()) {
            Toast.makeText(MainActivity.this, name + " win", Toast.LENGTH_LONG).show();
            timer.cancel();
            isFinish = true;
        }
    }

}