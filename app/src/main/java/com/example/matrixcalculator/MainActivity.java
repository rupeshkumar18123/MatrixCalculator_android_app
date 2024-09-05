package com.example.matrixcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
Button btn1,btn2,btn3,btn4,btn5,btn6;
//LinearLayout img;
int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);


//        img=findViewById(R.id.img);
//        Animation an= AnimationUtils.loadAnimation(this,R.anim.image);
//        img.startAnimation(an);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent isol;
                isol =new Intent(MainActivity.this ,solutionm.class);
                startActivity(isol);
                c=1;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iadd;
                iadd =new Intent(MainActivity.this ,addition.class);
                startActivity(iadd);
                c=2;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent isub;
                isub =new Intent(MainActivity.this ,subtration.class);
                startActivity(isub);
                c=3;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imulti;
                imulti=new Intent(MainActivity.this ,multiplication.class);
                startActivity(imulti);
                c=4;
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ideterminant;
                ideterminant =new Intent(MainActivity.this ,determinant.class);
                startActivity(ideterminant);
                c=5;
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itranspose;
                itranspose =new Intent(MainActivity.this ,transpose.class);
                startActivity(itranspose);
                c=6;
            }
        });


    }
}