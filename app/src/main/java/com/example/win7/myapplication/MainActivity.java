package com.example.win7.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.GridView;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    public int startValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               button1.setVisibility(View.INVISIBLE);
               button2.setVisibility(View.INVISIBLE);
               button3.setVisibility(View.INVISIBLE);
               startGame();
           }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    public void startGame(){
        final TextView t1 = (TextView) findViewById(R.id.textView);
        final TextView t2 = (TextView) findViewById(R.id.textView2);
        final TextView t3 = (TextView) findViewById(R.id.textView3);
        final TextView t4 = (TextView) findViewById(R.id.textView4);


        final GridView gridGame = (GridView) findViewById(R.id.gridview);

        if(startValue == 0) {
            gridGame.setAdapter(new ImageAdapter(this));
            startValue = -1;
        }
        gridGame.setAdapter(new ImageAdapter(this));
        gridGame.setVisibility(View.VISIBLE);

        gridGame.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                t1.setText(ImageAdapter.x1 + "");
                t2.setText(ImageAdapter.y1 + "");
                t3.setText(ImageAdapter.t + "");
                t4.setText("Score  " + ImageAdapter.getScore());

                for(int y = 0; y < 81; y++){
                    ImageAdapter.rowMatchCheck(y);
                    ImageAdapter.columnMatchCheck(y);
                }
            try {

                startGame();
            }catch(NullPointerException e){
                startGame();
            }
                return false;
            }
        });
    }
}