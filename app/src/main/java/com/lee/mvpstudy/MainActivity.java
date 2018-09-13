package com.lee.mvpstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lee.mvpstudy.activity.ConstraintAnim2Activity;
import com.lee.mvpstudy.activity.ConstraintAnimActivity;
import com.lee.mvpstudy.activity.MaterialActivity;
import com.lee.mvpstudy.activity.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void homeClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void constraintAnimClick(View view) {
        Intent intent = new Intent(this, ConstraintAnimActivity.class);
        startActivity(intent);
    }

    public void constraintAnim2Click(View view) {
        Intent intent = new Intent(this, ConstraintAnim2Activity.class);
        startActivity(intent);
    }

    public void materialClick(View view) {
        Intent intent = new Intent(this, MaterialActivity.class);
        startActivity(intent);
    }
}
